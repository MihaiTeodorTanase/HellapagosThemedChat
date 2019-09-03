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
import java.net.URI;


public class Main extends Application {


    @Override
    public void start(final Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hellapagos Game");
        OverallController.loadMainMenu(primaryStage);
        playMusic();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public MediaPlayer playMusic(){

MediaPlayer mediaPlayer;


        Media musicFile=new Media("file:///C:/Users/oprea/Desktop/Java/proiectfinalhellapagos/src/main/resources/HellapagosMusic.mp3");
        mediaPlayer=new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.4D);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });



return mediaPlayer;

    }







}
