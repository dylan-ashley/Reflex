package ca.ualberta.dashley_reflex.GameShowBuzzerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import ca.ualberta.dashley_reflex.BaseReflexActivity;
import ca.ualberta.dashley_reflex.R;

public class GameShowBuzzerActivity extends BaseReflexActivity {

    private NumberPicker playerCountPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer);

        playerCountPicker = (NumberPicker) findViewById(R.id.player_count_picker);
        playerCountPicker.setMinValue(2);
        playerCountPicker.setMaxValue(4);
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
