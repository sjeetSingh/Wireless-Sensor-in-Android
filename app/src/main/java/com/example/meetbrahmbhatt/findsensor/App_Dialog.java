package com.example.meetbrahmbhatt.findsensor;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by meetbrahmbhatt on 5/2/16.
 */
public class App_Dialog<Listener> extends DialogFragment {

    private Listener listener_1;

    public Listener getListener() {
        return listener_1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public boolean isShowing() {
        final Dialog dialog = getDialog();
        return dialog != null && dialog.isShowing();
    }

    protected boolean isListenerOptional() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener_1 = (Listener) activity;
        } catch (ClassCastException e) {
            if ( !isListenerOptional() )
                throw new ClassCastException(activity.getClass().getName() + " must implement listener");
        }
    }

    // Hack for android issue 17423 in the compatibility library
    @Override
    public void onDestroyView() {
        if ( getDialog() != null && getRetainInstance() )
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }
}
