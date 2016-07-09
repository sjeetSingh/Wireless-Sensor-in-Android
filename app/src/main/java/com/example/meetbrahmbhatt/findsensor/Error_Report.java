package com.example.meetbrahmbhatt.findsensor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by meetbrahmbhatt on 5/2/16.
 */
public class Error_Report extends App_Dialog<Error_Report.ErrorDialogListener> {
    public static final String TAG = Error_Report.class.getSimpleName();

    public interface ErrorDialogListener {
        public void onDismiss(Error_Report f);
    }

    private int message;

    public static Error_Report newInstance(int message) {
        final Error_Report dialog = new Error_Report();
        dialog.message = message;

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(null)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getListener() != null)
                            getListener().onDismiss(Error_Report.this);
                    }
                })
                .create();
    }
}