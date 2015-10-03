package ca.ualberta.dashley_reflex.ReactionTimerActivity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import ca.ualberta.dashley_reflex.Tools.MessageSender;
import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

import static java.lang.Math.random;

/**
 * Created by dashley on 2015-09-26.
 */
public class ReactionButtonManager {

    private final int activeButtonColor;
    private final int inactiveButtonColor;
    private final Button button;
    private final MessageSender messageSender;
    private final StatisticsHandler statisticsHandler;
    private final Handler activeButtonColorHandler;
    private final Handler inactiveButtonColorHandler;
    private Boolean isRunning;
    private Timer buttonColorChanger;
    private long endTime;

    public ReactionButtonManager(final int activeButtonColor,
                                 final int inactiveButtonColor,
                                 final Button button,
                                 final MessageSender messageSender,
                                 final StatisticsHandler statisticsHandler) {
        this.activeButtonColor = activeButtonColor;
        this.inactiveButtonColor = inactiveButtonColor;
        this.button = button;
        this.messageSender = messageSender;
        this.statisticsHandler = statisticsHandler;
        this.isRunning = Boolean.FALSE;
        this.buttonColorChanger = new Timer();
        activeButtonColorHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                button.setBackgroundColor(activeButtonColor);
            }
        };
        inactiveButtonColorHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                button.setBackgroundColor(inactiveButtonColor);
            }
        };
    }

    // TJ_Fischer; http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java; 2015-09-26
    private long getRandomTime() {
        long min = 10;
        long max = 2000;
        return (long) (min + (random() * ((max - min) + 1)));
    }

    public void timedButtonColorChange(long delay, final Handler handler) {
        buttonColorChanger = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.obtainMessage(1).sendToTarget();
            }
        };
        buttonColorChanger.schedule(task, delay);
    }

    private void validReaction(long reactionTime) {
        statisticsHandler.recordReaction(reactionTime);
        messageSender.sendMessage("Your reaction time was " + reactionTime + " ms.");
    }

    private void invalidReaction() {
        buttonColorChanger.cancel();
        messageSender.sendMessage("You hit the button too soon!");
        timedButtonColorChange(250, activeButtonColorHandler);
    }

    public void onClick() {
        if (isRunning) {
            isRunning = Boolean.FALSE;
            long reactionTime = System.currentTimeMillis() - endTime;
            if (reactionTime > 0) {
                validReaction(reactionTime);
            } else {
                invalidReaction();
            }
        } else {
            isRunning = Boolean.TRUE;
            button.setBackgroundColor(inactiveButtonColor);
            // http://www.tutorialspoint.com/java/lang/system_currenttimemillis.htm; 2015-09-27
            endTime = System.currentTimeMillis() + getRandomTime();
            // http://www.java2s.com/Code/Java/Development-Class/UsejavautilTimertoscheduleatasktoexecuteonce5secondshavepassed.htm; 2015-09-27
            timedButtonColorChange(endTime - System.currentTimeMillis(), activeButtonColorHandler);
        }
    }
}
