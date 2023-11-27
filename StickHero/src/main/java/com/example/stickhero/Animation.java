package com.example.stickhero;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;


public class Animation {
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
        System.out.println("Final Y Coordinate after scaling: " + stickHeight);

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

    public void moveCharacter(Scene scene,ImageView hero, int hero_counter, double heroStartX, Group root)
    {
//        final TranslateTransition[] translateTransition = new TranslateTransition[1];
        int spacing = (int)Math.round(GlobalData.spacingArrayList.get(hero_counter));
        int rectangleLenght = (int)Math.round(GlobalData.rectangleArrayList.get(hero_counter+1).getWidth());
        int stickLength = (int)Math.round(stickHeight);
        System.out.println("spacing "+spacing);
        System.out.println("rectangle lenght "+rectangleLenght);
        System.out.println("StickLength "+stickLength);
        if(spacing <= stickLength && stickLength <= rectangleLenght+spacing && GlobalData.isMoveCharcter)
        {
            GlobalData.isMoveCharcter = false;
            System.out.println("hi from move chracter");
            Timeline timeline = new Timeline();
            System.out.println("meow1");
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5),e ->{
                System.out.println("meow2");
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), hero);
                System.out.println("meow3");
                translateTransition.setToX(spacing+rectangleLenght);
                System.out.println("Meow 4");
                translateTransition.setOnFinished(eventhhjhjj ->
                {
                    System.out.println("meow5");
                    stickHeight = 9.6;
                    GlobalData.isstickrotate=true;
                    System.out.println("meow6");
                    root.getChildren().remove(GlobalData.stick);
                    moveBackBlocksAndCharacter(scene,hero,hero_counter,heroStartX, root);
                    System.out.println("check");
                });
                translateTransition.play();
            }));
            timeline.play();

        }

    }

    public void moveBackBlocksAndCharacter(Scene scene,ImageView hero, int hero_counter, double heroStartX, Group root){

        final int[] flag = {0};
        flag[0]=0;
        Rectangle firstbox = GlobalData.rectangleArrayList.get(0);
        int firstspace = (int)Math.round(GlobalData.spacingArrayList.get(0));
        double xdisplacement = firstspace + firstbox.getWidth();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->{
            for (Rectangle box: GlobalData.rectangleArrayList) {
                double newX = box.getLayoutX()-xdisplacement;
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
                        keyEventHandler.setupXHandler(scene, hero, hero_counter,heroStartX, root);
                        flag[0]++;
                        System.out.println("jfyjfyjyujukfu");
                        GlobalData.stick=graphics.createStick();
//                        System.out.println("hero x "+hero.getX());
//                        System.out.println("hero width "+hero.getFitWidth());
                        GlobalData.stick.setTranslateX(GlobalData.rectangleArrayList.get(1).getWidth()+4);
                        GlobalData.stick.setTranslateY(490);
                        GlobalData.stick.setTranslateZ(10);
                        root.getChildren().add(GlobalData.stick);
                        Rectangle delFirstRectangle = GlobalData.rectangleArrayList.get(0);
                        double delFirstspace = GlobalData.spacingArrayList.get(0);
                        GlobalData.totalRectangleLength -= delFirstRectangle.getWidth();
                        GlobalData.totalSpaceLength -= delFirstspace;
                        GlobalData.rectangleArrayList.remove(delFirstRectangle);
                        GlobalData.spacingArrayList.remove(delFirstspace);
                        playableScreenController.generateRectangle(root);
                        GlobalData.isMoveCharcter = true;
                    }
                });
            }

        }));
        timeline.play();
    }
}
