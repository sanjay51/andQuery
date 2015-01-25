package com.codingg.andquery.Animation;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sanjav on 1/4/15.
 */
public abstract class Animation {
    public float speed = 1;
    public float refinement = 100;
    int counter = 0;
    boolean isTerminated = false;
    boolean isRemovable  = false;
    private static final Object lock = new Object();

    public enum Category { INFINITE, ONE_TIME };
    private Category category;
    protected View view = null;
    private int id = 0;
    public static Activity activity = null;

    public static int AnimationCounter = 0;
    public static List<Animation> AnimationList = new ArrayList<>();

    public Animation() {
        this(Category.INFINITE);
    }

    public static void start(Activity activity1) {
        activity = activity1;
        GameLoop loop = new GameLoop();
        new Thread(loop).start();
    }

    public Animation(Category category) {
        this.category = category;
        this.id = AnimationCounter;
        AnimationCounter++;
    }

    public abstract void onIterate();
    public abstract void begin();
    public abstract void onTerminate();

    //return true when to-terminate, always return false for infinite loops
    public boolean terminationCondition() { return false; }

    public static void registerForAnimation(View view, Animation animation) {
        animation.view = view;
        synchronized (lock) {
            AnimationList.add(animation);
        }
        animation.begin();
    }

    public View getView() {
        return view;
    }

    public static void loop() {
        synchronized (lock) {
            for (Iterator<Animation> iterator = AnimationList.iterator(); iterator.hasNext(); ) {
                Animation animation = iterator.next();
                if (!animation.isTerminated) {
                    animation.iterate();

                    if (animation.terminationCondition()) {
                        animation.isTerminated = true;
                    }

                    if (animation.isRemovable) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    public void updateViewLocation(final float x, final float y) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getView().setX(x);
                getView().setY(y);
            }
        });
    }

    public void markAsTerminated() {
        isTerminated = true;
    }

    public void iterate() {
        if (counter == speed) {
            counter = 0;
            this.onIterate();
        }
        counter++;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setRefinement(float refinement) {
        this.refinement = refinement;
    }

    public double mod(double x) {
        return x>=0 ? x : (-x);
    }
}
