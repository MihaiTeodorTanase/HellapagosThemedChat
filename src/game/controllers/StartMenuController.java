package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {
    private static StartMenuController instance = null;
    private StartMenuController() {
    }

    public static StartMenuController getInstance() {
        if (instance == null) {
            instance = new StartMenuController();
        }
        return instance;
    }
    void loadStartGameMenuScreen(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("startMenu.fxml"));
        loader.setControllerFactory(controllerType -> getInstance());
        Parent view =loader.load();
        stage.setScene(new Scene(view));
        OverallController.loadPreferences(stage);
    }

    public void onPressedBack(ActionEvent event) throws IOException {
        OverallController.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedJoin(ActionEvent event) throws IOException {
        OverallController.loadJoinMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedHost(ActionEvent event) throws Exception {
        OverallController.loadHostMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }


}
