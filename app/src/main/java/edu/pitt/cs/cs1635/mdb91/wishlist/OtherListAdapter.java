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

public class OtherListAdapter extends RecyclerView.Adapter<OtherListAdapter.ViewHolder> {
    private List<ListItem> mDataset;
    private ArrayList<Integer> selected;
    private static Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        public TextView price;
        public TextView source;
        public TextView priority;
        public CheckBox select;
        public TextView isPurchased;
        public ViewHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.name);
            description = (TextView)v.findViewById(R.id.description);
            price = (TextView)v.findViewById(R.id.price);
            source = (TextView)v.findViewById(R.id.src);
            priority = (TextView)v.findViewById(R.id.priority);
            select = (CheckBox)v.findViewById((R.id.checkbox));
            select.setSelected(false);
            isPurchased = (TextView)v.findViewById(R.id.purchased);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OtherListAdapter(WishList myDataset, Context c) {
        mDataset = myDataset.getList();
        context = c;
        selected = new ArrayList<Integer>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OtherListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_list_row, parent, false);
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
        holder.description.setText(item.getDescription());
        holder.price.setText("$"+String.valueOf(item.getPrice()));
        holder.source.setText(item.getSource());
        holder.priority.setText("Priority: " + String.valueOf(item.getPriority()));
        holder.select.setTag(new Integer(position));
        if(item.isPurchased())
            holder.isPurchased.setText("This item has already been purchased");
        else holder.isPurchased.setText("This item has not yet been purchased!");
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
}

