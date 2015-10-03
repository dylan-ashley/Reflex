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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

public class GameShowBuzzerActivity extends BaseReflexActivity {

    private NumberPicker playerCountPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer);

        playerCountPicker = (NumberPicker) findViewById(R.id.player_count_picker);
        playerCountPicker.setMinValue(2);
        playerCountPicker.setMaxValue(4);
    }

    public void startGameShowBuzzer(View view) {
        int playerCount = playerCountPicker.getValue();
        if (playerCount == 2) {
            Intent intent = new Intent(this, GameShowBuzzerTwoPlayerActivity.class);
            startActivity(intent);
        } else if (playerCount == 3) {
            Intent intent = new Intent(this, GameShowBuzzerThreePlayerActivity.class);
            startActivity(intent);
        } else if (playerCount == 4) {
            Intent intent = new Intent(this, GameShowBuzzerFourPlayerActivity.class);
            startActivity(intent);
        } else {
            throw new RuntimeException("invalid player count received");
        }
    }
}
