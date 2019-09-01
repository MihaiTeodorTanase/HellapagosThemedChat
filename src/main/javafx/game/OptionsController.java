package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {
    @FXML
    CheckBox fscheckbox;

    private static boolean toggledFullScreen;

    static void loadOptionsScreen(ActionEvent event)throws IOException{
        Parent optionsView = FXMLLoader.load(OptionsController.class.getResource("options.fxml"));
        Stage optionsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene optionsScene = new Scene(optionsView);
        optionsStage.setScene(optionsScene);
        optionsStage.setFullScreen(OptionsController.isToggledFullScreen());
        optionsStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        optionsStage.show();
    }

    static boolean isToggledFullScreen() {
        return toggledFullScreen;
    }

    private void setToggledFullScreen(boolean toggledFullScreen) {
        OptionsController.toggledFullScreen = toggledFullScreen;
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        MainMenuController.loadMainMenuScreen(event);
    }

    public void onResolutionPressed() {
    }

    public void onToggleFullScreenPressed(ActionEvent event) {
        Stage mainMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (toggledFullScreen) {
            mainMenuStage.setFullScreen(false);
            setToggledFullScreen(false);
        } else {
            mainMenuStage.setFullScreen(true);
            setToggledFullScreen(true);
        }
    }

    public void evaluateSettings(){
        if(isToggledFullScreen()){
            fscheckbox.setSelected(true);
        }
    }
}
