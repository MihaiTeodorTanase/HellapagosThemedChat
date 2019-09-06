package game;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;


public class Main extends Application {


    @Override
    public void start(final Stage primaryStage) throws Exception {
            OptionsController optionsController=new OptionsController();


        primaryStage.setTitle("Hellapagos Game");
        OverallController.loadMainMenu(primaryStage);
        primaryStage.show();
        MusicPlayer.playMusic();




    }




    public static void main(String[] args) {
        launch(args);
    }


}
