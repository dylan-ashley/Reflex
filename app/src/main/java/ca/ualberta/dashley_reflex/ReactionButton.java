package ca.ualberta.dashley_reflex;

import android.widget.Button;

import static java.lang.Math.random;
import static java.lang.Math.round;

/**
 * Created by dashley on 2015-09-26.
 */
public abstract class ReactionButton {

    protected int activeButtonColor;
    protected int inactiveButtonColor;
    protected Button button;
    protected long waitTime;
    protected long endTime;
    protected Boolean isRunning;

    public ReactionButton(int activeButtonColor,
                          int inactiveButtonColor,
                          Button button) {
        this.activeButtonColor = activeButtonColor;
        this.inactiveButtonColor = inactiveButtonColor;
        this.button = button;
        this.isRunning = Boolean.FALSE;
    }

    // TJ_Fischer; http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java; 2015-09-26
    protected long getRandomTime() {
        long min = 10000;
        long max = 2000000;
        return (long) (min + (random() * ((min - max) + 1)));
    }

    protected abstract void onReaction(long reactionTime);

    public void onClick() {
        if (isRunning) {
            onReaction(round((System.nanoTime() - this.endTime) / 1000));
        } else {
            isRunning = Boolean.TRUE;
            button.setBackgroundColor(inactiveButtonColor);
            this.waitTime = this.getRandomTime();
            this.endTime = System.nanoTime() + this.waitTime;
            //this.waitNow();
        }
    }

    private void waitNow() {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        button.setBackgroundColor(activeButtonColor);
    }
}
