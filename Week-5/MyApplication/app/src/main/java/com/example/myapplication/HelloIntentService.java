package com.example.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class HelloIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 1;

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("asdas", intent.getStringExtra("message"));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, App.CHANEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Tiêu đề thông báo")
                .setContentText("Hello World")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
