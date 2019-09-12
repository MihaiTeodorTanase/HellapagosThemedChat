package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGameMenuController {

    public void onPressedBack(ActionEvent event) throws IOException {
        OverallController.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedJoin(ActionEvent event) throws IOException {
        OverallController.loadJoinMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedHost() {

    }

    void loadStartGameMenuScreen(Stage stage) throws IOException {
        Parent optionsView = FXMLLoader.load(OptionsController.class.getResource("startGameMenu.fxml"));
        stage.setScene(new Scene(optionsView));
        OverallController.loadPreferences(stage);
    }
}
