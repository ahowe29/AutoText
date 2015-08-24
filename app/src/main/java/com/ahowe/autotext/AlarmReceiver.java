package com.ahowe.autotext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.ahowe.autotext.notifications.NotificationManager;

/**
 * Created by Stephen on 2/8/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String phone = intent.getStringExtra("Contact");
        String message = intent.getStringExtra("Message");

        String toast = "Sending " + message + " to " + phone;

        //send a notification
        NotificationManager.buildNotification(context, toast, toast);

        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, null, null);


    }
}