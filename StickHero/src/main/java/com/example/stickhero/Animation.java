package com.example.stickhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Animation {
    Playable_Screen_Controller playableScreenController = new Playable_Screen_Controller();
    Graphics graphicsStick = CreateStick.getCreateStickInstance();
    ResestGlobal resestGlobal = new ResestGlobal();
    
    KeyEventHandler keyEventHandler = new KeyEventHandler();

    private boolean collisionEnabled = false;

    private boolean collisionRunning = false;
    private boolean cherryTaken = false;
//    ---getter setter
    public boolean isCollisionEnabled() {
        return collisionEnabled;
    }

    public void setCollisionEnabled(boolean collisionEnabled) {
        this.collisionEnabled = collisionEnabled;
    }

    // Getter and Setter for collisionRunning
    public boolean isCollisionRunning() {
        return collisionRunning;
    }

    public void setCollisionRunning(boolean collisionRunning) {
        this.collisionRunning = collisionRunning;
    }

    // Getter and Setter for cherryTaken
    public boolean isCherryTaken() {
        return cherryTaken;
    }

    public void setCherryTaken(boolean cherryTaken) {
        this.cherryTaken = cherryTaken;
    }

    
    public void checkCollision(ImageView image1, ImageView image2,Group root){
        if (image1.getBoundsInParent().intersects(image2.getBoundsInParent())){
//            System.out.println("got it");
            root.getChildren().remove(image2);
            GlobalData.cherryList.remove(GlobalData.cherryList.size()-1);
            GlobalData.cherrycount+=1;
            cherryTaken=true;
            collisionEnabled=false;
        }
    }
    
    public void elongateStickWithAnimation()
    {
        GlobalData.stick.getTransforms().add(new Scale(1, 1.1, 1, GlobalData.stick.getTranslateX(), GlobalData.stick.getBoundsInLocal().getMaxY(), GlobalData.stick.getTranslateZ()));
        GlobalData.stickHeight*=1.1;
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



    public void moveCharacter(Scene scene,ImageView hero, int hero_counter, double heroStartX, Group root,Label Score,Label cherry,Stage stage)
    {

        if(GlobalData.isMoveCharcter) {
//            System.out.println("=============================");
//            System.out.println("score before " + GlobalData.score);
            int spacing = (int) Math.round(GlobalData.spacingArrayList.get(GlobalData.score));
            int rectangleLenght = (int) Math.round(GlobalData.rectangleArrayList.get(GlobalData.score + 1).getWidth());
            int stickLength = (int) Math.round(GlobalData.stickHeight);
//            System.out.println("spacing " + spacing);
//            System.out.println("rectangle lenght " + rectangleLenght);
//            System.out.println("Total length "+ (spacing+rectangleLenght));
//            System.out.println("StickLength " + stickLength);

            if (spacing-8<= stickLength && stickLength <= rectangleLenght + spacing && GlobalData.isMoveCharcter) {
//                System.out.println("bye");
//               ---- cherry collision
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
                }
                else
                {
                    collisionTimer = null;
                }


                GlobalData.score++;
                GlobalData.realScore++;
                GlobalData.isMoveCharcter = false;


                Timeline timeline = new Timeline();

                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->
                {

                    collisionEnabled=true;
                    if (collisionTimer!=null){
                        collisionTimer.start();
                        collisionRunning=true;
                    }

                    Rectangle temp = GlobalData.rectangleArrayList.get(GlobalData.score-1);
//                    System.out.println("temp rectangle "+temp);
//                    System.out.println("temp X" +temp.getX());
//                    System.out.println("transX" +GlobalData.transitionX);
//                    System.out.println("hero set x "+(temp.getX()-GlobalData.transitionX+temp.getWidth()-64));
                    hero.setX(temp.getX()-GlobalData.transitionX + temp.getWidth()-64);
//                    System.out.println(hero.getX());
                    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), hero);

                    translateTransition.setToX(spacing + rectangleLenght);

                    translateTransition.setOnFinished(eventHeroTransition ->
                    {
                        if(collisionRunning) {
                            collisionRunning=false;
                            collisionEnabled = false;
                            collisionTimer.stop();
                        }
                        Score.setText(""+GlobalData.realScore);
                        cherry.setText(GlobalData.cherrycount+" X");

                        GlobalData.stickX = spacing + rectangleLenght;
                        SoundFactory soundFactory = new SoundFactory();
                        Sound button = soundFactory.getSound("Score");
                        button.getSound();
                        GlobalData.stickHeight = 9.6;
                        GlobalData.isstickrotate = true;
                        root.getChildren().remove(GlobalData.stick);
                        if (cherryTaken==false && GlobalData.cherryList.size()!=0) {
                            root.getChildren().remove(GlobalData.cherryList.get(GlobalData.cherryList.size() - 1));
                        }
                        else {
                            cherryTaken = false;
                        }
                        moveBackBlocksAndCharacter(scene, hero, hero_counter, heroStartX, root,Score,cherry,stage);
                    });
                    translateTransition.play();
                }));
                timeline.play();
            }
            else {
//                System.out.println("hi");
                int index =0;
                if(GlobalData.score>0)
                {
                    index = GlobalData.score;
                }
                Rectangle temp = GlobalData.rectangleArrayList.get(index);
                hero.setX(temp.getX()-GlobalData.transitionX + temp.getWidth()-44);
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), hero);
//                System.out.println("stick length"+stickLength);
                translateTransition.setByX(stickLength +5);
                translateTransition.setOnFinished(eventHeroFall ->
                {
                    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2),hero);
                    SoundFactory soundFactory = new SoundFactory();
                    Sound button = soundFactory.getSound("Fall");
                    button.getSound();
                    translateTransition1.setToY(600);
                    translateTransition1.setOnFinished(eventPauseScreen -> {
                        try {
                            switchToExit(hero,scene);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    translateTransition1.play();

                });
                translateTransition.play();

            }
        }


    }

    public void moveBackBlocksAndCharacter(Scene scene,ImageView hero, int hero_counter, double heroStartX, Group root,Label Score,Label cherry,Stage stage){

        final int[] flag = {0};
        flag[0]=0;
        Rectangle firstbox = GlobalData.rectangleArrayList.get(GlobalData.score-1);
        int firstspace = (int)Math.round(GlobalData.spacingArrayList.get(GlobalData.score-1));
        double xdisplacement = firstspace + firstbox.getWidth();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->{
            for (Rectangle box: GlobalData.rectangleArrayList) {
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
                        keyEventHandler.setupXHandler(scene, hero, hero_counter,heroStartX, root,Score,cherry,stage);
                        flag[0]++;
//                        System.out.println("----------------");
                        GlobalData.stick = graphicsStick.createStick();
//                        System.out.println(GlobalData.rectangleArrayList.get(GlobalData.score));
//                        System.out.println(xdisplacement);
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
                        playableScreenController.generateRectangle(root);
                        GlobalData.isMoveCharcter = true;
//                        System.out.println("cherry count :"+GlobalData.cherrycount);
                    }
                });
            }

        }));
        timeline.play();
    }

    public void switchToExit(ImageView hero,Scene scene) throws IOException {
        Group root_pause = null;
        try {
            root_pause = FXMLLoader.load(getClass().getResource("exit_screen.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene_pause = new Scene(root_pause);

// Create a new stage
        Stage stage_pause = null;
        try {
            stage_pause = (Stage) GlobalData.scene.getWindow();
        }
        catch (Exception e)
        {
            System.out.print("");
        }
        

        Label score = new Label();
        Label cherry = new Label();
        score.setLayoutX(348);
        score.setLayoutY(251);
//        System.out.println(GlobalData.score);
        score.setText(GlobalData.realScore + "");
        score.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-font-family: Arial");
        root_pause.getChildren().add(score);

        cherry.setLayoutX(368);
        cherry.setLayoutY(291);
        cherry.setText(GlobalData.cherrycount + "");
        cherry.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-font-family: Arial");
        root_pause.getChildren().add(cherry);

// Set the scene for the new stage
        try
        {
            stage_pause.setScene(scene_pause);
        }
        catch (Exception e)
        {
            System.out.print("");
        }


// Show the new stage
        try {
            stage_pause.show();
        }
        catch (Exception e)
        {
            System.out.print("");
        }


    }


}