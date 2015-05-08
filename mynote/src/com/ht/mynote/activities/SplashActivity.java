package com.ht.mynote.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends Activity implements Runnable {
    @Override
    public void run() {
        SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
        boolean autoLogin = sp.getBoolean("autoLogin", false);
        String name = sp.getString("name", null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (name == null) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            if (autoLogin) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }

        }

    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread(this);
        thread.start();
    }
}
