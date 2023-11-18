package com.example.stickhero;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Playable_Screen_Controller implements Initializable {
	
	
	@FXML
	private ImageView satwik;
    @FXML
    private Button pause;
	
	private Stage stage,stage_pause;
    private Scene scene,scene_pause;
    private Group root;
    private Parent root_pause;
    private Rectangle stick;
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
		// TODO Auto-generated method stub
//    	satwik = new Pane();
		
		
	}
    double stickInitialY = 480;
    double upwardDistance = 10;
    private void elongateStickWithAnimation() {
        if (stick.getHeight() + upwardDistance <= 557) { // Check if the stick height doesn't exceed the limit
            double newHeight = stick.getHeight() + upwardDistance; // Increase height by the specified distance

            // Animate stick's height and y-position
            KeyValue heightValue = new KeyValue(stick.heightProperty(), newHeight);
            KeyValue yValue = new KeyValue(stick.yProperty(), stickInitialY - upwardDistance);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), heightValue, yValue);
            Timeline timeline = new Timeline(keyFrame);
            timeline.play();
        }
    }

//   
//    
    
//	
//	
	public void generate_scene(ActionEvent event) throws IOException
	{
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
//      URL url = getClass().getResource("images/");
        Image image = new Image(getClass().getResourceAsStream("images/hero11.png"));
        ImageView imageview = new ImageView(image);
      imageview.setFitWidth(100); // Set the width
      imageview.setFitHeight(100);
      imageview.setX(heroStartX);
      imageview.setY(heroStartY);
      root.getChildren().add(imageview);
      stick = graphics.createStick();
      
//      satwik.setLayoutX(heroStartX);
//      satwik.setLayoutY(heroStartY);
        Color customColor = Color.valueOf("#795234");
      
//      hero.setX(startX + ((rectangleWidth + spacing) * i)-5 );
      for (int i = 0; i < rectangleCount; i++) {
      	  double rectangleWidth = random_generator.getRandomWidth(); // Width of each rectangle
          double rectangleHeight = 70; // Height of each rectangle
          double spacing = random_generator.getRandomSpacing();
//          Rectangle rectangle = new Rectangle(rectangleWidth, rectangleHeight);
          Rectangle box = graphics.createRectangle(rectangleWidth, rectangleHeight, customColor);
          
          
          
          
          box.setLayoutX(startX + (rectangleWidth + spacing) * i);
          box.setLayoutY(startY);
//          rectangle.setFill(javafx.scene.paint.Color.BROWN);
          root.getChildren().add(box);
//          
      }
      stick.setLayoutX(90); 
      stick.setLayoutY(480);
      root.getChildren().add(stick);
      
      scene.setOnKeyPressed(eventKey -> {
          if (eventKey.getCode() == KeyCode.SPACE) {
              elongateStickWithAnimation();
          }
      });
      stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
		
        
    }
    
    public void switchToPause(ActionEvent event)throws IOException
    {
        root_pause = FXMLLoader.load(getClass().getResource("exit_screen.fxml"));
        stage_pause = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene_pause = new Scene(root_pause);
//        scene_pause.getStylesheets().add(getClass().getResource("com/example/stickhero/main_screen_css.css").toExternalForm());
        stage_pause.setScene(scene_pause);
        stage_pause.show();
    }

	
	
}