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

package ca.ualberta.dashley_reflex.GameShowBuzzer;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.Tools.MessageSender;
import ca.ualberta.dashley_reflex.Statistics.StatisticsHandler;

/**
 * Provides the core model of the Game Show Buzzer game.
 *
 * Rationale: This allows semi independence of android while abstracting the functionality of the game with any number
 * of players to a single object.
 */
public class GameShowBuzzerManager {

    private final StatisticsHandler statisticsHandler;
    private final MessageSender messageSender;
    private ArrayList<Button> players;

    /**
     * Returns a new instance of this class.
     *
     * @param players set of buttons to be used by the players
     * @param messageSender object used to communicate with the players
     * @param statisticsHandler object used to record statistics
     */
    public GameShowBuzzerManager(ArrayList<Button> players,
                                 MessageSender messageSender,
                                 StatisticsHandler statisticsHandler) {
        this.messageSender = messageSender;
        this.players = players;
        this.statisticsHandler = statisticsHandler;
        setupButtons();
    }

    /**
     * Records a button push and notifies the user who pushed their button first.
     *
     * @param i index of the player who pressed their button first
     * @throws IllegalArgumentException
     */
    private void buttonPushed(int i) throws IllegalArgumentException {
        if (players.size() == 2) {
            statisticsHandler.recordTwoPlayerBuzzer(i);
        } else if (players.size() == 3) {
            statisticsHandler.recordThreePlayerBuzzer(i);
        } else if (players.size() == 4) {
            statisticsHandler.recordFourPlayerBuzzer(i);
        } else {
            throw new IllegalArgumentException("class does not support " + i + " players");
        }
        messageSender.sendMessage("Player " + i + " pushed their button first!");
    }

    /**
     * Sets up all the buttons with their required listeners.
     */
    private void setupButtons() {
        for (int i = 0; i < players.size(); i++) {
            final int j = i + 1;
            players.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPushed(j);
                }
            });
        }
    }
}
