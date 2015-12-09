package com.github.mytravelsapp.presentation.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.github.mytravelsapp.R;

/**
 * @author fjtorres
 */
public class SplashActivity extends Activity {

    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                final Intent myTravelsIntent = new Intent(SplashActivity.this, TravelListActivity.class);
                SplashActivity.this.startActivity(myTravelsIntent);
                SplashActivity.this.finish();
            }
        }, DELAY);
    }
}
