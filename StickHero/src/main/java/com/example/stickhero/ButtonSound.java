package com.example.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ButtonSound implements Sound{
    @Override
    public void getSound() {
        Media buttonSound = new Media(getClass().getResource("sounds/button.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }
}