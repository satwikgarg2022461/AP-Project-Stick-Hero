package com.example.stickhero;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Playable_Screen_Controller implements Serializable {
    public static final long serialVersionUID = 41L;


    Graphics graphicsRectangle = CreateRectangle.getInstance();
    Graphics GraphicsStick = CreateStick.getCreateStickInstance();
    Cherry cherry = new Cherry();
    Random_generator random_generator = new Random_generator();
    //    Animation animation = new Animation();
    KeyEventHandler keyEventHandler = new KeyEventHandler();
    double startY = 488;
    Color customColor = Color.valueOf("#795234");
    private Image image;

    private ImageView imageview;


    @FXML
    private Button pause;

    private Stage stage;
    private Scene scene;
    private Group root;

    private Scale scale;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setRoot(Group root) {
        this.root = root;
    }


   // Getter methods
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
        SoundFactory soundFactory = new SoundFactory();
        Sound button = soundFactory.getSound("Button");
        button.getSound();
        Parent root_pause = FXMLLoader.load(getClass().getResource("exit_screen.fxml"));
        Stage stage_pause = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_pause = new Scene(root_pause);
        stage_pause.setScene(scene_pause);
        stage_pause.show();
    }

    public void generateRectangle(Group root)
    {
//        keyEventHandler.setupFlip(scene,imageview,root);
        while (GlobalData.totalRectangleLength + GlobalData.totalSpaceLength < 180){
//            System.out.println("hi");
            if(GlobalData.counter == 0)
            {
                Rectangle firstBox = graphicsRectangle.createRectangle(200,startY,87, 70,customColor);
//                System.out.println(firstBox);
                root.getChildren().add(firstBox);
                GlobalData.rectangleArrayList.add(firstBox);
                GlobalData.totalRectangleLength += firstBox.getWidth();
            }
            else
            {
                double rectangleWidth = random_generator.getRandomWidth();
                double rectangleHeight = 70;
                double spacing = random_generator.getRandomSpacing();
                GlobalData.spacingArrayList.add(spacing);
//                System.out.println("spacing "+spacing);
                GlobalData.totalSpaceLength += spacing;

                cherry.generateCherry(root);

                Rectangle box = graphicsRectangle.createRectangle(+200+GlobalData.totalRectangleLength + GlobalData.totalSpaceLength,startY,rectangleWidth, rectangleHeight, customColor);
                GlobalData.totalRectangleLength += rectangleWidth;

//                System.out.println("box "+box);
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

        Label cherry = new Label();
        cherry.setLayoutX(556);
        cherry.setLayoutY(28);
        cherry.setStyle("-fx-font-family: Arial; -fx-font-size: 14; -fx-font-weight: bold;");
        cherry.setText(GlobalData.cherrycount+" X");
        cherry.setTextFill(Color.valueOf("#f25b7d"));
        root.getChildren().add(cherry);


        Label Score = new Label();
        Score.setLayoutX(315);
        Score.setLayoutY(129);
        Score.setText(""+GlobalData.realScore);
        Score.setStyle("-fx-font-size: 29; -fx-font-weight: bold;");
        root.getChildren().add(Score);

//        --- initialising X and Y coordinate of rectangle and hero
        double heroStartX = 209;
        double heroStartY = 387;

//        ----  image of the hero
        image = new Image(getClass().getResourceAsStream("images/hero11.png"));
        imageview = new ImageView(image);
        imageview.setFitWidth(100);
        imageview.setFitHeight(100);
        imageview.setX(heroStartX+10);
        imageview.setY(heroStartY);
        root.getChildren().add(imageview);

//        ---- stick creation
        GlobalData.stick = GraphicsStick.createStick();
        GlobalData.stick.setTranslateX(290);
        GlobalData.stick.setTranslateY(490);
        GlobalData.stick.setTranslateZ(10);
        root.getChildren().add(GlobalData.stick);

        //      ---- producing rectangle
        generateRectangle(root);
        keyEventHandler.setupFlip(scene,imageview,root);
        keyEventHandler.setupArrowUpHandler(scene);
        GlobalData.scene = scene;
        keyEventHandler.setupXHandler(scene, imageview, hero_counter,heroStartX, root,Score,cherry,stage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void SaveButton(ActionEvent event) throws IOException {
        System.out.println("save button");
        ObjectOutputStream out =null;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(GlobalData.realScore);
        arr.add(GlobalData.cherrycount);
        try {
            System.out.println("try");
            out = new ObjectOutputStream(new FileOutputStream("load.txt"));
            out.writeObject(arr);
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            System.out.println("No previous game record");
        }
        finally {
            if(out!=null)
            {
                out.close();
            }
        }
    }

}