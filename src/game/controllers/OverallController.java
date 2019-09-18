package game.controllers;

import javafx.stage.Stage;

import java.io.IOException;

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
        }
        if (optionsController.getPreferences().get("musickey", "false").equals("true")) {

        } else {
            stopMusic();
        }
        stage.show();
    }

}

