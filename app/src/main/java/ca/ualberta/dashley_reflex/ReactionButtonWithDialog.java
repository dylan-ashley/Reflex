package ca.ualberta.dashley_reflex;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by dashley on 2015-09-26.
 */
public class ReactionButtonWithDialog extends ReactionButton {

    private Activity activity;

    public ReactionButtonWithDialog(int activeButtonColor,
                                    int inactiveButtonColor,
                                    Button button,
                                    Activity activity) {
        super(activeButtonColor, inactiveButtonColor, button);
        this.activity = activity;
    }

    @Override
    protected void onReaction(long reactionTime) {
        if (reactionTime > 0) {
            new SimpleDialog("Your reaction time was " + reactionTime + " ms.", this.activity);

        } else {
            new SimpleDialog("You hit the button too soon!", this.activity);
        }
    }
}
