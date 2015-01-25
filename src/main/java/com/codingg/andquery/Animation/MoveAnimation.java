package com.codingg.andquery.Animation;

import android.app.Activity;
import android.graphics.Point;

/**
 * Created by sanjav on 1/4/15.
 */
public class MoveAnimation extends Animation {
    private float currY  = 0;
    private float currX  = 0;
    private float stepY  = 0;
    private float stepX  = 0;
    private int quadrant = 1;

    public Point startPoint;
    public Point endPoint;

    public static Activity mainActivity = null;

    public MoveAnimation(Activity mainActivity, Point startPoint, Point endPoint, float speed) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        updateQuadrant();

        setSpeed(speed);

        this.activity = mainActivity;
    }

    public MoveAnimation(Activity mainActivity, Point startPoint, Point endPoint, float speed, float refinement) {
        this(mainActivity, startPoint, endPoint, speed);
        setRefinement(refinement);
    }

    public void resetParameters(Point startPoint, Point endPoint, float speed, float refinement) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        setSpeed(speed);
        setRefinement(refinement);

        updateQuadrant();
        begin();
        this.isTerminated = false;
    }

    public void updateQuadrant() {
        if (startPoint.x <= endPoint.x && startPoint.y <= endPoint.y) {
            quadrant = 4;
        } else if (startPoint.x <= endPoint.x && startPoint.y >= endPoint.y) {
            quadrant = 1;
        } else if (startPoint.x >= endPoint.x && startPoint.y >= endPoint.y) {
            quadrant = 2;
        } else if (startPoint.x >= endPoint.x && startPoint.y <= endPoint.y) {
            quadrant = 3;
        }
    }

    @Override
    public void onIterate() {
        currX = currX + stepX;
        currY = currY + stepY;

        updateViewLocation(currX, currY);
    }

    @Override
    public void begin() {
        currX = startPoint.x;
        currY = startPoint.y;

        stepX = (endPoint.x - startPoint.x) / refinement;
        stepY = (endPoint.y - startPoint.y) / refinement;

        onIterate();
    }

    @Override
    public boolean terminationCondition() {
        if (quadrant == 4) {
            return currX >= endPoint.x
                    && currY >= endPoint.y;
        } else if (quadrant == 1) {
            return currX >= endPoint.x
                    && currY <= endPoint.y;
        } else if (quadrant == 2) {
            return currX <= endPoint.x
                    && currY <= endPoint.y;
        } else if (quadrant == 3) {
            return currX <= endPoint.x
                    && currY >= endPoint.y;
        }

        //Else don't terminate
        return false;
    }

    public void onBegin() {};
    public void onTerminate() {};
}
