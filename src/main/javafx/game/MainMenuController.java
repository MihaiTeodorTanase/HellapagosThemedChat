package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void loadMainMenuScreen(Stage mainMenuStage, OptionsController optionsController) throws IOException {
        Parent mainMenuView = FXMLLoader.load(MainMenuController.class.getResource("mainMenu.fxml"));
        mainMenuStage.setScene(new Scene(mainMenuView));
        mainMenuStage.setFullScreen(optionsController.isToggledFullScreen());
        mainMenuStage.show();
    }
    public void onPressedStart(){

    }

    public void onPressedRules(){

    }

    public void onPressedOptions(ActionEvent event) throws IOException {
        OverallController.loadOptions((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onPressedExit(){
        System.exit(1);
    }
}
