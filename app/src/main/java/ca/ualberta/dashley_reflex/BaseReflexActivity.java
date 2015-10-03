package ca.ualberta.dashley_reflex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import ca.ualberta.dashley_reflex.Tools.AndroidGsonStatisticsFileHandler;
import ca.ualberta.dashley_reflex.Tools.SimpleDialog;
import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

public abstract class BaseReflexActivity extends AppCompatActivity {

    protected StatisticsHandler statisticsHandler;
    protected final SimpleDialog messageSender = new SimpleDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statisticsHandler = new StatisticsHandler(new AndroidGsonStatisticsFileHandler(this.getBaseContext()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            statisticsHandler.saveInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
