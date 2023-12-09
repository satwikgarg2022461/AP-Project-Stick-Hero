package com.example.stickhero;

import java.util.ArrayList;
interface Reset
{
    public void reset();
    public void reviveReset();
}

class ResestGlobal implements Reset {

    public void reset()
    {
        Animation animation = new Animation();
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
//        animation.setStickHeight(9.6);
        GlobalData.stickHeight = 9.6;
        GlobalData.cherrycount =0;
        GlobalData.cherryList = new ArrayList<>();
        GlobalData.realScore =0;
    }

    public void reviveReset()
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
//        animation.setStickHeight(9.6);
        GlobalData.stickHeight = 9.6;
        GlobalData.cherrycount -=2;
        GlobalData.cherryList = new ArrayList<>();

    }

}
