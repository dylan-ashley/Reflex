package ca.ualberta.dashley_reflex;

/**
 * Created by dashley on 2015-09-25.
 */
public class StatisticsHandler {

    private static StatisticsHandler instance = null;

    private StatisticsHandler() {};

    public static StatisticsHandler getInstance() {
        if (instance == null) {
            instance = new StatisticsHandler();
        }
        return instance;
    }
}
