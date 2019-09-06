package game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {

    static MediaPlayer mediaPlayer;


    public static MediaPlayer playMusic() {


        Media musicFile = new Media("file:///C:/Users/oprea/Desktop/Java/proiectfinalhellapagos/src/main/resources/HellapagosMusic.mp3");
        mediaPlayer = new MediaPlayer(musicFile);
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

    public static void stopMusic() {
         mediaPlayer.stop();

    }




}
