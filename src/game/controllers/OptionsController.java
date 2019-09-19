package game.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
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

    private Preferences preferences = Preferences.userNodeForPackage(this.getClass());
    private static OptionsController instance = null;
    private StringBuilder sbForVolume;

    private OptionsController() {
    }

    public static OptionsController getInstance() {
        if (instance == null) {
            instance = new OptionsController();
        }
        return instance;
    }

    void loadOptionsScreen(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("options.fxml"));
        loader.setControllerFactory(controllerType -> getInstance());
        Parent optionsView = loader.load();
        stage.setScene(new Scene(optionsView));
        OverallController.loadPreferences(stage);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::saveVolumeSliderState);
    }

    @FXML
    public void initialize() {
        fsCheckbox.setSelected(preferences.getBoolean("fullscreenkey", false));
        musicCheckbox.setSelected(preferences.getBoolean("musickey", true));
        volumeSlider.setValue(preferences.getDouble("musicvolumekey", 100.0));
        volumeLabel.setText(preferences.get("volumelabelkey", "100"));
    }

    public void onBackPressed(ActionEvent event) throws IOException {
        saveVolumeSliderState();
        OverallController.loadMainMenu((Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    public void onToggleFullScreenPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean isChecked = fsCheckbox.isSelected();
        if (!isChecked) {
            preferences.put("fullscreenkey", "false");
            stage.setFullScreen(false);
        } else {
            preferences.put("fullscreenkey", "true");
            stage.setFullScreen(true);
        }
    }

    public void onToggleMusicPressed() {
        if (!musicCheckbox.isSelected()) {
            OverallController.stopMusic();
            preferences.putBoolean("musickey", false);
        } else {
            OverallController.playMusic();
            preferences.putBoolean("musickey", true);
        }
    }

    public void setMusicPlayerVolume() {
        sbForVolume = new StringBuilder();
        volumeSlider.valueProperty().addListener(observable -> {
            OverallController.getMusicController().getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
            volumeLabel.setText("" + (int) volumeSlider.getValue());
            sbForVolume.setLength(0);
            sbForVolume.append(volumeSlider.getValue());
        });
    }

    public void saveVolumeSliderState() {
        if (sbForVolume != null) {
            preferences.putDouble("musicvolumekey", Double.valueOf(sbForVolume.toString()));
            System.out.println("Double value of: " + sbForVolume.toString());
            preferences.put("volumelabelkey", String.valueOf(Double.valueOf(sbForVolume.toString()).intValue()));
            sbForVolume = null;
        }
    }

    private <T extends Event> void saveVolumeSliderState(T t) {
        saveVolumeSliderState();
    }

    public Preferences getPreferences() {
        return this.preferences;
    }
}