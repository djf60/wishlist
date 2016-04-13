package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Reinhold on 4/12/16.
 */
public class AllMyListsAdapter extends RecyclerView.Adapter<AllMyListsAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView listName;
        private Context context;

        public ViewHolder(View v, Context c) {
            super(v);
            context = c;
            v.setClickable(true);
            v.setOnClickListener(this);
            listName = (TextView) v.findViewById(R.id.userName);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            String listName = mDataset.get(position);
            Intent intent = new Intent(context, MyListActivity.class);
            intent.putExtra("listName", listName);
            context.startActivity(intent);

        }
    }

    public void add(int position, String list) {
        mDataset.add(position, list);
        notifyItemInserted(position);
    }

    public void remove(String list) {
        int position = mDataset.indexOf(list);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public AllMyListsAdapter(ArrayList<String> myDataset, Context c) {
        context = c;
        mDataset = myDataset;
    }

    @Override
    public AllMyListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = mDataset.get(position);
        holder.listName.setText(name);

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
