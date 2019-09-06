package game;

import javafx.stage.Stage;

import java.io.IOException;

public class OverallController {
    private static MainMenuController mainMenuController = new MainMenuController();
    private static OptionsController optionsController = new OptionsController();

    static void loadMainMenu(Stage stage) throws IOException {
        mainMenuController.loadMainMenuScreen(stage);
    }

    static void loadOptions(Stage stage) throws IOException {
        optionsController.loadOptionsScreen(stage);
    }
}

