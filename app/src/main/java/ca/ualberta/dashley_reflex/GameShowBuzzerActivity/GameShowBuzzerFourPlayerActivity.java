package ca.ualberta.dashley_reflex.GameShowBuzzerActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

public class GameShowBuzzerFourPlayerActivity extends BaseReflexActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer_four_player);

        ArrayList<Button> players = new ArrayList<>();
        players.add((Button) findViewById(R.id.four_player_player_one_button));
        players.add((Button) findViewById(R.id.four_player_player_two_button));
        players.add((Button) findViewById(R.id.four_player_player_three_button));
        players.add((Button) findViewById(R.id.four_player_player_four_button));
        new GameShowBuzzerManager(players, messageSender, statisticsHandler);
    }
}
