package ca.ualberta.dashley_reflex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

public class GameShowBuzzerActivity extends AppCompatActivity {

    private StatisticsHandler statisticsHandler = StatisticsHandler.getInstance();
    private NumberPicker playerCountPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer);

        if (!statisticsHandler.statisticsAreLoaded()) {
            statisticsHandler.loadFromFile(this.getBaseContext());
        }

        playerCountPicker = (NumberPicker) findViewById(R.id.player_count_picker);
        playerCountPicker.setMinValue(2);
        playerCountPicker.setMaxValue(4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_show_buzzer, menu);
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

    public void startGameShowBuzzer(View view) {
        int playerCount = playerCountPicker.getValue();
        if (playerCount == 2) {
            Intent intent = new Intent(this, GameShowBuzzerTwoPlayerActivity.class);
            startActivity(intent);
        } else if (playerCount == 3) {
            Intent intent = new Intent(this, GameShowBuzzerThreePlayerActivity.class);
            startActivity(intent);
        } else if (playerCount == 4) {
            Intent intent = new Intent(this, GameShowBuzzerFourPlayerActivity.class);
            startActivity(intent);
        } else {
            throw new RuntimeException("invalid player count received");
        }
    }
}
