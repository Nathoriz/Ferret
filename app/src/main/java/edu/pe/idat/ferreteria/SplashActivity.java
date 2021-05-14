package edu.pe.idat.ferreteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent MainActivityIntent = new Intent(this,MainActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException ex) {
                } finally {
                    startActivity(MainActivityIntent);
                    finish();
                }
            }
        };
        timer.start();
    }
}