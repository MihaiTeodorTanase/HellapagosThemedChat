package game.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.util.prefs.Preferences;

public class RoomController extends FXMLLoader implements Runnable {

    @FXML
    public TextArea roomChat;
    @FXML
    public TextField chatTextField;
    @FXML
    public Button sendButton;
    @FXML
    public TextArea chatPlayersList;
    @FXML
    public TextField nrOfOnlinePlayersField;
    @FXML
    public Label disconnectLabel;

    private Thread chatThread;
    private boolean isHost = false;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private boolean login = false;
    private String name;

    private static RoomController instance = null;

    private RoomController() {

    }

    public static RoomController getInstance() {
        if (instance == null) {
            instance = new RoomController();
        }
        return instance;
    }

    public void loadRoom(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("room.fxml"));
        loader.setControllerFactory(controllerType -> getInstance());
        Parent optionsView = loader.load();
        stage.setScene(new Scene(optionsView));
        OverallController.loadPreferences(stage);
        chatThread = new Thread(this);
        chatThread.start();
    }

    public void onPressedBack(ActionEvent event) throws IOException {
        logout();
        OverallController.loadStartMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void logout() {

        if (this.login) {
            try {
                login = false;
                chatThread = null;
                if (out != null) {
                    if (isHost) {
                        out.write("$shuttingdown");
                    }
                    out.close();
                }
                if (socket != null)
                    socket.close();
                if (in != null)
                    in.close();
                if (isHost)
                    OverallController.getServerController().stopChatServerThread();
            } catch (IOException ignored) {
            }
        }
    }

    public void onPressedSend() throws IOException {

        if (!chatTextField.getText().contains("$") && !chatTextField.getText().matches("^ *")) {
            try {
                out.write(chatTextField.getText().trim());
                out.newLine();
                out.flush();
            } catch (IOException ignored) {
            }
            chatTextField.setText("");
        } else {
            chatTextField.setText("");
        }
    }

    public void onEnter(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            try{
            onPressedSend();}
            catch (IOException ignored){}
        }
    }

    public void setIdentity(String ip, String name) throws IOException {
        this.name = name;
        login = true;
        socket = new Socket(ip, 28015);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write(name);
        out.newLine();
        out.flush();
    }


    public void setHost(boolean host) {
        isHost = host;
    }

    @Override
    public void run() {
        disconnectLabel.setVisible(false);
        sendButton.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, OverallController.getRoomController()::logout);
        try {
            while (login) {
                String msg = in.readLine();
                if (msg == null || msg.equals(""))
                    break;
                if (msg.contains("$onPlayers")) {
                    nrOfOnlinePlayersField.clear();
                    nrOfOnlinePlayersField.appendText(msg.substring(0, msg.length() - 10) + " online");
                } else if (msg.contains("$online")) {
                    chatPlayersList.appendText(msg.substring(0, msg.length() - 7) + "\n");
                } else if (msg.equals("$clearchat")) {
                    chatPlayersList.clear();
                } else if (msg.contains("/clear")) {
                    roomChat.clear();
                } else if (msg.contains("$shuttingdown")) {
                    disconnectLabel.setVisible(true);
                    logout();
                } else {
                    roomChat.appendText(msg + "\n");
                }
            }
        } catch (IOException ignored) {
        }
    }

    private <T extends Event> void logout(T t) {
        logout();
    }
}
