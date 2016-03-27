package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private List<ListItem> mDataset;
        public boolean[] selected;
        private int numSelected;
        private static Context context;
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView name;
            public TextView description;
            public TextView price;
            public TextView source;
            public TextView priority;
            public CheckBox select;
            public ViewHolder(View v) {
                super(v);
                name = (TextView)v.findViewById(R.id.name);
                description = (TextView)v.findViewById(R.id.description);
                price = (TextView)v.findViewById(R.id.price);
                source = (TextView)v.findViewById(R.id.src);
                priority = (TextView)v.findViewById(R.id.priority);
                select = new CheckBox(context);
                select.setSelected(false);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ListAdapter(WishList myDataset, Context c) {
            mDataset = myDataset.getList();
            context = c;
            selected = new boolean[myDataset.getList().size()];
            numSelected = 0;
        }

        public void addItem(ListItem item)
        {
            mDataset.add(0, item);
            notifyDataSetChanged();
        }

        public void editItem(int index, ListItem item)
        {
            mDataset.remove(index);
            mDataset.add(0, item);
            notifyDataSetChanged();
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
            // set the view's size, margins, paddings and layout parameters

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
            holder.priority.setText(item.getPriority());
            //setup checkbox clickListener
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox checkBox = (CheckBox)view;
                    if (checkBox.isSelected())
                    {
                        numSelected--;
                        checkBox.setSelected(false);
                        selected[(int) checkBox.getTag()] = false;
                    }
                    else
                    {
                        numSelected++;
                        checkBox.setSelected(true);
                        selected[(int)checkBox.getTag()] = true;
                    }
                }
            };
            holder.select.setTag(position);
            holder.select.setOnClickListener(clickListener);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public int getNumSelected()
        {
            return numSelected;
        }

        public void removeSelected()
        {
            for (int i = 0; i < selected.length; i++)
            {
                if (selected[i] == true)
                {
                    mDataset.remove(i);
                }
            }
            selected = new boolean[mDataset.size()];
            notifyDataSetChanged();
        }

}

