package ca.ualberta.dashley_reflex.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by dashley on 2015-09-26.
 */
public class SimpleDialog implements MessageSender {

    private Activity activity;

    public SimpleDialog(Activity activity) {
        this.activity = activity;
    }

    public void sendMessage(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
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
}
