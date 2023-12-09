package com.example.stickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
//        ----test
        Result result= JUnitCore.runClasses(TestCases.class);
        System.out.println("============Junit Test Case==============");
        for (Failure failure : result.getFailures()) {
            System.out.println("Junit Test case result "+failure.toString());
        }
        System.out.println("Junit Test case result "+result.wasSuccessful());
        GlobalData.cherrycount = 0;

        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("main_screen.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("main_screen_css.css").toExternalForm());
        primaryStage.setScene(scene);
        Main_screen_controller sampleController = new Main_screen_controller();
        sampleController.initialize(null, null);
        primaryStage.setTitle("Stick Heros");
        Image icon = new Image(getClass().getResourceAsStream("images/icon.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}