package com.ahowe.autotext.screens;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ahowe.autotext.AlarmReceiver;
import com.ahowe.autotext.pickers.DatePickerFragment;
import com.ahowe.autotext.R;
import com.ahowe.autotext.pickers.TimePickerFragment;
import com.ahowe.autotext.database.TextDataLayer;
import com.ahowe.autotext.models.Contact;
import com.ahowe.autotext.models.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jbruzek on 4/17/15.
 */
public class NewMessageActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final int CONTACT_PICKER_RESULT = 501;

    private TextDataLayer dataLayer;
    private Toolbar toolbar;
    private Toolbar contactBar;
    private EditText text;
    private Button send;
    private Button date;
    private Button timePicker;
    private Contact contact;
    int year = -1;
    int month = -1;
    int day = -1;
    int hour = -1;
    int min = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_message);

        toolbar = (Toolbar) findViewById(R.id.new_message_tool_bar);
        toolbar.setTitle("New Message");
//        toolbar.setSubtitle("To");
//        toolbar.inflateMenu(R.menu.contact_menu);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.find_contact) {
//                    Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//                    startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
//                    return true;
//                }
//                return false;
//            }
//        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        contactBar = (Toolbar) findViewById(R.id.contact_toolbar);
        contactBar.setSubtitle("To");
        contactBar.inflateMenu(R.menu.contact_menu);
        contactBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.find_contact) {
                    Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
                    return true;
                }
                return false;
            }
        });

        text = (EditText) findViewById(R.id.new_message_input);
        send = (Button) findViewById(R.id.send_button);
        date = (Button) findViewById(R.id.date_button);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment f = new DatePickerFragment();
                f.show(getFragmentManager(), "Date Picker");
            }
        });
        timePicker = (Button) findViewById(R.id.time_button);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment f = new TimePickerFragment();
                f.show(getFragmentManager(), "Time Fragment");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText();
            }
        });

        dataLayer = new TextDataLayer(this);
        dataLayer.openDB();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK) {
            switch(requestCode){
                case CONTACT_PICKER_RESULT:
                    Uri result = data.getData();
                    handleContactSelected(result);
                    break;
            }
        }
    }

    private void handleContactSelected(Uri result)
    {
        String id = result.getLastPathSegment();
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(result, null, null, null, null);
        cursor.moveToFirst();

        try {
            contact = new Contact();
            int c = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int c2 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            contact = new Contact(cursor.getString(c), cursor.getString(c2));
            contactBar.setSubtitle(contact.getName());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendText() {
        GregorianCalendar cal = new GregorianCalendar();

        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        intentAlarm.putExtra("Contact", contact.getNumber());
        intentAlarm.putExtra("Message", text.getText().toString());

        long time = 0;
        if (year == -1 || month == -1 || day == -1 || hour == -1 || min == -1) {
            Toast.makeText(this, "Please select a date to send the text", Toast.LENGTH_SHORT).show();
            return;
        }

        cal.set(year,month,day,hour,min,0);
        time = cal.getTimeInMillis();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time,
                PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        DateFormat df = DateFormat.getDateTimeInstance();

        Toast.makeText(this, "Text scheduled for " + df.format(new Date(time)),
                Toast.LENGTH_LONG).show();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        Text newText = new Text();
        newText.setMessage(text.getText().toString());
        newText.setRecipient(contact);
        newText.setSendDate(time);
        newText.setCreationDate(new Date().getTime());
        dataLayer.insertText(newText);

        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.min = minute;
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