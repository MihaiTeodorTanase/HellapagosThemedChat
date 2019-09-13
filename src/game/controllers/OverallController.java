package game.controllers;

import javafx.stage.Stage;

import java.io.IOException;

public class OverallController {
    private static final MainMenuController mainMenuController = new MainMenuController();
    private static final OptionsController optionsController = new OptionsController();
    private static final MusicController musicController = new MusicController();
    private static final StartGameMenuController startGameMenuController = new StartGameMenuController();
    private static final JoinGameMenuController joinGameMenuController = new JoinGameMenuController();

    public static void loadMainMenu(Stage stage) throws IOException {
        mainMenuController.loadMainMenuScreen(stage);
    }

    public static void loadOptions(Stage stage) throws IOException {
        optionsController.loadOptionsScreen(stage);
    }

    public static void loadStartMenu(Stage stage) throws IOException {
        startGameMenuController.loadStartGameMenuScreen(stage);
    }

    public static void loadJoinMenu(Stage stage) throws IOException {
        joinGameMenuController.loadJoinGameMenuScreen(stage);
    }

    public static void playMusic() {
        musicController.playMusic();
    }

    public static void stopMusic() {
        musicController.stopMusic();
    }

    public static MusicController getMusicController() {
        return musicController;
    }

    static void loadPreferences(Stage stage) {
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

