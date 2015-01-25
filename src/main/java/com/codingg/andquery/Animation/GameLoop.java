package com.codingg.andquery.Animation;

/**
 * Created by sanjav on 1/24/15.
 */
public class GameLoop implements Runnable {
    @Override
    public void run() {
        while (true) {
            Animation.loop();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
