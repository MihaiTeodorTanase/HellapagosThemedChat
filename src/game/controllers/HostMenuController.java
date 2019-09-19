package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.BindException;

public class HostMenuController implements Runnable {
    @FXML
    public Button createButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public Label warningLabel;

    private Thread warningsThread;
    private static HostMenuController instance = null;

    private HostMenuController() {
    }

    public static HostMenuController getInstance() {
        if (instance == null) {
            instance = new HostMenuController();
        }
        return instance;
    }

    void loadHostMenuScreen(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hostMenu.fxml"));
        loader.setControllerFactory(controllerType -> getInstance());
        Parent view = loader.load();
        stage.setScene(new Scene(view));
        OverallController.loadPreferences(stage);
    }

    public void onCreatePressed(ActionEvent event) throws Exception {
        if (!nameTextField.getText().matches("[a-zA-Z0-9]*") || nameTextField.getText().equals("")) {
            warningLabel.setText("Use a-z 0-9 for name!");
            warningLabel.setVisible(true);
            initializeWarningsThread();
        } else {
            OverallController.getRoomController().setHost(true);
            try {
                OverallController.getServerController().startChatServerThread();
                OverallController.getRoomController().setIdentity("localhost", nameTextField.getText());
                OverallController.loadRoom((Stage) ((Node) event.getSource()).getScene().getWindow());
            } catch (BindException e) {
                warningLabel.setText("Port 28015 is already in use!");
                warningLabel.setVisible(true);
                initializeWarningsThread();
            }
        }
    }

    private void initializeWarningsThread() {
        warningsThread = new Thread(this);
        warningsThread.setName("WarningsThread");
        warningsThread.start();
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        OverallController.loadStartMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @Override
    public void run() {
        createButton.setDisable(true);
        boolean running = true;
        while (running) {
            if (warningLabel.isVisible()) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ignored) {
                }
                warningLabel.setVisible(false);
                createButton.setDisable(false);
                warningsThread = null;
                running = false;
            }
        }
    }
}
