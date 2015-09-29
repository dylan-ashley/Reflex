package ca.ualberta.dashley_reflex;

/**
 * Created by dashley on 2015-09-28.
 */
public interface StatisticsDisplay {

    void addStatisticsToDisplay(String statistic);

    void removeStatisticsFromDisplay(String statistic);

    void refreshDisplay();
}
