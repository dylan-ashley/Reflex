package ca.ualberta.dashley_reflex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ca.ualberta.dashley_reflex.GameShowBuzzerActivity.GameShowBuzzerActivity;
import ca.ualberta.dashley_reflex.ReactionTimerActivity.ReactionTimerActivity;
import ca.ualberta.dashley_reflex.StatisticsActivity.StatisticsActivity;
import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

public class MainMenuActivity extends AppCompatActivity {

    private final StatisticsHandler statisticsHandler = StatisticsHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        if (!statisticsHandler.statisticsAreLoaded()) {
            statisticsHandler.loadFromFile(this.getBaseContext());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void startReactionTimer(View view) {
        Intent intent = new Intent(this, ReactionTimerActivity.class);
        startActivity(intent);
    }

    public void startGameShowBuzzer(View view) {
        Intent intent = new Intent(this, GameShowBuzzerActivity.class);
        startActivity(intent);
    }

    public void startStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
