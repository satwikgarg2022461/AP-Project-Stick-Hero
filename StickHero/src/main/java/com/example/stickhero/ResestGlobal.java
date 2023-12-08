package com.example.stickhero;

import java.util.ArrayList;

public class ResestGlobal {
    public void reset()
    {
        GlobalData.totalRectangleLength = 0;
        GlobalData.totalSpaceLength = 0;
        GlobalData.rectangleArrayList = new ArrayList<>();
        GlobalData.spacingArrayList = new ArrayList<>();
        GlobalData.counter = 0;
        GlobalData.score=0;
        GlobalData.stickX=0;
        GlobalData.transitionX=0;
        GlobalData.isstickrotate=true;
        GlobalData.isMoveCharcter=true;
    }
}
