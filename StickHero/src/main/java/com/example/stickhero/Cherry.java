package com.example.stickhero;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Cherry {
    Random_generator randomGenerator = new Random_generator();
    public void generateCherry(Group root)
    {
        Rectangle temp = GlobalData.rectangleArrayList.get(GlobalData.score);
        Double spacing =GlobalData.spacingArrayList.get(GlobalData.score);
        if (randomGenerator.randomInt()%2==0){

            Image image = new Image(getClass().getResourceAsStream("images/cherry.png"));
            ImageView cherry = new ImageView(image);
            cherry.setFitHeight(37);
            cherry.setFitWidth(37);
            cherry.setX(randomGenerator.generateRandomDouble(temp.getX()-GlobalData.transitionX+temp.getWidth(),temp.getX()-GlobalData.transitionX+temp.getWidth()+spacing - 40));
            cherry.setY(507);
            GlobalData.cherryList.add(cherry);
            root.getChildren().add(cherry);
        }
    }
}
