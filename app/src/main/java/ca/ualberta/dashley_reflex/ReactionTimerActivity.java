package ca.ualberta.dashley_reflex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class ReactionTimerActivity extends AppCompatActivity {

    private ReactionButton reactionButton;
    private StatisticsHandler statisticsHandler = StatisticsHandler.getInstance(this.getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        // Gangnus; http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context; 2015-09-26
        Button button = (Button) findViewById(R.id.reaction_timer_reaction_timer_button);
        this.reactionButton = new ReactionButton(
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.very_dark_grey),
                button,
                this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reactionButton.onClick();
            }
        });

        String message = getResources().getString(R.string.reaction_timer_explanation_text_message);
        new SimpleDialog(message, this);
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
            statisticsHandler.saveInFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
