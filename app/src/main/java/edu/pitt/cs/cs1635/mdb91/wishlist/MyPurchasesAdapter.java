package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 3/27/2016.
 */

public class MyPurchasesAdapter extends RecyclerView.Adapter<MyPurchasesAdapter.ViewHolder> {
    private List<ListItem> mDataset;
    private ArrayList<Integer> selected;
    private static Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView name;
        public TextView price;
        public TextView source;
        public TextView priority;
        public CheckBox select;
        public ViewHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.name);
            username = (TextView)v.findViewById(R.id.username);
            price = (TextView)v.findViewById(R.id.price);
            source = (TextView)v.findViewById(R.id.src);
            priority = (TextView)v.findViewById(R.id.priority);
            select = (CheckBox)v.findViewById((R.id.checkbox));
            select.setSelected(false);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyPurchasesAdapter(WishList myDataset, Context c) {
        mDataset = myDataset.getList();
        context = c;
        selected = new ArrayList<Integer>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyPurchasesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_purchased_list_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get item at that index
        ListItem item = mDataset.get(position);
        //set fields in viewholder
        holder.name.setText(item.getName());
        holder.username.setText("For: "+item.getUser());
        holder.price.setText("$"+String.valueOf(item.getPrice()));
        holder.source.setText(item.getSource());
        holder.priority.setText("Priority: " + String.valueOf(item.getPriority()));
        holder.select.setTag(new Integer(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public ArrayList<Integer> getSelected()
    {
        return selected;
    }

    public void removeSelected()
    {
        int lastRemoved = selected.get(0);
        int toRemove = selected.get(0);
        while(selected.size() > 0)
        {
            mDataset.remove(toRemove);
            lastRemoved = toRemove;
            notifyItemRemoved(toRemove);
            notifyItemRangeChanged(toRemove, mDataset.size());
            selected.remove(0);
            if (selected.size() == 0) break;
            toRemove = selected.get(0);
            if (toRemove >= lastRemoved)
                toRemove--;
        }

        //notifyDataSetChanged();

    }
}


