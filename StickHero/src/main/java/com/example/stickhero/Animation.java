package com.example.stickhero;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;


public class Animation {

    double transformedY;
    public void elongateStickWithAnimation(Box stick)
    {
        double currentTranslateY = stick.getTranslateY();
        stick.getTransforms().add(new Scale(1, 1.1, 1, stick.getTranslateX(), stick.getBoundsInLocal().getMaxY(), stick.getTranslateZ()));
        stick.setTranslateY(currentTranslateY);
        transformedY = 480- stick.localToScene(0, 0).getY() ;
    }

    public void createDropAnimation(Box stick)
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), stick);
        rotateTransition.setAxis(Rotate.Z_AXIS);
        rotateTransition.setByAngle(90);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }

    public boolean moveCharacter(ImageView hero, int hero_counter, double heroStartX, Box stick)
    {
        int spacing = (int)Math.round(GlobalData.spacingArrayList.get(hero_counter));
        int rectangleLenght = (int)Math.round(GlobalData.rectangleArrayList.get(hero_counter+1).getWidth());
        int stickLength = (int)Math.round(transformedY);
        System.out.println("spacing "+spacing);
        System.out.println("rectangle lenght "+rectangleLenght);
        System.out.println("StickLength "+stickLength);
        if(spacing <= stickLength && stickLength <= rectangleLenght+spacing)
        {
            System.out.println("hi");
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), hero);
            translateTransition.setToX(stickLength+100);
            translateTransition.play();
            return true;
        }
        return false;
    }
}
