package ca.ualberta.dashley_reflex.StatisticsActivity;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

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
