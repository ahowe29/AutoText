package com.ahowe.autotext;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity
{
    DatabaseHelper.DBAdapter dbHelper;
    private static int SPLASH_TIMEOUT = 2000;

    Timer timer;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_splash_screen);
        dbHelper = new DatabaseHelper.DBAdapter(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        //dbHelper.clearDB();
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
