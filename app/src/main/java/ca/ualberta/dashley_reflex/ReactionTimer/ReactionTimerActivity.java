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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

/**
 * Activity that controls the Reaction Timer game.
 *
 * Rationale: This follows the paradigm typical of android programming.
 */
public class ReactionTimerActivity extends BaseReflexActivity {

    private ReactionButtonManager reactionButtonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        // Gangnus; http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context; 2015-09-26
        Button button = (Button) findViewById(R.id.reaction_timer_reaction_timer_button);
        this.reactionButtonManager = new ReactionButtonManager(
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.very_dark_grey),
                button,
                messageSender,
                statisticsHandler);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reactionButtonManager.onClick();
            }
        });

        String message = getResources().getString(R.string.reaction_timer_explanation_text_message);
        messageSender.sendMessage(message);
    }
}
