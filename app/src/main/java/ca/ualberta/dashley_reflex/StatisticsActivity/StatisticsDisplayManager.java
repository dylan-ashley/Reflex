package ca.ualberta.dashley_reflex.StatisticsActivity;

import java.util.ArrayList;
import java.util.LinkedList;

import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

import static java.util.Collections.sort;

/**
 * Created by dashley on 2015-09-28.
 */
public class StatisticsDisplayManager {

    private final StatisticsDisplay display;
    private final StatisticsHandler statisticsHandler = StatisticsHandler.getInstance();
    private ArrayList<String> statisticsList;

    public StatisticsDisplayManager(StatisticsDisplay display) {
        this.display = display;
    }

    public void loadStatistics() {
        LinkedList<Long> reactionTimes = (LinkedList<Long>) statisticsHandler.getReactionTimes().clone();
        int reactionTimesSize = reactionTimes.size();

        statisticsList = new ArrayList<>();

        if (reactionTimesSize > 9) {
            int start = reactionTimesSize - 10;
            LinkedList<Long> last10ReactionTimes = new LinkedList<>(reactionTimes.subList(start, reactionTimesSize));
            sort(last10ReactionTimes);
            statisticsList.add("Fastest reaction time of last 10 trials: " + last10ReactionTimes.getFirst());
            statisticsList.add("Slowest reaction time of last 10 trials: " + last10ReactionTimes.getLast());
        }

        if (reactionTimesSize > 99) {
            int start = reactionTimesSize - 100;
            LinkedList<Long> last100ReactionTimes = new LinkedList<>(reactionTimes.subList(start, reactionTimesSize));
            sort(last100ReactionTimes);
            statisticsList.add("Fastest reaction time of last 100 trials: " + last100ReactionTimes.getFirst());
            statisticsList.add("Slowest reaction time of last 100 trials: " + last100ReactionTimes.getLast());
        }

        if (reactionTimesSize > 0) {
            sort(reactionTimes);
            statisticsList.add("Fastest reaction time: " + reactionTimes.getFirst());
            statisticsList.add("Slowest reaction time: " + reactionTimes.getLast());
        }
    }

    public void showStatistics() {
        for (String statistic: statisticsList) {
            display.addStatisticsToDisplay(statistic);
        }
        display.refreshDisplay();
    }

    public void clearStatistics() {
        for (String statistic: statisticsList) {
            display.removeStatisticsFromDisplay(statistic);
        }
        display.refreshDisplay();
    }

    public ArrayList<String> getStatistics() {
        return statisticsList;
    }
}
