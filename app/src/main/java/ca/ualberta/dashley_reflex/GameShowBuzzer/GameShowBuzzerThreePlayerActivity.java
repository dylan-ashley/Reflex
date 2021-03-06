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

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

/**
 * Creates and controls the Game Show Buzzer game for the case where there are three players.
 *
 * Rationale: This allows an easy and intuitive branching point while retaining the benefits of the android xml layouts.
 */
public class GameShowBuzzerThreePlayerActivity extends BaseReflexActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer_three_player);

        ArrayList<Button> players = new ArrayList<>();
        players.add((Button) findViewById(R.id.three_player_player_one_button));
        players.add((Button) findViewById(R.id.three_player_player_two_button));
        players.add((Button) findViewById(R.id.three_player_player_three_button));
        new GameShowBuzzerManager(players, messageSender, statisticsHandler);
    }
}
