package com.ahowe.autotext.list;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jbruzek on 6/15/15.
 */
public class ListItemTouchListener {

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback;
    ItemTouchHelper itemTouchHelper;

    public ListItemTouchListener() {
        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                if( swipeDir == ItemTouchHelper.LEFT) {
                    Log.d("SWIPE", "Left");
                } else {
                    Log.d("SWIPE", "Right");
                }
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    }

    public void addRecycler(RecyclerView view) {
        itemTouchHelper.attachToRecyclerView(view);
    }
}
