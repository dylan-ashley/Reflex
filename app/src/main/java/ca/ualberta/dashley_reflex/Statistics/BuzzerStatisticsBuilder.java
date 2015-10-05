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
import java.util.LinkedList;


/**
 * Generator for Buzzer statistics.
 *
 * Rationale: Delegates the complicated task of computing buzzer statistics to a specialist class.
 */
public class BuzzerStatisticsBuilder {

    private final StatisticsHandler handler;
    private ArrayList<String> statisticsList = new ArrayList<>();

    /**
     * Returns a new instance of this class.
     *
     * @param handler handler to retrieve statistics from
     */
    public BuzzerStatisticsBuilder(StatisticsHandler handler) {
        this.handler = handler;
    }

    /**
     * Computes all relevant statistics and returns them.
     *
     * @return statistics
     */
    public ArrayList<String> getStatistics() {
        LinkedList<Long> twoPlayerBuzzerWins = handler.getTwoPlayerBuzzerWins();
        for (int i = 0; i < 2; i++) {
            statisticsList.add("Two Player Game Show Buzzer Player "
                    + (i + 1)
                    + " Buzzer Presses: "
                    + twoPlayerBuzzerWins.get(i));
        }

        LinkedList<Long> threePlayerBuzzerWins = handler.getThreePlayerBuzzerWins();
        for (int i = 0; i < 3; i++) {
            statisticsList.add("Three Player Game Show Buzzer Player "
                    + (i + 1)
                    + " Buzzer Presses: "
                    + threePlayerBuzzerWins.get(i));
        }

        LinkedList<Long> fourPlayerBuzzerWins = handler.getFourPlayerBuzzerWins();
        for (int i = 0; i < 4; i++) {
            statisticsList.add("Four Player Game Show Buzzer Player "
                    + (i + 1)
                    + " Buzzer Presses: "
                    + fourPlayerBuzzerWins.get(i));
        }

        return statisticsList;
    }
}
