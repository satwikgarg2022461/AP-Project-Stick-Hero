package com.example.stickhero;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_screen_controller implements Initializable {
    @FXML
    private Button PlayButton;
    private Stage stage;
    private Scene scene;
    private Parent root;


    // Getter for Stage
    public Stage getStage() {
        return stage;
    }

    // Setter for Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Getter for Scene
    public Scene getScene() {
        return scene;
    }

    // Setter for Scene
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    // Getter for Root
    public Parent getRoot() {
        return root;
    }

    // Setter for Root
    public void setRoot(Parent root) {
        this.root = root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(PlayButton);
        translateTransition.setDuration(Duration.millis(1500));
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setByY(25);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    public void switchToPlayScreen(ActionEvent event) throws IOException {
        SoundFactory soundFactory = new SoundFactory();
        Sound button = soundFactory.getSound("Button");
        button.getSound();
        root = FXMLLoader.load(getClass().getResource("gameLoad.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gameLoad_css.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
