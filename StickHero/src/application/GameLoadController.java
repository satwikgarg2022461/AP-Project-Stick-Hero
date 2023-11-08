package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameLoadController {
	
	@FXML
    private Button playbutton;
    private Stage stage;
    private Scene scene;
    private Parent root;
	
	public void switchToPlayScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("playable_screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("playable_screen_css.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}