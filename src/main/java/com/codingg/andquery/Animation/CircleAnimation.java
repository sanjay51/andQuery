package com.codingg.andquery.Animation;

import android.graphics.Point;

/**
 * Created by sanjav on 1/24/15.
 */
public class CircleAnimation extends TrajectoryAnimation {
    float radius = 5;
    float angle = 0;

    public CircleAnimation(Point startPoint, float radius, float speed, float refinement) {
        super(startPoint, speed, refinement);
        this.radius = radius;
    }
    @Override
    public float getX(float currX, float currY) {
        double x = startPoint.x + (radius * Math.cos(Math.toRadians(angle)));
        angle += speed;
        if (angle == 360) angle = 0;

        return (float) x;
    }

    @Override
    public float getY(float currX, float currY) {
        double y = startPoint.y + (radius * Math.sin(Math.toRadians(angle)));
        return (float) y;
    }

    @Override
    public void onTerminate() {

    }
}
