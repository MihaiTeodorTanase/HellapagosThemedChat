package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private static MainMenuController instance = null;

    private MainMenuController() {
    }

    public static MainMenuController getInstance() {
        if (instance == null) {
            instance = new MainMenuController();
        }
        return instance;
    }

    public void loadMainMenuScreen(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainMenu.fxml"));
        loader.setControllerFactory(controllerType -> getInstance());
        Parent mainMenuView =loader.load();
        stage.setScene(new Scene(mainMenuView));
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        OverallController.loadPreferences(stage);
        stage.show();
    }

    public void onPressedStart(ActionEvent event) throws IOException {
    OverallController.loadStartMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedRules() {

    }

    public void onPressedOptions(ActionEvent event) throws IOException {
        OverallController.loadOptions((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedExit() {
        System.exit(1);
    }
}
