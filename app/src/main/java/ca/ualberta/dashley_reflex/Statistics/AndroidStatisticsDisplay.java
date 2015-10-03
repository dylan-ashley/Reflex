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

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.R;

/**
 * Created by dashley on 2015-09-28.
 */
public class AndroidStatisticsDisplay implements StatisticsDisplay {

    private final ArrayList<String> statisticsOnDisplay = new ArrayList<>();
    private final ArrayAdapter<String> arrayAdapter;

    public AndroidStatisticsDisplay(ListView list, Activity activity) {
        this.arrayAdapter = new ArrayAdapter<>(activity, R.layout.list_item, statisticsOnDisplay);
        list.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void addStatisticsToDisplay(String statistic) {
        statisticsOnDisplay.add(statistic);
    }

    @Override
    public void removeStatisticsFromDisplay(String statistic) {
        statisticsOnDisplay.remove(statistic);
    }

    @Override
    public void refreshDisplay() {
        arrayAdapter.notifyDataSetChanged();
    }
}
