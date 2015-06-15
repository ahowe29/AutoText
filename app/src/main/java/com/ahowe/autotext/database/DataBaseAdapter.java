package com.ahowe.autotext.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class manages the SQLite database
 *
 * Created by jbruzek on 6/14/15.
 */
public class DataBaseAdapter extends SQLiteOpenHelper {

    public static final String TABLE_TEXTS = "texts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_RECEIVER_NAME = "rec_name";
    public static final String COLUMN_RECEIVER_NUMBER = "rec_num";
    public static final String COLUMN_CREATION_DATE = "date_create";
    public static final String COLUMN_SEND_DATE = "date_send";

    private static final String DATABASE_NAME = "autotext.db";
    private static final int DATABASE_VERSION = 4;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_TEXTS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_TEXT + " text not null, " +
            COLUMN_RECEIVER_NAME + " text not null, " +
            COLUMN_RECEIVER_NUMBER + " text not null, " +
            COLUMN_CREATION_DATE + " integer not null, " +
            COLUMN_SEND_DATE + " integer not null" +
            ");";

    /**
     * Constructor
     * @param context
     */
    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create the database by executing the creation SQL
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /**
     * Handle the upgrading of the database to a new version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseAdapter.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEXTS);
        onCreate(db);
    }

}