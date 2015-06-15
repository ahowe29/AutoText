package com.ahowe.autotext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahowe.autotext.models.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jbruzek on 4/18/15.
 */
public class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.ViewHolder> {

    private TextListCallbacks tcb;
    private Context context;
    private List<Text> items;

    public TextListAdapter(TextListCallbacks cb, Context c, List<Text> list) {
        tcb = cb;
        context = c;
        items = new ArrayList<Text>();
        items.addAll(list);
        Log.d("ADAPTER", "initializing adapter, data size: " + items.size());
    }

    /**
     * ViewHolders populate the RecyclerView. Each item in the list is contained in a VewHolder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context2;
        View parent;
        TextView message;
        TextView number;
        TextView time;


        public ViewHolder(View itemView,int ViewType, Context c) {
            super(itemView);
            this.parent = itemView;

            context2 = c;

            message = (TextView) itemView.findViewById(R.id.item_messagetext);
            number = (TextView) itemView.findViewById(R.id.item_number);
            time = (TextView) itemView.findViewById(R.id.item_time);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            }

        @Override
        public void onClick(View v) {
            //handle a click event
            Toast.makeText(context, "Clicked " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public TextListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_list, parent, false);
        ViewHolder vhItem = new ViewHolder(v,viewType, context);
        return vhItem;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //do stuff with the VH items
        Text t = items.get(i);

        viewHolder.message.setText(t.getMessage());
        viewHolder.number.setText(t.getRecipient().getNumber());
        viewHolder.time.setText(DateFormat.format("MM/dd/yyyy", new Date(t.getSendDate())).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
