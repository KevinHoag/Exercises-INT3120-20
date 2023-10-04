package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String message = "Xin chào từ MainActivity";
        Intent intent = new Intent(this, HelloIntentService.class);
        intent.putExtra("message", message);
        startService(intent);

        Button startServiceButton = findViewById(R.id.start_service_button);
        Button stopServiceButton = findViewById(R.id.stop_service_button);

        startServiceButton.setOnClickListener(view -> startHelloService());

        stopServiceButton.setOnClickListener(view -> stopHelloService());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 2131230949:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 2131230950:
                break;
        }
        return true;
    }

    private void startHelloService() {
        Intent serviceIntent = new Intent(this, HelloService.class);
        startService(serviceIntent);
    }

    private void stopHelloService() {
        Intent serviceIntent = new Intent(this, HelloService.class);
        stopService(serviceIntent);
    }
}