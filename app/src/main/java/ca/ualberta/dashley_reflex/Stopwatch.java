package ca.ualberta.dashley_reflex;

/**
 * Created by dashley on 2015-09-25.
 */
public class Stopwatch {

    private long startTime;

    public Stopwatch() {
        this.restart();
    }

    public void restart() {
        startTime = System.nanoTime();
    }

    public long getTime() {
        return System.nanoTime() - startTime;
    }
}
