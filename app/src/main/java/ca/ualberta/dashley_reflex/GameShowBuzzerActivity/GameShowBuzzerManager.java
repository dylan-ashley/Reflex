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

package ca.ualberta.dashley_reflex.GameShowBuzzerActivity;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.Tools.MessageSender;
import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

/**
 * Created by dashley on 2015-10-02.
 */
public class GameShowBuzzerManager {

    private final StatisticsHandler statisticsHandler;
    private final MessageSender messageSender;
    private ArrayList<Button> players;

    public GameShowBuzzerManager(ArrayList<Button> players,
                                 MessageSender messageSender,
                                 StatisticsHandler statisticsHandler) {
        this.messageSender = messageSender;
        this.players = players;
        this.statisticsHandler = statisticsHandler;
        setupButtons();
    }

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
