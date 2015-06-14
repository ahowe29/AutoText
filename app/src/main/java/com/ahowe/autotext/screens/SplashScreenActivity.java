package com.ahowe.autotext.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ahowe.autotext.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity
{
    private static int SPLASH_TIMEOUT = 2000;

    Timer timer;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash_screen);
        EndSplash();

    }

    public void EndSplash()
    {
        timer = new Timer(true);
        timer.schedule(new SplashTask(), SPLASH_TIMEOUT);
    }

    class SplashTask extends TimerTask {
        public void run()
        {
            Intent intro1 = new Intent(SplashScreenActivity.this, MessageLibraryActivity.class);
            timer.cancel();
            startActivity(intro1);
            finish();
        }
    }

}
