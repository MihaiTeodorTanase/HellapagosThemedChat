package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.io.IOException;

public class MainMenuController {

    public void onPressedStart(){

    }

    public void onPressedRules(){

    }

    public void onPressedOptions(ActionEvent event) throws IOException {
        Parent optionsView = FXMLLoader.load(getClass().getResource("fxml/options.fxml"));
        Stage optionsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene optionsScene = new Scene(optionsView);
        optionsStage.setScene(optionsScene);
        optionsStage.setFullScreen(OptionsController.isToggledFullScreen());
        optionsStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        optionsStage.show();
    }

    public void onPressedExit(){
        System.exit(1);
    }
}
