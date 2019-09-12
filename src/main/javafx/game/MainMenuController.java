package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void loadMainMenuScreen(Stage mainMenuStage) throws IOException {
        Parent mainMenuView = FXMLLoader.load(MainMenuController.class.getResource("mainMenu.fxml"));
        mainMenuStage.setScene(new Scene(mainMenuView));
        if(new OptionsController().getPreferences().get("fullscreenkey" , "false").equals("true")){
            mainMenuStage.setFullScreen(true);
        }
        else {
            mainMenuStage.setFullScreen(false);
        }
        if(new OptionsController().getPreferences().get("musickey" , "true").equals("false")){
            OverallController.stopMusic();
        }
        mainMenuStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        mainMenuStage.show();
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
