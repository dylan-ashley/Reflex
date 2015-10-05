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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import ca.ualberta.dashley_reflex.Statistics.AndroidGsonStatisticsFileHandler;
import ca.ualberta.dashley_reflex.Tools.SimpleDialog;
import ca.ualberta.dashley_reflex.Statistics.StatisticsHandler;

/**
 * Abstract class that instantiates and manages a SimpleDialog and a StatisticsHandler.
 *
 * Rationale: Most activities in this application use one or both of the aforementioned objects.
 */
public abstract class BaseReflexActivity extends AppCompatActivity {

    protected final SimpleDialog messageSender = new SimpleDialog(this);
    protected StatisticsHandler statisticsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statisticsHandler = new StatisticsHandler(new AndroidGsonStatisticsFileHandler(this.getBaseContext()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            statisticsHandler.saveInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
