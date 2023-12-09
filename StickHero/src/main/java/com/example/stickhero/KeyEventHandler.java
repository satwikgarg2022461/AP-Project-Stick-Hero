package com.example.stickhero;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.RotateTransition;

public class KeyEventHandler {

    private boolean isFlipped = false;
    static Animation animation = new Animation();
    public void setupArrowUpHandler(Scene scene) {
        //elongating stick by pressing space key
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE && GlobalData.isstickrotate) {
                animation.elongateStickWithAnimation();
                keyEvent.consume();
            }
        });
    }

    public void setupFlip(Scene scene,ImageView image,Group root) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) {
            // Toggle the flip state
                isFlipped = !isFlipped;

                // Adjust the direction of the translation based on the flip state
                if (isFlipped) {
                    // Flip horizontally
                    image.setScaleY(-1);
                    image.setTranslateY(image.getTranslateY() + image.getFitHeight());
                    // Adjust the direction for the flip
                } else {
                    // Reset to normal orientation
                    image.setScaleY(1);
                    image.setTranslateY(image.getTranslateY() - image.getFitHeight());
                    // Reset the direction for normal motion
                }
            }
        });
    }

    public void setupXHandler(Scene scene, ImageView imageview, int hero_counter, double heroStartX, Group root, Label Score, Label cherry, Stage stage)
    {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, dropStick -> {

            if (dropStick.getCode() == KeyCode.X && GlobalData.isMoveCharcter) {
                RotateTransition rotateTransition = animation.createDropAnimation();
                rotateTransition.setOnFinished(eventStickRotate -> {
                    GlobalData.isstickrotate = false;
                    animation.moveCharacter(scene,imageview, hero_counter, heroStartX,root,Score,cherry,stage);
                });
            }
        });
    }
}
