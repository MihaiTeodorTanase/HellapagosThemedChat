package game.controllers;

import game.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicController {

    private MediaPlayer mediaPlayer;

    public MusicController() {
        Media musicFile = new Media(Main.class.getResource("resources/music/HellapagosMusic.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(Double.parseDouble(new OptionsController().getPreferences().get("musicvolumekey","1")));
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }

    void playMusic() {
        mediaPlayer.play();
    }

    void stopMusic() {
        mediaPlayer.stop();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

