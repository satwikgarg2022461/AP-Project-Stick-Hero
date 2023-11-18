package com.example.stickhero;


import java.io.IOException;
import java.net.URL;
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

public class Playable_Screen_Controller implements Initializable {

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


    }

    double stickInitialY = 480;
    double upwardDistance = 10;



    private void elongateStickWithAnimation() {
        if (stick.getHeight() + upwardDistance <= 557) { // Check if the stick height doesn't exceed the limit
            double currentTranslateY = stick.getTranslateY();

            // Increasing the scale of the rectangle in the Y-axis
            stick.getTransforms().add(new Scale(1, 1.1, 1, stick.getTranslateX(), stick.getBoundsInLocal().getMaxY(), stick.getTranslateZ()));

            // Compensating for the translation to keep the base stationary
            stick.setTranslateY(currentTranslateY);
        }
    }


    public void generate_scene(ActionEvent event) throws IOException {
        Graphics graphics = new Graphics();
        Random_generator random_generator = new Random_generator();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("playable_screen.fxml"));
        root = loader.load();
        scene = new Scene(root);

        int rectangleCount = 2; // Number of rectangles to be added
        double startX = 0; // Initial x position of the first rectangle
        double startY = 488; // Initial y position of the first rectangle
        double heroStartX = 0;
        double heroStartY = 387;
        Image image = new Image(getClass().getResourceAsStream("images/hero11.png"));
        ImageView imageview = new ImageView(image);
        imageview.setFitWidth(100);
        imageview.setFitHeight(100);
        imageview.setX(heroStartX);
        imageview.setY(heroStartY);
        root.getChildren().add(imageview);
        stick = graphics.createStick();


        Color customColor = Color.valueOf("#795234");


        for (int i = 0; i < rectangleCount; i++) {
            double rectangleWidth = random_generator.getRandomWidth(); // Width of each rectangle
            double rectangleHeight = 70; // Height of each rectangle
            double spacing = random_generator.getRandomSpacing();
            Rectangle box = graphics.createRectangle(rectangleWidth, rectangleHeight, customColor);
            box.setLayoutX(startX + (rectangleWidth + spacing) * i);
            box.setLayoutY(startY);
            root.getChildren().add(box);
        }
        stick.setTranslateX(90);
        stick.setTranslateY(480);
        stick.setTranslateZ(10);

        double currentHeight = stick.getHeight();

        stick.setRotationAxis(Rotate.X_AXIS);
        stick.setRotate(0);

        root.getChildren().add(stick);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {

                elongateStickWithAnimation();

                keyEvent.consume();
            }
        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED, dropStick -> {
            if (dropStick.getCode() == KeyCode.X) {

                createDropAnimation();
            }
        });

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void  createDropAnimation() {

        // Set up a rotation animation (90 degrees in z-axis)
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1.5), stick);
        rotateTransition.setAxis(Rotate.Z_AXIS);
        rotateTransition.setByAngle(90);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }

    public void switchToPause(ActionEvent event) throws IOException {
        root_pause = FXMLLoader.load(getClass().getResource("exit_screen.fxml"));
        stage_pause = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_pause = new Scene(root_pause);

        stage_pause.setScene(scene_pause);
        stage_pause.show();
    }


}