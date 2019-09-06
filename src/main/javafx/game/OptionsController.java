package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

public class OptionsController {
    @FXML
    CheckBox fscheckbox;
    @FXML
    CheckBox id_music_checkBox;

    public Preferences preferences = Preferences.userNodeForPackage(this.getClass());

    void loadOptionsScreen(Stage optionsStage) throws IOException {
        Parent optionsView = FXMLLoader.load(OptionsController.class.getResource("options.fxml"));
        optionsStage.setScene(new Scene(optionsView));
        if(preferences.get("fullscreenkey" , "false").equals("true")){
            optionsStage.setFullScreen(true);
        }
        else {
            optionsStage.setFullScreen(false);
        }
        if(preferences.get("musickey" , "false").equals("true")){

        }
        else {
            MusicController.stopMusic();
        }

        optionsStage.show();
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        OverallController.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onToggleFullScreenPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean isChecked = fscheckbox.isSelected();
        Path path = Paths.get(OptionsController.class.getResource("options.fxml").toExternalForm().replaceFirst("file:/", ""));
        Charset charset = StandardCharsets.UTF_8;

        if (!isChecked) {
            stage.setFullScreen(false);
            preferences.put("fullscreenkey","false");
            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#onToggleFullScreenPressed\" selected=\"true\"", "\"#onToggleFullScreenPressed\" selected=\"false\"");
                Files.write(path, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            stage.setFullScreen(true);
            preferences.put("fullscreenkey","true");
            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#onToggleFullScreenPressed\" selected=\"false\"", "\"#onToggleFullScreenPressed\" selected=\"true\"");
                Files.write(path, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onToggleMusicPressed() {
        Path path = Paths.get(OptionsController.class.getResource("options.fxml").toExternalForm().replaceFirst("file:/", ""));
        Charset charset = StandardCharsets.UTF_8;

        if (!id_music_checkBox.isSelected()) {

            try {
                MusicController.stopMusic();
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#onToggleMusicPressed\" selected=\"true\"", "\"#onToggleMusicPressed\" selected=\"false\"");
                Files.write(path, content.getBytes(charset));
                preferences.put("musickey","false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                MusicController.playMusic();
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#onToggleMusicPressed\" selected=\"false\"", "\"#onToggleMusicPressed\" selected=\"true\"");
                Files.write(path, content.getBytes(charset));
                preferences.put("musickey","true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Preferences getPreferences() {
        return this.preferences;
    }
}