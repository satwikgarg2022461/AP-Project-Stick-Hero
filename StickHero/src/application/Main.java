package application;
	
import javafx.scene.image.Image;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage)throws IOException {
		
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("main_screen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("main_screen_css.css").toExternalForm());
			primaryStage.setScene(scene);
			Main_screen_controller sampleController = new Main_screen_controller();
			sampleController.initialize(null, null);
			primaryStage.setTitle("Stick Heros");
			Image icon = new Image("file:images/icon.png");
			primaryStage.getIcons().add(icon);
			
			primaryStage.setResizable(false);
			primaryStage.show();
			
			
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
