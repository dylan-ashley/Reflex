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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static java.util.Collections.sort;

/**
 * Generator for Reaction Time statistics.
 *
 * Rationale: Delegates the complicated task of computing reaction time statistics to a specialist class.
 */
public class ReactionTimeStatisticsBuilder {

    private final StatisticsHandler handler;
    private ArrayList<String> statisticsList = new ArrayList<>();

    /**
     * Returns a new instance of this class.
     *
     * @param handler handler to retrieve statistics from
     */
    public ReactionTimeStatisticsBuilder(StatisticsHandler handler) {
        this.handler = handler;
    }

    /**
     * Computes all relevant statistics and returns them.
     *
     * @return statistics
     */
    public ArrayList<String> getStatistics() {
        LinkedList<Long> reactionTimes = (LinkedList<Long>) handler.getReactionTimes().clone();
        int reactionTimesSize = reactionTimes.size();

        statisticsList = new ArrayList<>();

        if (reactionTimesSize > 9) {
            int start = reactionTimesSize - 10;
            LinkedList<Long> last10ReactionTimes = new LinkedList<>(reactionTimes.subList(start, reactionTimesSize));
            loadStatisticsForTrial(last10ReactionTimes);
        }

        if (reactionTimesSize > 99) {
            int start = reactionTimesSize - 100;
            LinkedList<Long> last100ReactionTimes = new LinkedList<>(reactionTimes.subList(start, reactionTimesSize));
            loadStatisticsForTrial(last100ReactionTimes);
        }

        if (reactionTimesSize > 0) {
            loadStatisticsForTrial(reactionTimes);
        }

        return statisticsList;
    }

    /**
     * Loads relevant statistics for a given set of times.
     *
     * @param reactionTimes times used to generate statistics from
     */
    private void loadStatisticsForTrial(LinkedList<Long> reactionTimes) {
        int trialCount = reactionTimes.size();
        if (trialCount != 0) {
            sort(reactionTimes);
            statisticsList.add("Reaction time Fastest of last " + trialCount + " trials: " + reactionTimes.getFirst());
            statisticsList.add("Reaction time Slowest of last " + trialCount + " trials: " + reactionTimes.getLast());
            Long sumOfReactionTimes = sum(reactionTimes);
            statisticsList.add("Reaction time Average of last " + trialCount + " trials: " +
                    (sumOfReactionTimes.doubleValue() / reactionTimes.size()));
            Double median;
            if (trialCount % 2 == 1) {
                median = reactionTimes.get(trialCount / 2).doubleValue();
            } else {
                int lowerMiddleIndex = (trialCount - 1) / 2;
                median = (reactionTimes.get(lowerMiddleIndex) + reactionTimes.get(lowerMiddleIndex + 1)) / 2.0;
            }
            statisticsList.add("Reaction time Median of last " + trialCount + " trials: " + median);
        }
    }

    /**
     * Gets the sum of a series of longs.
     *
     * @param collection collection to sum over
     * @return sum of the elements
     */
    private long sum(Collection<Long> collection) {
        Iterator<Long> iterator = collection.iterator();
        long total = 0;
        while (iterator.hasNext()) {
            total += iterator.next();
        }
        return total;
    }
}
