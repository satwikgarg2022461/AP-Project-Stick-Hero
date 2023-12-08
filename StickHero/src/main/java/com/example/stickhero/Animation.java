package com.example.stickhero;

import javafx.animation.*;
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
    private boolean collisionEnabled = false;

    private ImageView oldcherry = null;
    private boolean increaseCherry = false;
    private boolean collisionRunning = false;
    private boolean cherryTaken = false;
    public void checkCollision(ImageView image1, ImageView image2,Group root){
        if (image1.getBoundsInParent().intersects(image2.getBoundsInParent())){
            System.out.println("got it");
            root.getChildren().remove(image2);
            GlobalData.cherryList.remove(GlobalData.cherryList.size()-1);
            GlobalData.cherrycount+=1;
            cherryTaken=true;
            collisionEnabled=false;
        }
    }



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
        AnimationTimer collisionTimer;
        if (GlobalData.cherryList.size()!=0) {
            collisionTimer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if (collisionEnabled) {
                        checkCollision(hero, GlobalData.cherryList.get(GlobalData.cherryList.size() - 1), root);
                    }
                }
            };
        } else {
            collisionTimer = null;
        }
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

                    collisionEnabled=true;
                    if (collisionTimer!=null){
                        collisionTimer.start();
                        collisionRunning=true;
                    }
                    scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                        if (keyEvent.getCode() == KeyCode.SPACE && GlobalData.isstickrotate) {
                            System.out.println("arrow up");

                            keyEvent.consume();
                        }
                    });

                    Rectangle temp = GlobalData.rectangleArrayList.get(GlobalData.score-1);
                    System.out.println("temp X" +temp.getX());
                    System.out.println("transX" +GlobalData.transitionX);
                    System.out.println(temp.getWidth());
                    System.out.println(temp.getX()-GlobalData.transitionX+temp.getWidth()-64);
                    hero.setX(temp.getX()-GlobalData.transitionX + temp.getWidth()-64);
                    System.out.println(hero.getX());
                    KeyEventHandler keyEventHandler1 = new KeyEventHandler();
                    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), hero);
                    scene.setOnKeyPressed(ev -> keyEventHandler1.handleKeyPress(ev.getCode(),hero));


                    translateTransition.setToX(spacing + rectangleLenght);
//                    translateTransition.setByX(spacing + rectangleLenght);
//                    translateTransition.setCycleCount(1);
                    translateTransition.setOnFinished(eventhhjhjj ->
                    {
                        if(collisionRunning) {
                            collisionRunning=false;
                            collisionEnabled = false;
                            collisionTimer.stop();
                        }
                        Score.setText(""+GlobalData.score);
//                        initialize();
                        GlobalData.stickX = spacing + rectangleLenght;
                        Sound sound = new Sound();
                        sound.scoreSound();
                        stickHeight = 9.6;
                        GlobalData.isstickrotate = true;

                        root.getChildren().remove(GlobalData.stick);
                        if (cherryTaken==false && GlobalData.cherryList.size()!=0) {
                            root.getChildren().remove(GlobalData.cherryList.get(GlobalData.cherryList.size() - 1));
                        }
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
                        System.out.println("cherry count :"+GlobalData.cherrycount);
                    }
                });
            }

        }));
        timeline.play();
    }


}