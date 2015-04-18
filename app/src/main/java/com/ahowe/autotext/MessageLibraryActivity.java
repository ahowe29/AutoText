package com.ahowe.autotext;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by jbruzek on 4/17/15.
 */
public class MessageLibraryActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_message_library);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("AutoText");
        setSupportActionBar(toolbar);
    }
}
