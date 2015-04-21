package com.ahowe.autotext;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbruzek on 4/17/15.
 */
public class MessageLibraryActivity extends ActionBarActivity implements TextListCallbacks {

    private Toolbar toolbar;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_message_library);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("AutoText");
        setSupportActionBar(toolbar);

        ActionButton actionButton = (ActionButton) findViewById(R.id.new_text_button);
        actionButton.setButtonColor(getResources().getColor(R.color.accent));
        actionButton.setButtonColorPressed(getResources().getColor(R.color.accentDark));
        actionButton.setImageDrawable(getResources().getDrawable(R.drawable.fab_plus_icon));
        actionButton.show();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageLibraryActivity.this, NewMessageActivity.class);
                startActivity(intent);
            }
        });

        recycler = (RecyclerView) findViewById(R.id.text_list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        SQLiteDatabase db = new DatabaseHelper.DBAdapter(this).getWritableDatabase();
//        List<Text> t = DatabaseHelper.getMessages(db);
//        Log.d("items", "" + t.size());
//        for (int i = 0; i < t.size(); i++) {
//            Log.d("item " + i, "" + t.get(i).number());
//            Log.d("item " + i, "" + t.get(i).sendDate());
//        }
        recycler.setAdapter(new TextListAdapter(this, this, DatabaseHelper.getMessages(db)));
    }
}
