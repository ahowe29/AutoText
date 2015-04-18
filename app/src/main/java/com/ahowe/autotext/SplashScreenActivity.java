package com.ahowe.autotext;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.autosms.com.example.autosms.database.DelayedMessageContract;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity
{
    DelayedMessageContract.DelayedMessageDbHelper dbHelper;

    Timer timer;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.splash_screen);
        dbHelper = new DelayedMessageContract.DelayedMessageDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        //dbHelper.clearDB();
        EndSplash();

    }

    public void EndSplash()
    {
        timer = new Timer(true);
        timer.schedule(new SplashTask(), 4000);
    }

    class SplashTask extends TimerTask {
        public void run()
        {
            Intent intro1 = new Intent(SplashScreenActivity.this, MessageLibraryActivity.class);
            timer.cancel();
            startActivity(intro1);
        }
    }

}
