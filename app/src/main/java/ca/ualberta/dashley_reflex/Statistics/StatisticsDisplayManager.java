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

import java.util.ArrayList;

import static java.util.Collections.sort;


/**
 * Created by dashley on 2015-09-28.
 */
public class StatisticsDisplayManager {

    private final StatisticsDisplay display;
    private final StatisticsHandler handler;
    private ArrayList<String> statisticsList = new ArrayList<>();

    public StatisticsDisplayManager(StatisticsDisplay display, StatisticsHandler handler) {
        this.display = display;
        this.handler = handler;
    }

    public void loadStatistics() {
        statisticsList.addAll(new ReactionTimeStatisticsBuilder(handler).getStatistics());
        statisticsList.addAll(new BuzzerStatisticsBuilder(handler).getStatistics());
    }

    public void showStatistics() {
        sort(statisticsList);
        for (String statistic : statisticsList) {
            display.addStatisticsToDisplay(statistic);
        }
        display.refreshDisplay();
    }

    public void clearStatistics() {
        for (String statistic : statisticsList) {
            display.removeStatisticsFromDisplay(statistic);
        }
        display.refreshDisplay();
        statisticsList = new ArrayList<>();
    }

    public ArrayList<String> getStatistics() {
        return statisticsList;
    }
}
