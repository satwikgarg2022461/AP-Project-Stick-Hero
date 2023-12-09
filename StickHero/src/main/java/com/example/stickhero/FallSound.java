package com.example.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FallSound implements Sound{
    @Override
    public void getSound() {
        Media buttonSound = new Media(getClass().getResource("sounds/fall.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }
}