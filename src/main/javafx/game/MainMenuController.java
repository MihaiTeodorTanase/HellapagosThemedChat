package game;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;

public class MainMenuController {
    public static void loadMainMenuScreen(ActionEvent event) throws IOException {
        Parent mainMenuView = FXMLLoader.load(MainMenuController.class.getResource("mainMenu.fxml"));
        Stage mainMenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene mainMenuScene = new Scene(mainMenuView);
        mainMenuStage.setScene(mainMenuScene);
        mainMenuStage.setFullScreen(OptionsController.isToggledFullScreen());
        mainMenuStage.show();
    }
    public void onPressedStart(){

    }

    public void onPressedRules(){

    }

    public void onPressedOptions(ActionEvent event) throws IOException {
        OptionsController.loadOptionsScreen(event);
    }

    public void onPressedExit(){
        System.exit(1);
    }
}
