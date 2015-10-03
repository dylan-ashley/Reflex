package ca.ualberta.dashley_reflex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ca.ualberta.dashley_reflex.GameShowBuzzerActivity.GameShowBuzzerActivity;
import ca.ualberta.dashley_reflex.ReactionTimerActivity.ReactionTimerActivity;
import ca.ualberta.dashley_reflex.StatisticsActivity.StatisticsActivity;

public class MainMenuActivity extends BaseReflexActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
