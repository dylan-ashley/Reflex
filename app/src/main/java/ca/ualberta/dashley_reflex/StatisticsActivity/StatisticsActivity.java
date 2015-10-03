package ca.ualberta.dashley_reflex.StatisticsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

public class StatisticsActivity extends BaseReflexActivity {

    private StatisticsDisplayManager displayManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView listView = (ListView) findViewById(R.id.statistics_list);
        AndroidStatisticsDisplay displayInterface = new AndroidStatisticsDisplay(listView, this);
        displayManager = new StatisticsDisplayManager(displayInterface, statisticsHandler);
        displayManager.loadStatistics();
        displayManager.showStatistics();
    }

    public void clearAllStatistics(View view) {
        statisticsHandler.clearStatistics();
        try {
            statisticsHandler.saveInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayManager.clearStatistics();
    }

    public void sendEmail(View view) {}
}
