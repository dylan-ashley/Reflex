package ca.ualberta.dashley_reflex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by dashley on 2015-09-26.
 */
public class SimpleDialog {

    // MysticMagicϡ; http://stackoverflow.com/questions/26097513/android-simple-alert-dialog; 2015-09-26
    public SimpleDialog(String message, Activity activity) {
        AlertDialog alertDialog= new AlertDialog.Builder(activity).create();
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
