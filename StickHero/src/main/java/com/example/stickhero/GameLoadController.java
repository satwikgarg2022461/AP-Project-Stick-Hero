package com.example.stickhero;

import java.io.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameLoadController implements Serializable {
    public static final long serialVersionUID = 42L;
    @FXML
    public Button loadbutton;
    @FXML
    private Button playbutton;
    Playable_Screen_Controller playable_Screen_Controller = new Playable_Screen_Controller();
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

    public void switchToPlayScreen(ActionEvent event) throws IOException {
        SoundFactory soundFactory = new SoundFactory();
        Sound button = soundFactory.getSound("Button");
        button.getSound();

    	playable_Screen_Controller.generate_scene(event);
    }

    public void LoadswitchtoPlayScreen(ActionEvent event) throws IOException, ClassNotFoundException
    {
        System.out.println("Load is working");
        SoundFactory soundFactory = new SoundFactory();
        Sound button = soundFactory.getSound("Button");
        button.getSound();
        ObjectInputStream in =null;
        ArrayList<Integer> arr= null;
        try {
            in = new ObjectInputStream(new FileInputStream("load.txt"));
            arr = (ArrayList<Integer>) in.readObject();
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            System.out.println("No previous game record");
        }
        finally {
            if(in!=null)
            {
                in.close();
            }
            if(arr != null)
            {
                GlobalData.realScore = arr.get(0);
                GlobalData.cherrycount = arr.get(1);
                playable_Screen_Controller.generate_scene(event);
            }
            else
            {
                System.out.println("No previous game record");
            }


        }
    }


}
