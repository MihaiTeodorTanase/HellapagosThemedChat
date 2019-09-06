package game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicController {

    private static MediaPlayer mediaPlayer;

    static MediaPlayer playMusic() {
        Media musicFile = new Media(MusicController.class.getResource("music/HellapagosMusic.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.4D);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        return mediaPlayer;
    }

    static void stopMusic() {
        mediaPlayer.stop();
    }
}
