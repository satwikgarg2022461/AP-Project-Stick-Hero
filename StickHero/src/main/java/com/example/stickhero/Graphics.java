package com.example.stickhero;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Graphics {

	public Rectangle createRectangle(double width, double height, Color color)
	{
		Rectangle rectangle = new Rectangle(width, height);
		rectangle.setFill(color);
		return rectangle;
	}
	
	public Rectangle createStick()
	{
		Rectangle stick = new Rectangle(10,10,Color.BLACK);
		return stick;
	}

}
