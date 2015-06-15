package com.ahowe.autotext.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ahowe.autotext.R;
import com.ahowe.autotext.TextListAdapter;
import com.ahowe.autotext.TextListCallbacks;
import com.ahowe.autotext.database.TextDataLayer;
import com.ahowe.autotext.models.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbruzek on 4/17/15.
 */
public class MessageLibraryActivity extends ActionBarActivity implements TextListCallbacks {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private TextDataLayer dataLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_message_library);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("AutoText");
        setSupportActionBar(toolbar);

        dataLayer = new TextDataLayer(this);
        dataLayer.openDB();

        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.new_text_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageLibraryActivity.this, NewMessageActivity.class);
                startActivity(intent);
            }
        });

        recycler = (RecyclerView) findViewById(R.id.text_list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        List<Text> list = dataLayer.getAllTexts(300);
        Log.d("ACTIVITY", "Got all texts, size: " + list.size());
        recycler.setAdapter(new TextListAdapter(this, this, list));
    }

    @Override
    protected void onPause() {
        dataLayer.closeDB();
        super.onPause();
    }

    @Override
    protected void onResume() {
        dataLayer.openDB();
        super.onResume();
    }
}
