package com.example.stickhero;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GlobalData {
    public static double totalRectangleLength = 0;
    public static double totalSpaceLength = 0;
    public static ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
    public static ArrayList<ImageView> cherryList = new ArrayList<>();

    public static ArrayList<Double> spacingArrayList = new ArrayList<>();
    public static Box stick;
    public static int counter =0;

    public static int score = 0;
    public static int cherrycount = 0;
    public static double stickX =0;
    public static double transitionX=0;

    public static int realScore = 0;


    public static boolean isstickrotate = true;
    public static boolean isMoveCharcter = true;
    public static double stickHeight = 9.6;
    public static Scene scene;

}
