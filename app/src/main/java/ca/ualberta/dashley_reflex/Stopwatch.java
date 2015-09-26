package ca.ualberta.dashley_reflex;

/**
 * Created by dashley on 2015-09-25.
 */
public class Stopwatch {

    private Boolean isRunning;
    private long startTime;

    public Stopwatch() {
        this.start();
    }

    public void start() {
        isRunning = Boolean.TRUE;
        startTime = System.nanoTime();
    }

    public long stop() throws StopwatchNotRunningException {
        if (isRunning) {
            isRunning = Boolean.FALSE;
            return System.nanoTime() - startTime;
        } else {
            throw new StopwatchNotRunningException();
        }
    }
}
