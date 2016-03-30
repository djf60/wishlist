package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OtherListActivity extends Activity {
    private RecyclerView mRecyclerView;
    private OtherListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private WishList list;
    private ArrayList<ListItem> toPurchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_list);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        TextView titleBar = (TextView)findViewById(R.id.username);
        titleBar.setText(username+"'s List");
        //INIT LIST OBJECT W/ USERNAME HERE
        //...//
        list = new WishList();
        list.addItem(new ListItem( "pasta sauce", 1, "for my pastas", 99.99, "pasta.web", 0, false, null));
        list.addItem(new ListItem( "cheese wheel", 2, "circular cheese", 999.99, "cheese_world.web", 0, false, null));
        list.addItem(new ListItem( "grab bag", 1, "a bag to grab", 8, "bag_world.web", 0, false, null));
        list.addItem(new ListItem( "a new cat", 1, "My old one died", 13.45, "the cat factory", 0, false, null));

        toPurchase = new ArrayList<ListItem>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new OtherListAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addToPurchased(View view)
    {
        if (!view.isSelected())
        {
            toPurchase.add(list.getList().get((Integer)view.getTag()));
            view.setSelected(true);
        }
        else
        {
            toPurchase.remove(list.getList().get((Integer)view.getTag()));
            view.setSelected(false);
        }
    }

    public void confirmAdditions(View view)
    {
        //Update database w/ toPurchase
        for (ListItem l: toPurchase)
        {
            l.purchased = true;
            mAdapter.notifyItemChanged(list.getList().indexOf(l));
        }
        toPurchase.clear();
    }

    public void backToMenu(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }
}
