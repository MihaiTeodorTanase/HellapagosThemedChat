package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {

    public void onBackPressed(ActionEvent event) throws IOException {
        Parent mainMenuView = FXMLLoader.load(getClass().getResource("fxml/mainMenu.fxml"));
        Stage mainMenuStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene mainMenuScene = new Scene(mainMenuView);
        mainMenuStage.setScene(mainMenuScene);
        mainMenuStage.show();
    }

    public void onResolutionPressed(){

    }

    public void onToggleFullScreenPressed(ActionEvent event) {
//        Stage mainMenuStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        mainMenuStage.setFullScreen(true);
    }
}
