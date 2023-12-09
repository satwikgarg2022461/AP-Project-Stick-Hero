package com.example.stickhero;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;

public abstract class Graphics {


	public Graphics() {

	}


	public Rectangle createRectangle(double x, double y,double width, double height, Color color)
	{
		return new Rectangle();
	}
	public Box createStick()
	{

		return new Box();
	}

}

class CreateRectangle extends Graphics
{
	private static CreateRectangle createRectangle = null;
	private CreateRectangle()
	{
		super();
	}


	public static CreateRectangle getInstance()
	{
		if(createRectangle == null)
		{
			createRectangle = new CreateRectangle();
			return createRectangle;
		}
		return createRectangle;

	}
	@Override
	public Rectangle createRectangle(double x, double y,double width, double height, Color color)
	{
		Rectangle rectangle = new Rectangle(x,y,width, height);
		rectangle.setFill(color);
		return rectangle;
	}
}

class CreateStick extends Graphics
{
	private static CreateStick createStick = null;
	private CreateStick()
	{
		super();
	}
	public static CreateStick getCreateStickInstance()
	{
		if(createStick == null)
		{
			createStick = new CreateStick();
			return createStick;
		}
		return createStick;
	}
	@Override
	public Box createStick()
	{
		Box stick = new Box(5,10,10);
		PhongMaterial material = new PhongMaterial();
		Color customColor = Color.valueOf("#795234");
		material.setDiffuseColor(Color.BLACK);
		stick.setMaterial(material);
		return stick;
	}
}
