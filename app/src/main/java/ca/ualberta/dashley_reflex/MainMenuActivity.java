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

package ca.ualberta.dashley_reflex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ca.ualberta.dashley_reflex.GameShowBuzzer.GameShowBuzzerActivity;
import ca.ualberta.dashley_reflex.ReactionTimer.ReactionTimerActivity;
import ca.ualberta.dashley_reflex.Statistics.StatisticsActivity;

/**
 * Activity that controls the main menu screen of the app.
 *
 * Rationale: This follows the paradigm typical of android programming.
 */
public class MainMenuActivity extends BaseReflexActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /**
     * Starts the ReactionTimer activity.
     *
     * @param view android view object
     */
    public void startReactionTimer(View view) {
        Intent intent = new Intent(this, ReactionTimerActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the GameShowBuzzer activity.
     *
     * @param view android view object
     */
    public void startGameShowBuzzer(View view) {
        Intent intent = new Intent(this, GameShowBuzzerActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the Statistics activity.
     *
     * @param view android view object
     */
    public void startStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
