package com.example.meetbrahmbhatt.findsensor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by meetbrahmbhatt on 5/2/16.
 */
public class Enable_Bluetooth extends App_Dialog<Enable_Bluetooth.EnableBluetoothDialogListener> {
    public static final String TAG = Enable_Bluetooth.class.getSimpleName();

    public interface EnableBluetoothDialogListener {
        public void onEnableBluetooth(Enable_Bluetooth f);
        public void onCancel(Enable_Bluetooth f);
    }

    public Enable_Bluetooth() {
        setCancelable(false);
    }

    @Override
    protected boolean isListenerOptional() {
        return false;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(null)
                .setMessage(R.string.dialog_enable_bluetooth)
                .setPositiveButton(R.string.turn_on, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        getListener().onEnableBluetooth(Enable_Bluetooth.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getListener().onCancel(Enable_Bluetooth.this);
                    }
                })
                .setCancelable(false);

        return builder.create();
    }
}