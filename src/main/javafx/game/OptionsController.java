package game;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private boolean toggledFullScreen;

    void loadOptionsScreen(Stage optionsStage) throws IOException {
        Parent optionsView = FXMLLoader.load(OptionsController.class.getResource("options.fxml"));
        optionsStage.setScene(new Scene(optionsView));
        optionsStage.setFullScreen(isToggledFullScreen());
        optionsStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        optionsStage.show();
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        OverallController.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    void onResolutionPressed() {
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

    public void evaluateSettings() {
        if (isToggledFullScreen()) {
            fscheckbox.setSelected(true);
        }
    }

    boolean isToggledFullScreen() {
        return toggledFullScreen;
    }

    private void setToggledFullScreen(boolean toggledFullScreen) {
        this.toggledFullScreen = toggledFullScreen;
    }
}
