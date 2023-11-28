package com.example.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    public void buttonSound(){
        Media buttonSound = new Media(getClass().getResource("sounds/button.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }
    public void fallSound(){
        Media buttonSound = new Media(getClass().getResource("sounds/fall.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }
    public void scoreSound(){
        Media buttonSound = new Media(getClass().getResource("sounds/victory.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
        mediaPlayer.play();
    }

}
