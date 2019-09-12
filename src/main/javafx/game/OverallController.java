package game;

import javafx.stage.Stage;

import java.io.IOException;

public class OverallController {
    private static final MainMenuController mainMenuController = new MainMenuController();
    private static final OptionsController optionsController = new OptionsController();
    private static final MusicController musicController = new MusicController();
    private static final StartGameMenuController startGameMenuController = new StartGameMenuController();
    private static final JoinGameMenuController joinGameMenuController = new JoinGameMenuController();

    static void loadMainMenu(Stage stage) throws IOException {
        mainMenuController.loadMainMenuScreen(stage);
    }

    static void loadOptions(Stage stage) throws IOException {
        optionsController.loadOptionsScreen(stage);
    }

    static void loadStartMenu(Stage stage) throws IOException {
        startGameMenuController.loadStartGameMenuScreen(stage);
    }

    static void loadJoinMenu(Stage stage) throws IOException {
        joinGameMenuController.loadJoinGameMenuScreen(stage);
    }

    static void playMusic() {
        musicController.playMusic();
    }

    static void stopMusic() {
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

