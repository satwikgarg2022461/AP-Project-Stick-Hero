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
        Sound sound = new Sound();
        sound.buttonSound();
        root = FXMLLoader.load(getClass().getResource("gameLoad.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gameLoad_css.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
