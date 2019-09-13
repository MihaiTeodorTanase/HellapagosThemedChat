package game.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
    CheckBox fsCheckbox;
    @FXML
    CheckBox musicCheckbox;
    @FXML
    Slider volumeSlider;
    @FXML
    Label volumeLabel;

    public Preferences preferences = Preferences.userNodeForPackage(this.getClass());


    void loadOptionsScreen(Stage optionsStage) throws IOException {
        Parent optionsView = FXMLLoader.load(OptionsController.class.getResource("options.fxml"));
        optionsStage.setScene(new Scene(optionsView));
        OverallController.loadPreferences(optionsStage);
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        OverallController.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public void onToggleFullScreenPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean isChecked = fsCheckbox.isSelected();
        Path path = Paths.get(OptionsController.class.getResource("options.fxml").toExternalForm().replaceFirst("file:/", ""));
        Charset charset = StandardCharsets.UTF_8;

        if (!isChecked) {
            stage.setFullScreen(false);
            preferences.put("fullscreenkey","false");
            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("selected=\"true\" styleClass=\"checkboxFS\"", "selected=\"false\" styleClass=\"checkboxFS\"");
                Files.write(path, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            stage.setFullScreen(true);
            preferences.put("fullscreenkey","true");
            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("selected=\"false\" styleClass=\"checkboxFS\"", "selected=\"true\" styleClass=\"checkboxFS\"");
                Files.write(path, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onToggleMusicPressed() {
        Path path = Paths.get(OptionsController.class.getResource("options.fxml").toExternalForm().replaceFirst("file:/", ""));
        Charset charset = StandardCharsets.UTF_8;

        if (!musicCheckbox.isSelected()) {

            try {
                OverallController.stopMusic();
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("selected=\"true\" styleClass=\"checkboxM\"", "selected=\"false\" styleClass=\"checkboxM\"");
                Files.write(path, content.getBytes(charset));
                preferences.put("musickey","false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                OverallController.playMusic();
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("selected=\"false\" styleClass=\"checkboxM\"", "selected=\"true\" styleClass=\"checkboxM\"");
                Files.write(path, content.getBytes(charset));
                preferences.put("musickey","true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMusicPlayerVolume(){
        volumeSlider.valueProperty().addListener(observable -> {
            OverallController.getMusicController().getMediaPlayer().setVolume(volumeSlider.getValue()/100);
            volumeLabel.setText(""+(int)volumeSlider.getValue());
            preferences.put("musicvolumekey",""+volumeSlider.getValue()/100);
            try {
                modifyRootOfSlider();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void modifyRootOfSlider() throws IOException {
        Path path = Paths.get(OptionsController.class.getResource("options.fxml").toExternalForm().replaceFirst("file:/", ""));
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path));
        String regex = "(value=\"\\d*\\.?\\d*\")";
        String replacement = "value=\""+volumeSlider.getValue()+"\"";
        content = content.replaceAll(regex, replacement);
        String regexForLabel = "(text=\"\\d*\\.?\\d*\")";
        String replacementForLabel = "text=\""+(int)volumeSlider.getValue()+"\"";
        content = content.replaceAll(regexForLabel,replacementForLabel);
        Files.write(path, content.getBytes(charset));
    }

    public Preferences getPreferences() {
        return this.preferences;
    }
}