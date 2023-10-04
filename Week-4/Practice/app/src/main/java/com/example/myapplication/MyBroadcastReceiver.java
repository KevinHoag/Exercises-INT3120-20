package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.TextView;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private TextView textView;

    public MyBroadcastReceiver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isTurnedOn = isAirplaneModeOn(context);
            updateTextView(isTurnedOn);
        }
    }

    private boolean isAirplaneModeOn(Context context) {
        return Settings.Global.getInt(
                context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON,
                0
        ) != 0;
    }

    private void updateTextView(boolean isTurnedOn) {
        textView.setText("Airplane mode is " + (isTurnedOn ? "on" : "off"));
    }
}
