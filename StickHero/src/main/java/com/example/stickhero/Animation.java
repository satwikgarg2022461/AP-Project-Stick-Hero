package com.example.stickhero;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Animation {

    Label Score = new Label();



    Playable_Screen_Controller playableScreenController = new Playable_Screen_Controller();
    Graphics graphics = new Graphics();
    KeyEventHandler keyEventHandler = new KeyEventHandler();



    double stickHeight=9.6;
    private double scalingFactor = 1.0;
    double initialY = 490;

    public void elongateStickWithAnimation()
    {
//        double initialTranslateY = stick.getTranslateY()

        GlobalData.stick.getTransforms().add(new Scale(1, 1.1, 1, GlobalData.stick.getTranslateX(), GlobalData.stick.getBoundsInLocal().getMaxY(), GlobalData.stick.getTranslateZ()));
//        System.out.println("y "+ GlobalData.stick.getBoundsInLocal().getMaxY());
        stickHeight *= 1.1;
//        System.out.println("Final Y Coordinate after scaling: " + stickHeight);

    }

    public RotateTransition createDropAnimation()
    {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), GlobalData.stick);
        rotateTransition.setAxis(Rotate.Z_AXIS);
        rotateTransition.setByAngle(90);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
        return rotateTransition;
    }

    public void moveCharacter(Scene scene,ImageView hero, int hero_counter, double heroStartX, Group root,Label Score)
    {
        if(GlobalData.isMoveCharcter) {
            System.out.println("=============================");
            System.out.println("score before " + GlobalData.score);
            int spacing = (int) Math.round(GlobalData.spacingArrayList.get(GlobalData.score));
            int rectangleLenght = (int) Math.round(GlobalData.rectangleArrayList.get(GlobalData.score + 1).getWidth());
            int stickLength = (int) Math.round(stickHeight);
            System.out.println("spacing " + spacing);
            System.out.println("rectangle lenght " + rectangleLenght);
            System.out.println("Total length "+ (spacing+rectangleLenght));
            System.out.println("StickLength " + stickLength);
            if (spacing-8<= stickLength && stickLength <= rectangleLenght + spacing && GlobalData.isMoveCharcter) {
                GlobalData.score++;
                System.out.println(GlobalData.score);
                GlobalData.isMoveCharcter = false;
                System.out.println("mew");

                Timeline timeline = new Timeline();

                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), e -> {
                    Rectangle temp = GlobalData.rectangleArrayList.get(GlobalData.score-1);
                    System.out.println("temp X" +temp.getX());
                    System.out.println("transX" +GlobalData.transitionX);
                    System.out.println(temp.getWidth());
                    System.out.println(temp.getX()-GlobalData.transitionX+temp.getWidth()-64);
                    hero.setX(temp.getX()-GlobalData.transitionX + temp.getWidth()-64);
                    System.out.println(hero.getX());
                    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), hero);


                    translateTransition.setToX(spacing + rectangleLenght);
//                    translateTransition.setByX(spacing + rectangleLenght);
//                    translateTransition.setCycleCount(1);
                    translateTransition.setOnFinished(eventhhjhjj ->
                    {
                        Score.setText(""+GlobalData.score);
//                        initialize();
                        GlobalData.stickX = spacing + rectangleLenght;
                        Sound sound = new Sound();
                        sound.scoreSound();
                        stickHeight = 9.6;
                        GlobalData.isstickrotate = true;

                        root.getChildren().remove(GlobalData.stick);
                        moveBackBlocksAndCharacter(scene, hero, hero_counter, heroStartX, root,Score);

                    });
                    translateTransition.play();
                }));
                timeline.play();

            }
        }


    }

    public void moveBackBlocksAndCharacter(Scene scene,ImageView hero, int hero_counter, double heroStartX, Group root,Label Score){

        final int[] flag = {0};
        flag[0]=0;
        Rectangle firstbox = GlobalData.rectangleArrayList.get(GlobalData.score-1);
        int firstspace = (int)Math.round(GlobalData.spacingArrayList.get(GlobalData.score-1));
        double xdisplacement = firstspace + firstbox.getWidth();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->{
            for (Rectangle box: GlobalData.rectangleArrayList) {
//                double newX = box.getLayoutX()-xdisplacement;
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), box);
                translateTransition.setByX(-xdisplacement);
                translateTransition.play();
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(1), hero);
                translateTransition1.setByX(-xdisplacement);
                translateTransition1.play();
                translateTransition1.setOnFinished(eventCreateStick->
                {
                    if(flag[0] == 0)
                    {
                        keyEventHandler.setupArrowUpHandler(scene);
                        keyEventHandler.setupXHandler(scene, hero, hero_counter,heroStartX, root,Score);
                        flag[0]++;
                        System.out.println("----------------");
                        GlobalData.stick = graphics.createStick();
//                        System.out.println("hero x " +String.format("%.2f", GlobalData.stickX+firstspace+firstbox.getWidth()));
//                        System.out.println("hero width "+hero.getFitWidth());
                        System.out.println(GlobalData.rectangleArrayList.get(GlobalData.score));
                        System.out.println(xdisplacement);
                        Rectangle temp = GlobalData.rectangleArrayList.get(GlobalData.score);
                        GlobalData.transitionX = xdisplacement;
                        GlobalData.stick.setTranslateX(temp.getX() - xdisplacement + temp.getWidth()+7);
                        GlobalData.stick.setTranslateY(490);
                        GlobalData.stick.setTranslateZ(10);
                        root.getChildren().add(GlobalData.stick);
                        Rectangle delFirstRectangle = GlobalData.rectangleArrayList.get(GlobalData.score -1);
                        double delFirstspace = GlobalData.spacingArrayList.get(GlobalData.score -1);
                        GlobalData.totalRectangleLength -= delFirstRectangle.getWidth();
                        GlobalData.totalSpaceLength -= delFirstspace;
//                        GlobalData.rectangleArrayList.remove(delFirstRectangle);
//                        GlobalData.spacingArrayList.remove(delFirstspace);
                        playableScreenController.generateRectangle(root);
                        GlobalData.isMoveCharcter = true;
                    }
                });
            }

        }));
        timeline.play();
    }


}