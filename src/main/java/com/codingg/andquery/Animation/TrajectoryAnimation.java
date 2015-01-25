package com.codingg.andquery.Animation;

import android.graphics.Point;

/**
 * Created by sanjav on 1/24/15.
 */
public abstract class TrajectoryAnimation extends Animation {
    public Point startPoint;

    private float currY  = 0;
    private float currX  = 0;

    @Override
    public void onIterate() {
        currX = getX(currX, currY);
        currY = getY(currX, currY);

        updateViewLocation(currX, currY);
    }

    @Override
    public void begin() {
        currX = startPoint.x;
        currY = startPoint.y;

        onIterate();
    }

    public abstract float getX(float currX, float currY);
    public abstract float getY(float currX, float currY);

    public TrajectoryAnimation(Point startPoint, float speed) {
        this.startPoint = startPoint;

        setSpeed(speed);
    }

    public TrajectoryAnimation(Point startPoint, float speed, float refinement) {
        this.startPoint = startPoint;

        setRefinement(refinement);
        setSpeed(speed);
    }

    public void resetParameters(Point startPoint, float speed, int refinement) {
        this.startPoint = startPoint;
        setSpeed(speed);
        setRefinement(refinement);
        this.isTerminated = false;
    }

    @Override
    public boolean terminationCondition() {
        return false;
    }
}
