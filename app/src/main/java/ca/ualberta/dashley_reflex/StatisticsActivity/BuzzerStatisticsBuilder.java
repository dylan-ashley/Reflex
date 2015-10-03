package ca.ualberta.dashley_reflex.StatisticsActivity;

import java.util.ArrayList;
import java.util.LinkedList;

import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;


/**
 * Created by dashley on 2015-10-03.
 */
public class BuzzerStatisticsBuilder {

    private ArrayList<String> statisticsList = new ArrayList<>();
    private final StatisticsHandler handler;

    public BuzzerStatisticsBuilder(StatisticsHandler handler) {
        this.handler = handler;
    }

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
