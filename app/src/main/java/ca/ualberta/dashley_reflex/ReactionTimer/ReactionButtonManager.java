// Copyright 2015 Dylan Robert Ashley
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package ca.ualberta.dashley_reflex.ReactionTimer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import ca.ualberta.dashley_reflex.Tools.MessageSender;
import ca.ualberta.dashley_reflex.Statistics.StatisticsHandler;

import static java.lang.Math.random;

/**
 * Provides the core model of the Reaction Timer game.
 *
 * Rationale: This allows semi independence of android while abstracting the functionality of the game with any number
 * of players to a single object.
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

    /**
     * Returns a new instance of this class.
     *
     * @param activeButtonColor color to use when the button is in the active state
     * @param inactiveButtonColor color to use when the button is in the inactive state
     * @param button button used in the game
     * @param messageSender object used to communicate with the players
     * @param statisticsHandler object used to record statistics
     */
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

    /**
     *
     * Generates a random time between 10ms and 2000ms.
     *
     * @return time
     */
    // TJ_Fischer; http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java; 2015-09-26
    private long getRandomTime() {
        long min = 10;
        long max = 2000;
        return (long) (min + (random() * ((max - min) + 1)));
    }

    /**
     * Changes the button's color after a set amount of time has elapsed.
     *
     * @param delay time before color is changed
     * @param handler handler used to change the button's color
     */
    // http://www.java2s.com/Code/Java/Development-Class/UsejavautilTimertoscheduleatasktoexecuteonce5secondshavepassed.htm; 2015-09-27
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

    /**
     * Called on valid reaction.
     *
     * @param reactionTime time it took to react
     */
    private void validReaction(long reactionTime) {
        statisticsHandler.recordReaction(reactionTime);
        messageSender.sendMessage("Your reaction time was " + reactionTime + " ms.");
    }

    /**
     * Called on invalid reaction.
     */
    private void invalidReaction() {
        buttonColorChanger.cancel();
        messageSender.sendMessage("You hit the button too soon!");
        timedButtonColorChange(250, activeButtonColorHandler);
    }

    /**
     * Called when the user clicks the button. The response depends on the state of the game at the time.
     */
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
            endTime = System.currentTimeMillis() + getRandomTime();
            timedButtonColorChange(endTime - System.currentTimeMillis(), activeButtonColorHandler);
        }
    }
}
