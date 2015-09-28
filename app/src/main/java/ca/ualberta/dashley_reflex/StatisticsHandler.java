package ca.ualberta.dashley_reflex;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * Created by dashley on 2015-09-25.
 */
public class StatisticsHandler {

    private static StatisticsHandler ourInstance;
    private static final String FILENAME = "statistics.json";
    private static LinkedList<Long> reactionTimes;
    private static Context context;

    public static StatisticsHandler getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new StatisticsHandler(context);
        }
        return ourInstance;
    }

    private StatisticsHandler(Context context) {
        this.context = context;
        try {
            loadFromFile();
        } catch (FileNotFoundException e) {
            reactionTimes = new LinkedList<>();
        }
    }

    private void ResizeReactionTimes() {
        while (reactionTimes.size() > 100) {
            reactionTimes.remove();
        }
    }

    public void RecordReaction(long time) {
        reactionTimes.add(time);
        ResizeReactionTimes();
    }

    private void loadFromFile() throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(FILENAME);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));
        Gson gson = new Gson();
        // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html; 2015-09-23
        Type linkedListType = new TypeToken<LinkedList<Long>>() {}.getType();
        reactionTimes = gson.fromJson(in, linkedListType);
    }

    public void saveInFile() throws IOException {
        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        gson.toJson(reactionTimes, out);
        out.flush();
        fos.close();
    }

    public static LinkedList<Long> getReactionTimes() {
        return reactionTimes;
    }

    public static void setReactionTimes(LinkedList<Long> reactionTimes) {
        StatisticsHandler.reactionTimes = reactionTimes;
    }
}
