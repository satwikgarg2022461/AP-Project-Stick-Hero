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
import javafx.scene.control.Label;
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

public class Playable_Screen_Controller  {

    //    int counter = 0;
    Graphics graphics = new Graphics();
    Random_generator random_generator = new Random_generator();
    //    Animation animation = new Animation();
    KeyEventHandler keyEventHandler = new KeyEventHandler();
    double startY = 488;
    Color customColor = Color.valueOf("#795234");


    @FXML
    private Button pause;

    private Stage stage, stage_pause;
    private Scene scene, scene_pause;
    private Group root;
    private Parent root_pause;
    //    private Box stick;
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





    public void switchToPause(ActionEvent event) throws IOException {
        Sound sound = new Sound();
        sound.buttonSound();
        root_pause = FXMLLoader.load(getClass().getResource("exit_screen.fxml"));
        stage_pause = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_pause = new Scene(root_pause);
        stage_pause.setScene(scene_pause);
        stage_pause.show();
    }

    public void generateRectangle(Group root)
    {
        while (GlobalData.totalRectangleLength + GlobalData.totalSpaceLength < 180){
//            System.out.println(counter+"  "+(GlobalData.totalRectangleLength+GlobalData.totalSpaceLength));
            if(GlobalData.counter == 0)
            {
                System.out.println("hi");
                Rectangle firstBox = graphics.createRectangle(200,startY,87, 70,customColor);
                System.out.println(firstBox);
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

                GlobalData.spacingArrayList.add(spacing);


//                System.out.println("initail width "+rectangleWidth);
//                System.out.println("initial spacing "+spacing);
                GlobalData.totalSpaceLength += spacing;


//                System.out.println("Total space length "+GlobalData.totalSpaceLength);
                Rectangle box = graphics.createRectangle(+200+GlobalData.totalRectangleLength + GlobalData.totalSpaceLength,startY,rectangleWidth, rectangleHeight, customColor);
                GlobalData.totalRectangleLength += rectangleWidth;
//                System.out.println("Total Rectangle length "+GlobalData.totalRectangleLength);
//                box.setLayoutX(GlobalData.totalRectangleLength + GlobalData.totalSpaceLength);
//                box.setLayoutY(startY);
                System.out.println("box "+box);
                root.getChildren().add(box);
                GlobalData.rectangleArrayList.add(box);
            }
            GlobalData.counter++;
        }
    }

    public void generate_scene(ActionEvent event) throws IOException {




        int hero_counter = 0;





        FXMLLoader loader = new FXMLLoader(getClass().getResource("playable_screen.fxml"));
        root = loader.load();
        setRoot(root);
        scene = new Scene(root);
//        Score.setText("1");
        Label Score = new Label();
        Score.setLayoutX(315);
        Score.setLayoutY(129);
        Score.setText("0");
        Score.setStyle("-fx-font-size: 29; -fx-font-weight: bold;");
        root.getChildren().add(Score);

//        --- initialising X and Y coordinate of rectangle and hero

        double heroStartX = 209;
        double heroStartY = 387;
//        ----  image of the hero
        Image image = new Image(getClass().getResourceAsStream("images/hero11.png"));
        ImageView imageview = new ImageView(image);
        imageview.setFitWidth(100);
        imageview.setFitHeight(100);
        imageview.setX(heroStartX+10);
        imageview.setY(heroStartY);
        root.getChildren().add(imageview);
//        ---- stick creation
        GlobalData.stick = graphics.createStick();
        GlobalData.stick.setTranslateX(290);
        GlobalData.stick.setTranslateY(490);
        GlobalData.stick.setTranslateZ(10);
        root.getChildren().add(GlobalData.stick);



        //      ---- producing rectangle
        generateRectangle(root);



        keyEventHandler.setupArrowUpHandler(scene);
        keyEventHandler.setupXHandler(scene, imageview, hero_counter,heroStartX, root,Score);







        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}