package ca.ualberta.dashley_reflex.ReactionTimerActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import ca.ualberta.dashley_reflex.R;
import ca.ualberta.dashley_reflex.Tools.SimpleDialog;
import ca.ualberta.dashley_reflex.Tools.StatisticsHandler;

public class ReactionTimerActivity extends AppCompatActivity {

    private ReactionButtonManager reactionButtonManager;
    private final StatisticsHandler statisticsHandler = StatisticsHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        if (!statisticsHandler.statisticsAreLoaded()) {
            statisticsHandler.loadFromFile(this.getBaseContext());
        }

        SimpleDialog messageSender = new SimpleDialog(this);

        // Gangnus; http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context; 2015-09-26
        Button button = (Button) findViewById(R.id.reaction_timer_reaction_timer_button);
        this.reactionButtonManager = new ReactionButtonManager(
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.very_dark_grey),
                button,
                messageSender);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reactionButtonManager.onClick();
            }
        });

        String message = getResources().getString(R.string.reaction_timer_explanation_text_message);
        messageSender.sendMessage(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_timer, menu);
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