package com.example.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Exit_screen_controller {
    @FXML
    private Button home;
    @FXML
    private Button reload;
    @FXML
    private Button revive;
    private Stage stage_home,stage_reload;
    private Scene scene_home,scene_reload;
    private Parent root_home,root_reload;
    ResestGlobal resestGlobal = new ResestGlobal();


    public void switchToHome(ActionEvent event) throws IOException
    {
        Sound sound = new Sound();
        sound.buttonSound();
        resestGlobal.reset();
        root_home = FXMLLoader.load(getClass().getResource("main_screen.fxml"));
        stage_home = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene_home = new Scene(root_home);
        scene_home.getStylesheets().add(getClass().getResource("main_screen_css.css").toExternalForm());
        stage_home.setScene(scene_home);
        stage_home.show();
    }
    public void switchToPlayable(ActionEvent event) throws IOException
    {
        Sound sound = new Sound();
        sound.buttonSound();
        resestGlobal.reset();
        Playable_Screen_Controller playable_Screen_Controller = new Playable_Screen_Controller();
        playable_Screen_Controller.generate_scene(event);
    }

    public void revive(ActionEvent event) throws IOException
    {
        System.out.println("===========revive================");
        Sound sound = new Sound();
        sound.buttonSound();
//        int score = GlobalData.score;
//        int cherryCount = GlobalData.cherrycount;

        if(GlobalData.cherrycount >= 2)
        {
            resestGlobal.reviveReset();
//            GlobalData.score = score;
//            GlobalData.cherrycount = cherryCount-2;
            Playable_Screen_Controller playable_Screen_Controller = new Playable_Screen_Controller();
            playable_Screen_Controller.generate_scene(event);

        }
        else
        {
            System.out.println("Not enough cherry");
        }

    }


}
