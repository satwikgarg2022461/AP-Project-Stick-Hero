package com.example.stickhero;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Box;

public class Playable_Screen_Controller {

    @FXML
    private Button pause;

    private Stage stage, stage_pause;
    private Scene scene, scene_pause;
    private Group root;
    private Parent root_pause;
    private Box stick;
    private Scale scale;

    //
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    //
//
//    // Getter methods
    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Group getRoot() {
        return root;
    }



    boolean isstickrotate = true;

    public void switchToPause(ActionEvent event) throws IOException {
        root_pause = FXMLLoader.load(getClass().getResource("exit_screen.fxml"));
        stage_pause = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_pause = new Scene(root_pause);
        stage_pause.setScene(scene_pause);
        stage_pause.show();
    }

    public void generate_scene(ActionEvent event) throws IOException {

        int hero_counter = 0;
        GlobalData.totalRectangleLength = 0;
        GlobalData.totalSpaceLength = 0;
        GlobalData.rectangleArrayList = new ArrayList<>();
        GlobalData.spacingArrayList = new ArrayList<>();

        Graphics graphics = new Graphics();
        Random_generator random_generator = new Random_generator();
        Animation animation = new Animation();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("playable_screen.fxml"));
        root = loader.load();
        scene = new Scene(root);

//        --- initialising X and Y coordinate of rectangle and hero
        double startY = 488;
        double heroStartX = 0;
        double heroStartY = 387;
//        ----  image of the hero
        Image image = new Image(getClass().getResourceAsStream("images/hero11.png"));
        ImageView imageview = new ImageView(image);
        imageview.setFitWidth(100);
        imageview.setFitHeight(100);
        imageview.setX(heroStartX);
        imageview.setY(heroStartY);
        root.getChildren().add(imageview);
//        ---- stick creation
        stick = graphics.createStick();
        stick.setTranslateX(90);
        stick.setTranslateY(490);
        stick.setTranslateZ(10);
        root.getChildren().add(stick);
        Color customColor = Color.valueOf("#795234");


        //      ---- producing rectangle
        int counter = 0;
        while (GlobalData.totalRectangleLength + GlobalData.totalSpaceLength < 350){
//            System.out.println(counter+"  "+(GlobalData.totalRectangleLength+GlobalData.totalSpaceLength));
            if(counter == 0)
            {
                Rectangle firstBox = graphics.createRectangle(87, 70,customColor);
                firstBox.setLayoutX(0);
                firstBox.setLayoutY(startY);
                root.getChildren().add(firstBox);
                GlobalData.rectangleArrayList.add(firstBox);
                GlobalData.totalRectangleLength += firstBox.getWidth();
            }
            else
            {
                double rectangleWidth = random_generator.getRandomWidth();
                double rectangleHeight = 70;
                double spacing = random_generator.getRandomSpacing();
//                System.out.println(counter);
//                System.out.println("width "+rectangleWidth);
//                System.out.println("spacing "+spacing);
                GlobalData.spacingArrayList.add(spacing);
                Rectangle box = graphics.createRectangle(rectangleWidth, rectangleHeight, customColor);
                GlobalData.totalSpaceLength += spacing;
                for (Rectangle rectangle : GlobalData.rectangleArrayList)
                {
//                    System.out.println("total rectangle length "+GlobalData.totalRectangleLength);
//                    System.out.println("total spacing "+GlobalData.totalSpaceLength);
//                    System.out.println("rectangle length "+box.getWidth());
                    GlobalData.totalRectangleLength += box.getWidth();
                }
//                System.out.println("after total rectangle length "+GlobalData.totalRectangleLength);
//                System.out.println("after total spacing "+GlobalData.totalSpaceLength);


                box.setLayoutX(GlobalData.totalRectangleLength + GlobalData.totalSpaceLength);
                box.setLayoutY(startY);
                root.getChildren().add(box);
                GlobalData.rectangleArrayList.add(box);
            }
            counter++;
        }

//        elonating stick by pressing space key
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE && isstickrotate) {
                animation.elongateStickWithAnimation(stick);
                keyEvent.consume();
            }
        });

//          rotating stick by pressing X
        scene.addEventFilter(KeyEvent.KEY_PRESSED, dropStick -> {
            if (dropStick.getCode() == KeyCode.X) {
                animation.createDropAnimation(stick);
                isstickrotate = false;
                dropStick.consume();
                animation.moveCharacter(imageview, hero_counter, heroStartX, stick);
                animation.moveBackBlocksAndCharacter(imageview, hero_counter, heroStartX);


//                hero_counter++;
            }
        });

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
