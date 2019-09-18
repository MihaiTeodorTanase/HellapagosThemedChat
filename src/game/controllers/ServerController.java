package game.controllers;

import game.server.ChatHandler;
import game.server.TCPServer;

public class ServerController {

    private Thread serverThread;
    private TCPServer<ChatHandler> server;
    private static ServerController instance = null;
    private boolean chatServerStarted = false;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public  void startChatServerThread() throws Exception {
        if(!chatServerStarted){
            server=new TCPServer<>(28015, ChatHandler.class);
            serverThread = new Thread(server);
            serverThread.start();
            this.chatServerStarted =true;
        }
    }

    public  void stopChatServerThread() {
        if(chatServerStarted){
            server.stopServer();
            serverThread=null;
            this.chatServerStarted =false;
        }
    }
}
