package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.FileProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1;

    private static final int VIDEO_REQUEST = 1;
    private SensorManager mSensorManager;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView movementDetection = findViewById(R.id.movement_detection);
        TextView wifiConnectivity = findViewById(R.id.wifi_connectivity);
        SwitchCompat switchButton = findViewById(R.id.switch_button);
        Button callButton = findViewById(R.id.call_button);
        Button smsButton = findViewById(R.id.sms_button);
        Button cameraButton = findViewById(R.id.camera_button);
        Button videoButton = findViewById(R.id.video_button);

        imageView = findViewById(R.id.my_image_view);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getSensorList(Sensor.TYPE_MOTION_DETECT) != null) {
            movementDetection.setText("There is a movement detection");
        } else {
            movementDetection.setText("Null");
        }

        BroadcastReceiver connectivityChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                    if (activeNetwork != null && activeNetwork.isConnected()) {
                        wifiConnectivity.setText("Đã kết nối vào mạng");
                    } else {
                        wifiConnectivity.setText("Không có kết nối mạng");
                    }
                }
            }
        };

        switchButton.setOnClickListener(v -> {
            if (switchButton.isChecked()) {
                Intent settingsIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                startActivity(settingsIntent);

                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(connectivityChangedReceiver, filter);
            } else {
                Intent settingsIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                startActivity(settingsIntent);

                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(connectivityChangedReceiver, filter);
            }
        });

        callButton.setOnClickListener(v -> {
            Intent whoYouGonnaCall = new Intent(Intent.ACTION_DIAL);
            whoYouGonnaCall.setData(Uri.parse("tel:555-2368"));
            startActivity(whoYouGonnaCall);
        });

        smsButton.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:55512345"));
            smsIntent.putExtra("sms_body", "Press send to send me");
            startActivity(smsIntent);
        });

        cameraButton.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });

        videoButton.setOnClickListener(v -> {
            File videoFile = new File(getExternalFilesDir(null), "myvideo.mp4");
            Uri videoUri = FileProvider.getUriForFile(getApplicationContext(), "com.example.myapplication.fileprovider", videoFile);

            Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);
            videoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        });

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityChangedReceiver, filter);

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Toast.makeText(this, wifi.getConnectionInfo().toString(), Toast.LENGTH_SHORT).show();
    }
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        } else if (requestCode == VIDEO_REQUEST) {
            Uri videoUri = data.getData();

            Glide.with(this)
                    .load(videoUri)
                    .into(imageView);
        }
    }
}

