package ca.ualberta.dashley_reflex;

/**
 * Created by dashley on 2015-09-25.
 */
public class StatisticsHandler {
    private static StatisticsHandler ourInstance = new StatisticsHandler();

    public static StatisticsHandler getInstance() {
        return ourInstance;
    }

    private StatisticsHandler() {
    }
}
