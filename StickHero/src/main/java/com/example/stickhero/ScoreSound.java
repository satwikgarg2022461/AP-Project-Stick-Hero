package com.example.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ScoreSound implements Sound{
    @Override
    public void getSound() {
        Media buttonSound = new Media(getClass().getResource("sounds/victory.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }
}