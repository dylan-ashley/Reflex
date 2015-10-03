package ca.ualberta.dashley_reflex.Tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by dashley on 2015-09-25.
 */
public class StatisticsHandler {

    private final String FILENAME = "statistics.json";
    private StatisticsFileHandler fileHandler;
    private Boolean statisticsAreLoaded = Boolean.FALSE;
    private Boolean saveNeeded = Boolean.FALSE;
    private LinkedList<Long> reactionTimes;
    private LinkedList<Long> twoPlayerBuzzerWins;
    private LinkedList<Long> threePlayerBuzzerWins;
    private LinkedList<Long> fourPlayerBuzzerWins;

    public StatisticsHandler(StatisticsFileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.loadFromFile();
    }

    public void recordReaction(long time) {
        reactionTimes.add(time);
        saveNeeded = Boolean.TRUE;
    }

    public void recordTwoPlayerBuzzer(int winner) {
        Long winnerCount = twoPlayerBuzzerWins.get(winner - 1);
        twoPlayerBuzzerWins.set(winner - 1, winnerCount + 1);
        saveNeeded = Boolean.TRUE;
    }

    public void recordThreePlayerBuzzer(int winner) {
        Long winnerCount = threePlayerBuzzerWins.get(winner - 1);
        threePlayerBuzzerWins.set(winner - 1, winnerCount + 1);
        saveNeeded = Boolean.TRUE;
    }

    public void recordFourPlayerBuzzer(int winner) {
        Long winnerCount = fourPlayerBuzzerWins.get(winner - 1);
        fourPlayerBuzzerWins.set(winner - 1, winnerCount + 1);
        saveNeeded = Boolean.TRUE;
    }

    public void loadFromFile() {
        try {
            HashMap<String, LinkedList<Long>> hashMap = fileHandler.loadFile(FILENAME);
            reactionTimes = hashMap.get("reactionTimes");
            twoPlayerBuzzerWins = hashMap.get("twoPlayerBuzzerWins");
            threePlayerBuzzerWins = hashMap.get("threePlayerBuzzerWins");
            fourPlayerBuzzerWins = hashMap.get("fourPlayerBuzzerWins");
        } catch (FileNotFoundException e) {
            clearStatistics();
        }
        statisticsAreLoaded = Boolean.TRUE;
    }

    public void saveInFile() throws IOException {
        if (saveNeeded) {
            HashMap<String, LinkedList<Long>> hashMap = new HashMap<>();
            hashMap.put("reactionTimes", reactionTimes);
            hashMap.put("twoPlayerBuzzerWins", twoPlayerBuzzerWins);
            hashMap.put("threePlayerBuzzerWins", threePlayerBuzzerWins);
            hashMap.put("fourPlayerBuzzerWins", fourPlayerBuzzerWins);
            fileHandler.saveInFile(FILENAME, hashMap);
        }
    }

    public void clearStatistics() {
        reactionTimes = new LinkedList<>();
        twoPlayerBuzzerWins = new LinkedList<>(Arrays.asList(new Long(0), new Long(0)));
        threePlayerBuzzerWins = new LinkedList<>(Arrays.asList(new Long(0), new Long(0), new Long(0)));
        fourPlayerBuzzerWins = new LinkedList<>(Arrays.asList(new Long(0), new Long(0), new Long(0), new Long(0)));
        saveNeeded = Boolean.TRUE;
    }

    public LinkedList<Long> getReactionTimes() {
        return reactionTimes;
    }

    public LinkedList<Long> getTwoPlayerBuzzerWins() {
        return twoPlayerBuzzerWins;
    }

    public LinkedList<Long> getThreePlayerBuzzerWins() {
        return threePlayerBuzzerWins;
    }

    public LinkedList<Long> getFourPlayerBuzzerWins() {
        return fourPlayerBuzzerWins;
    }

    public Boolean statisticsAreLoaded() {
        return statisticsAreLoaded;
    }
}
