package com.example.stickhero;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameLoadController {

    @FXML
    private Button playbutton;
    private Stage stage;
    private Scene scene;
    private Group root;
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }
    
//    Graphics graphics = new Graphics();
//    Random_generator random_generator = new Random_generator();
    

    public void switchToPlayScreen(ActionEvent event) throws IOException {
    	Playable_Screen_Controller playable_Screen_Controller = new Playable_Screen_Controller();
    	playable_Screen_Controller.generate_scene(event);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("playable_screen.fxml"));
//        root = loader.load();
//        scene = new Scene(root);
    }
}
