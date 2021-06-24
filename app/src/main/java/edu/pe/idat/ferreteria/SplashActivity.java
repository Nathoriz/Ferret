package edu.pe.idat.ferreteria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent LoginActivityIntent = new Intent(this,LoginActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException ex) {
                } finally {
                    startActivity(LoginActivityIntent);
                    finish();
                }
            }
        };
        timer.start();
    }
}