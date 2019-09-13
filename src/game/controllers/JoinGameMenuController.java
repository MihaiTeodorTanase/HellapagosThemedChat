package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JoinGameMenuController {

    void loadJoinGameMenuScreen(Stage stage) throws IOException {
        Parent optionsView = FXMLLoader.load(JoinGameMenuController.class.getResource("joinGameMenu.fxml"));
        stage.setScene(new Scene(optionsView));
        OverallController.loadPreferences(stage);
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        OverallController.loadStartMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
