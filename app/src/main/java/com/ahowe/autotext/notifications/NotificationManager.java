package com.ahowe.autotext.notifications;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.ahowe.autotext.R;

/**
 * Handles the creation and display of notifications
 *
 * Created by jbruzek on 8/24/15.
 *
 */
public class NotificationManager {
    public static void buildNotification(Context c, String title, String content) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(c)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Content");
    }
}
