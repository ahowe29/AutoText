package com.ahowe.autotext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Stephen on 2/8/2015.
 */
public final class DatabaseHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DelayedMessageEntry.TABLE_NAME + " (" +
                    DelayedMessageEntry._ID + " INTEGER PRIMARY KEY," +
                    DelayedMessageEntry.COLUMN_NAME_CONTACT + TEXT_TYPE + COMMA_SEP +
                    DelayedMessageEntry.COLUMN_NAME_MESSAGE + TEXT_TYPE + COMMA_SEP +
                    DelayedMessageEntry.COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
                    DelayedMessageEntry.COLUMN_NAME_MONTH + TEXT_TYPE + COMMA_SEP +
                    DelayedMessageEntry.COLUMN_NAME_DAY + TEXT_TYPE + COMMA_SEP +
                    DelayedMessageEntry.COLUMN_NAME_HOUR + TEXT_TYPE + COMMA_SEP +
                    DelayedMessageEntry.COLUMN_NAME_MINUTE + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DelayedMessageEntry.TABLE_NAME;

    public static abstract class DelayedMessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "AutoSMS";
        public static final String COLUMN_NAME_CONTACT = "contact";
        public static final String COLUMN_NAME_MESSAGE = "message";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_HOUR = "hour";
        public static final String COLUMN_NAME_MINUTE = "minute";
    }

    public static class DBAdapter extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public DBAdapter(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.i("TESTING", "onCreate called");
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public void clearDB() {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(SQL_DELETE_ENTRIES);
        }
    }
}
