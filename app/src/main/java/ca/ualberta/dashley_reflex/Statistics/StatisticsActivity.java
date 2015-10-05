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

package ca.ualberta.dashley_reflex.Statistics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

/**
 * Activity that controls the statistics portion of the application.
 *
 * Rationale: This follows the paradigm typical of android programming.
 */
public class StatisticsActivity extends BaseReflexActivity {

    private StatisticsDisplayManager displayManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView listView = (ListView) findViewById(R.id.statistics_list);
        AndroidStatisticsDisplay displayInterface = new AndroidStatisticsDisplay(listView, this);
        displayManager = new StatisticsDisplayManager(displayInterface, statisticsHandler);
        displayManager.loadStatistics();
        displayManager.showStatistics();
    }

    /**
     * Removes all statistics from the display.
     *
     * @param view android view object
     */
    public void clearAllStatistics(View view) {
        statisticsHandler.clearStatistics();
        try {
            statisticsHandler.saveInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayManager.clearStatistics();
    }

    /**
     * Prompts the user to send an email containing all of their statistics.
     *
     * @param view android view object
     */
    // https://developer.android.com/guide/components/intents-common.html#Email; 2015-10-03
    public void sendEmail(View view) {
        StringBuilder builder = new StringBuilder();
        for (String stat : displayManager.getStatistics()) {
            builder.append(stat + "\n");
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Reflex Statistics");
        intent.putExtra(Intent.EXTRA_TEXT, builder.toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
