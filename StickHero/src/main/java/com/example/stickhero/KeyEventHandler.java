package com.example.stickhero;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler {
//    static boolean isstickrotate = true;
    static Animation animation = new Animation();
    public void setupArrowUpHandler(Scene scene) {
        //elongating stick by pressing space key
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE && GlobalData.isstickrotate) {
//                System.out.println("arrow up");
                animation.elongateStickWithAnimation();
                keyEvent.consume();
            }
        });
    }

    public void setupXHandler(Scene scene, ImageView imageview, int hero_counter, double heroStartX, Group root,Label Score)
    {
        //          rotating stick by pressing X
        scene.addEventFilter(KeyEvent.KEY_PRESSED, dropStick -> {

            if (dropStick.getCode() == KeyCode.X && GlobalData.isMoveCharcter) {
                RotateTransition rotateTransition = animation.createDropAnimation();
                rotateTransition.setOnFinished(eventStickRotate -> {
                    GlobalData.isstickrotate = false;
//                    GlobalData.isMoveCharcter=false;
                    animation.moveCharacter(scene,imageview, hero_counter, heroStartX,root,Score);
                });
            }
        });
    }
}
