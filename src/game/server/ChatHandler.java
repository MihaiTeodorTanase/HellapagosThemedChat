package game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatHandler extends AbstractHandler {
    private final static int TIMEOUT = 600000;
    private List<PrintWriter> printWriterList = Collections.synchronizedList(new ArrayList<>());
    private HashMap<PrintWriter, String> onlinePlayers = new HashMap<>();

    @Override
    public void handleTask(Socket socket) {
        String name = null;
        BufferedReader in;
        PrintWriter out = null;
        AtomicInteger numberOfPlayers = new AtomicInteger();
        numberOfPlayers.getAndSet(0);
        try {

            socket.setSoTimeout(TIMEOUT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            printWriterList.add(out);
            name = in.readLine();
            onlinePlayers.put(out, name);
            sendMessage("$clearchat");

            printWriterList.forEach(key -> {
                numberOfPlayers.getAndIncrement();
                ;
                sendMessage(onlinePlayers.get(key) + "$online");
            });
            sendMessage(numberOfPlayers.toString() + "$onPlayers");
            sendMessage(name + " has joined the room.");
            String message;

            while ((message = in.readLine()) != null) {
                sendMessage(name + " : " + message);
            }
        } catch (IOException e) {
            System.err.println(e);
            sendMessage("Error in server");
        } finally {
            printWriterList.remove(out);
            sendMessage(name + " has left the room.");
            onlinePlayers.remove(out);
            sendMessage("$clearchat");
            numberOfPlayers.getAndSet(0);

            printWriterList.forEach(key -> {
                numberOfPlayers.getAndIncrement();
                sendMessage(onlinePlayers.get(key) + "$online");
            });
            sendMessage(numberOfPlayers.toString() + "$onPlayers");

        }
    }

    private void sendMessage(String message) {
        for (PrintWriter out : printWriterList) {
            synchronized (out) {
                out.println(message);
            }
        }
    }
}
