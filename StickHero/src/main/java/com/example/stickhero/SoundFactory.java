package com.example.stickhero;

public class SoundFactory {
    public Sound getSound(String soundName){
        if (soundName == null){
            return null;
        }
        else if(soundName.equalsIgnoreCase("Button")){
            return new ButtonSound();
        }
        else if(soundName.equalsIgnoreCase("Fall")){
            return new FallSound();
        }
        else if(soundName.equalsIgnoreCase("Score")){
            return new ScoreSound();
        }
        else{
            return null;
        }
    }
}