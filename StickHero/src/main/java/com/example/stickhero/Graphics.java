package com.example.stickhero;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;

public class Graphics {

	public Rectangle createRectangle(double width, double height, Color color)
	{
		Rectangle rectangle = new Rectangle(width, height);
		rectangle.setFill(color);
		return rectangle;
	}
	
	public Box createStick()
	{
		Box stick = new Box(10,10,10);
		PhongMaterial material = new PhongMaterial();
		Color customColor = Color.valueOf("#795234");
		material.setDiffuseColor(Color.BLACK);
		stick.setMaterial(material);
		return stick;
	}

}
