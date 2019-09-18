package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;

public class JoinMenuController implements Runnable {
    private static JoinMenuController instance = null;
    public TextField ipTextField;
    public Button connectButton;
    private Thread warningsThread;
    public TextField nameTextField;
    public Label warningLabel;

    private JoinMenuController() {
    }

    public static JoinMenuController getInstance() {
        if (instance == null) {
            instance = new JoinMenuController();
        }
        return instance;
    }

    void loadJoinMenuScreen(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("joinMenu.fxml"));
        loader.setControllerFactory(controllerType -> getInstance());
        Parent view = loader.load();
        stage.setScene(new Scene(view));
        OverallController.loadPreferences(stage);

    }

    public void onBackPressed(ActionEvent event) throws IOException {
        OverallController.loadStartMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onConnectPressed(ActionEvent event) {
        if (!nameTextField.getText().matches("[a-zA-Z0-9]*") || nameTextField.getText().equals("")) {
            warningLabel.setText("Use a-z 0-9 for name!");
            warningLabel.setVisible(true);
            warningsThread = new Thread(this);
            warningsThread.start();
        } else {
            try {
                warningsThread = new Thread(this);
                warningsThread.start();
                OverallController.getRoomController().setIdentity(ipTextField.getText(), nameTextField.getText());
                OverallController.loadRoom((Stage) ((Node) event.getSource()).getScene().getWindow());
                warningsThread = null;
            } catch (ConnectException e) {
                warningLabel.setText("Connection refused!");
                warningLabel.setVisible(true);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void lene() {
        ipTextField.setText("25.99.76.3");
//            nameTextField.setText("Mihai123123");
    }

    @Override
    public void run() {
        connectButton.setDisable(true);
        boolean running = true;
        while (running) {
            if (warningLabel.isVisible()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
                warningLabel.setVisible(false);
                connectButton.setDisable(false);
                warningsThread = null;
                running=false;
            }
        }
    }
}
