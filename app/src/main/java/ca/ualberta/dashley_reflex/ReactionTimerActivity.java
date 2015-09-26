package ca.ualberta.dashley_reflex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReactionTimerActivity extends AppCompatActivity {

    private ReactionButtonWithDialog reactionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        // Gangnus; http://stackoverflow.com/questions/4391720/how-can-i-get-a-resource-content-from-a-static-context; 2015-09-26
        Button button = (Button) findViewById(R.id.reaction_timer_reaction_timer_button);
        Context context = getBaseContext();
        Resources res = getResources();
        this.reactionButton = new ReactionButtonWithDialog(
                res.getColor(R.color.red),
                res.getColor(R.color.very_dark_grey),
                button,
                context);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                reactionButton.onClick();
            }
        });

        startDialog();
    }

    // MysticMagicϡ; http://stackoverflow.com/questions/26097513/android-simple-alert-dialog; 2015-09-26
    private void startDialog() {
        String message = getResources().getString(R.string.reaction_timer_explanation_text_message);
        AlertDialog alertDialog= new AlertDialog.Builder(ReactionTimerActivity.this).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        alertDialog.show();
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
}
