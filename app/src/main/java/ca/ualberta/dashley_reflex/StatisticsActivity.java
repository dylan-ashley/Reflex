package ca.ualberta.dashley_reflex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.IOException;

public class StatisticsActivity extends AppCompatActivity {

    private StatisticsHandler statisticsHandler = StatisticsHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        if (!statisticsHandler.statisticsAreLoaded()) {
            statisticsHandler.loadFromFile(this.getBaseContext());
        }

        ListView listView = (ListView) findViewById(R.id.statistics_list);
        AndroidStatisticsDisplay displayInterface = new AndroidStatisticsDisplay(listView);
        new StatisticsDisplayManager(displayInterface);
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
}
