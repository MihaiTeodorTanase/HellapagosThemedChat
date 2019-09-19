package game.controllers;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

public class OverallController {
    private static final MainMenuController mainMenuController = MainMenuController.getInstance();
    private static final OptionsController optionsController = OptionsController.getInstance();
    private static final StartMenuController startMenuController = StartMenuController.getInstance();
    private static final JoinMenuController joinMenuController = JoinMenuController.getInstance();
    private static final RoomController roomController = RoomController.getInstance();
    private static final MusicController musicController = new MusicController();
    private static final ServerController serverController = ServerController.getInstance();
    private static final HostMenuController hostMenuController = HostMenuController.getInstance();

    public static void loadHostMenu(Stage stage) throws IOException {
        hostMenuController.loadHostMenuScreen(stage);
    }

    public static void loadMainMenu(Stage stage) throws IOException {
        mainMenuController.loadMainMenuScreen(stage);
    }

    public static void loadOptions(Stage stage) throws IOException {
        optionsController.loadOptionsScreen(stage);
    }

    public static void loadStartMenu(Stage stage) throws IOException {
        startMenuController.loadStartGameMenuScreen(stage);
    }

    public static void loadJoinMenu(Stage stage) throws IOException {
        joinMenuController.loadJoinMenuScreen(stage);
    }

    public static void loadRoom(Stage stage) throws IOException {
        roomController.loadRoom(stage);
    }

    public static void playMusic() {
        musicController.playMusic();
    }

    public static void stopMusic() {
        musicController.stopMusic();
    }

    public static RoomController getRoomController() {
        return roomController;
    }

    public static MusicController getMusicController() {
        return musicController;
    }

    public static ServerController getServerController() {
        return serverController;
    }

    public static OptionsController getOptionsController() {
        return optionsController;
    }

    public static void loadPreferences(Stage stage) {
        if (optionsController.getPreferences().get("fullscreenkey", "false").equals("true")) {
            stage.setFullScreen(true);
        } else {
            stage.setFullScreen(false);
            stage.setX(optionsController.getPreferences().getDouble("windowX", 10));
            stage.setY(optionsController.getPreferences().getDouble("windowY", 10));
            stage.setWidth(optionsController.getPreferences().getDouble("windowWidth", 1280));
            stage.setHeight(optionsController.getPreferences().getDouble("windowHeight", 720));
        }
        if (optionsController.getPreferences().get("musickey", "false").equals("true")) {

        } else {
            stopMusic();
        }
        stage.show();
    }

    public static void saveWindowSizeAndPositionWhenChanged(Stage stage) {
        Preferences preferences = optionsController.getPreferences();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double XcenterOfScreen = (screenBounds.getWidth() - 1280) / 2;
        double YcenterOfScreen = (screenBounds.getHeight() - 720) / 2 ;
        stage.xProperty().addListener((obs, oldVal, newVal) -> preferences.putDouble("windowX", (Double) newVal));
        stage.yProperty().addListener((obs, oldVal, newVal) -> preferences.putDouble("windowY", (Double) newVal));
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (stage.getWidth() < 1280) {
                stage.setWidth(1280);
                stage.setX(XcenterOfScreen);
                stage.setY(YcenterOfScreen);
            } else {
                preferences.putDouble("windowWidth", (Double) newVal);
            }
        });
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (stage.getHeight() < 720) {
                stage.setHeight(720);
                stage.setX((screenBounds.getWidth() - 1280) / 2);
                stage.setY((screenBounds.getHeight() - 720) / 2);
            } else {
                preferences.putDouble("windowHeight", (Double) newVal);
            }
        });
    }

}

