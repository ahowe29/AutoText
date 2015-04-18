package com.ahowe.autotext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.software.shell.fab.ActionButton;

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
    }
}
