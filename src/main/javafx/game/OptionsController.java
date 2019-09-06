package game;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;


public class OptionsController {
    @FXML
    CheckBox fscheckbox;

    @FXML
    CheckBox id_music_checkBox;


    private static boolean toggledFullScreen;


    public CheckBox getFscheckbox() {
        return fscheckbox;
    }

    public void setFscheckbox(CheckBox fscheckbox) {
        this.fscheckbox = fscheckbox;
    }

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
        boolean isSelected = fscheckbox.isSelected();


        if (toggledFullScreen || !isSelected) {

            mainMenuStage.setFullScreen(false);
            setToggledFullScreen(false);
            System.out.println("deselectat");
            Path path = Paths.get("C:\\Users\\oprea\\Desktop\\Java\\proiectfinalhellapagos\\src\\main\\javafx\\game\\options.fxml");
            Charset charset = StandardCharsets.UTF_8;

            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#onToggleFullScreenPressed\" selected=\"true\"", "\"#onToggleFullScreenPressed\" selected=\"false\"");
                Files.write(path, content.getBytes(charset));



            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {

            mainMenuStage.setFullScreen(true);
            setToggledFullScreen(true);

            System.out.println("selectat");


            Path path = Paths.get("C:\\Users\\oprea\\Desktop\\Java\\proiectfinalhellapagos\\src\\main\\javafx\\game\\options.fxml");
            Charset charset = StandardCharsets.UTF_8;

            try {
                String content = new String(Files.readAllBytes(path));
                // next line changes the state back from "false" to "true"
                content = content.replaceAll("\"#onToggleFullScreenPressed\" selected=\"false\"", "\"#onToggleFullScreenPressed\" selected=\"true\"");
                Files.write(path, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    public void evaluateSettings() {
        if (isToggledFullScreen()) {
            fscheckbox.setSelected(true);
        }
    }

    static boolean isToggledFullScreen() {
        return toggledFullScreen;
    }

    private void setToggledFullScreen(boolean toggledFullScreen) {
        OptionsController.toggledFullScreen = toggledFullScreen;
    }


    public CheckBox getId_music_checkBox() {
        return id_music_checkBox;
    }


    public void musicHandler() {

        boolean isSelected = id_music_checkBox.isSelected();


        if (!isSelected) {

            System.out.println("Not selected");
            Path path = Paths.get("C:\\Users\\oprea\\Desktop\\Java\\proiectfinalhellapagos\\src\\main\\javafx\\game\\options.fxml");
            Charset charset = StandardCharsets.UTF_8;


            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#musicHandler\" selected=\"true\"", "\"#musicHandler\" selected=\"false\"");
                Files.write(path, content.getBytes(charset));

                MusicPlayer.stopMusic();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("selected");
            Path path = Paths.get("C:\\Users\\oprea\\Desktop\\Java\\proiectfinalhellapagos\\src\\main\\javafx\\game\\options.fxml");
            Charset charset = StandardCharsets.UTF_8;
            try {
                String content = new String(Files.readAllBytes(path));
                content = content.replaceAll("\"#musicHandler\" selected=\"false\"", "\"#musicHandler\" selected=\"true\"");
                Files.write(path, content.getBytes(charset));
              MusicPlayer.playMusic();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }







}