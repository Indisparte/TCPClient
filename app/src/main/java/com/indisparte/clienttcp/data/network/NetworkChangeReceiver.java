package com.indisparte.clienttcp.data.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.indisparte.clienttcp.R;

/**
 * Check for Internet connection. In case there is no connection,
 * it provides to show a Dialog with an option to try to connect again
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectedToInternet(context)) {//Internet is not connected
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            //Show dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setButton(
                    DialogInterface.BUTTON_POSITIVE,
                    context.getString(R.string.connection_dialog_retry_btn),
                    (dialogInterface, i) -> {
                        dialog.dismiss();
                        onReceive(context, intent);
                    });
            dialog.setCancelable(false);
            dialog.getWindow().setGravity(Gravity.CENTER);
        }
    }
}
