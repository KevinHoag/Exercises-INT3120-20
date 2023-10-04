package com.example.myapplication;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

public class App extends Application {

    public static final String CHANEL_ID = "foregroundService";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChanel();
    }
    public void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("App", "createNotificationChanel: ");
            NotificationChannel serviceChanel = new NotificationChannel(
                    CHANEL_ID,
                    "EXAMPLE SERVICE CHANNEL",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            serviceChanel.enableLights(true);
            serviceChanel.setLightColor(Color.RED);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChanel);
        }
    }
}