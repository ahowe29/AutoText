package com.ahowe.autotext.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ahowe.autotext.models.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbruzek on 6/14/15.
 */
public class TextDataLayer {

    private SQLiteDatabase database;
    private DataBaseAdapter dbAdapter;
    private String[] allColumns = {
            dbAdapter.COLUMN_ID,
            dbAdapter.COLUMN_TEXT
    };

    /**
     * Constructor. Initialize the DatabaseAdapter.
     * @param context
     */
    public TextDataLayer(Context context) {
        dbAdapter = new DataBaseAdapter(context);
    }

    /**
     * open the database for use
     */
    public void openDB() {
        database = dbAdapter.getWritableDatabase();
    }

    /**
     * Close the database when we're done
     */
    public void closeDB() {
        dbAdapter.close();
    }

    /**
     * insert a text into the database
     * @param text
     */
    public void insertText(Text text) {
        ContentValues values = new ContentValues();
        values.put(dbAdapter.COLUMN_TEXT, text.getMessage());
        database.insert(dbAdapter.TABLE_TEXTS, null, values);
    }

    /**
     * Get a cursor for the database result of querying all texts
     * @param limit the limit for how many texts you want to receive
     * @return
     */
    public Cursor getAllTextsCursor(int limit) {
        return database.query(dbAdapter.TABLE_TEXTS,
                allColumns, null, null, null, null, null, String.valueOf(limit));
    }

    /**
     * Get a list of all the texts in the database
     * @param limit the limit for how many texts you want to retrieve.
     * @return a list of texts.
     */
    public List<Text> getAllTexts(int limit) {
        List<Text> texts = new ArrayList<Text>();

        Cursor cursor = getAllTextsCursor(limit);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            texts.add(getText(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("DATABASE", "getAllTexts, size: " + texts.size());
        return texts;
    }

    /**
     * Get a text object from a cursor.
     * @param cursor
     * @return
     */
    private Text getText(Cursor cursor) {
        Text text = new Text();
        text.setId(cursor.getLong(0));
        text.setMessage(cursor.getString(1));
        return text;
    }

}
