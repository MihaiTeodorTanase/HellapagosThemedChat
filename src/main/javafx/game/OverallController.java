package game;

import javafx.stage.Stage;

import java.io.IOException;

public class OverallController {
    private static final MainMenuController mainMenuController = new MainMenuController();
    private static final OptionsController optionsController = new OptionsController();
    private static final MusicController musicController = new MusicController();

    static void loadMainMenu(Stage stage) throws IOException {
        mainMenuController.loadMainMenuScreen(stage);
    }

    static void loadOptions(Stage stage) throws IOException {
        optionsController.loadOptionsScreen(stage);
    }

    static void playMusic(){
        musicController.playMusic();
    }

    static void stopMusic(){
        musicController.stopMusic();
    }

    public static MusicController getMusicController() {
        return musicController;
    }
}

