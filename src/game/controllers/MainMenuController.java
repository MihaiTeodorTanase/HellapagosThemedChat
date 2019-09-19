package game.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

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
        OverallController.saveWindowSizeAndPositionWhenChanged(stage);
        stage.show();
    }

    private <T extends Event> void setSizePreferencesToDefaultSize(T t) {
        Preferences preferences =OverallController.getOptionsController().getPreferences();
        preferences.putDouble("windowWidth",1280);
        preferences.putDouble("windowHeight",720);
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
