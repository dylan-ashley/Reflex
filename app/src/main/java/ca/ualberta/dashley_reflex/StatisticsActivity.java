package ca.ualberta.dashley_reflex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class StatisticsActivity extends AppCompatActivity {

    private final StatisticsHandler statisticsHandler = StatisticsHandler.getInstance();
    private StatisticsDisplayManager displayManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if (!statisticsHandler.statisticsAreLoaded()) {
            statisticsHandler.loadFromFile(this.getBaseContext());
        }

        ListView listView = (ListView) findViewById(R.id.statistics_list);
        AndroidStatisticsDisplay displayInterface = new AndroidStatisticsDisplay(listView, this);
        displayManager = new StatisticsDisplayManager(displayInterface);
        displayManager.loadStatistics();
        displayManager.showStatistics();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            statisticsHandler.saveInFile(this.getBaseContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearAllStatistics(View view) {
        statisticsHandler.setReactionTimes(new LinkedList<Long>());
        try {
            statisticsHandler.saveInFile(this.getBaseContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayManager.clearStatistics();
    }

    public void sendEmail(View view) {
        ArrayList<String> statistics = displayManager.getStatistics();
        
    }

    // https://developer.android.com/guide/components/intents-common.html#Email; 2015-09-30
    private void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
