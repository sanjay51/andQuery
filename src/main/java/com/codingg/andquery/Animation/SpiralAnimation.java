package com.codingg.andquery.Animation;

import android.graphics.Point;

/**
 * Created by sanjav on 1/25/15.
 */
public class SpiralAnimation extends TrajectoryAnimation {
    float radius = 1;
    float angle = 0;
    float divergence = 1;
    int moves = 0;

    public SpiralAnimation(Point startPoint, float divergence, float speed, float refinement) {
        super(startPoint, speed, refinement);
        this.divergence = divergence;
    }

    @Override
    public float getX(float currX, float currY) {
        double x = startPoint.x + (radius * Math.cos(Math.toRadians(angle)));
        angle += speed;
        radius += divergence;

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

    @Override
    public boolean terminationCondition() {
        if (moves == 2000) {
            return true;
        }
        moves++;

        return false;
    }
}
