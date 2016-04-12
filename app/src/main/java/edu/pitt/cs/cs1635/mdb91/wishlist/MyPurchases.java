package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MyPurchases extends Activity {
    private RecyclerView mRecyclerView;
    private MyPurchasesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private WishList list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchases);

        list = new WishList();
        list.addItem(new ListItem( "pasta sauce", 1, "for my pastas", 99.99, "pasta.web", 0, false, "Jeb"));
        list.addItem(new ListItem( "cheese wheel", 2, "circular cheese", 999.99, "cheese_world.web", 0, false, "Malacai"));
        list.addItem(new ListItem("grab bag", 1, "a bag to grab", 8, "bag_world.web", 0, false, "Scuba Steve"));
        list.addItem(new ListItem("a new cat", 1, "My old one died", 13.45, "the cat factory", 0, false, "Aunt Ant"));

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyPurchasesAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void removeItems(View view)
    {
        Log.d("DEBUG", "In MyList.removeItems, selected.size() = " + mAdapter.getSelected().size());
        if (mAdapter.getSelected().size() > 0)
            mAdapter.removeSelected();
    }

    public void addToSelected(View view)
    {
        Log.d("DEBUG", "In adapter.addtoselected.  Checkbox at position " + view.getTag() + " clicked.  IsSelected = " + view.isSelected());
        if (!view.isSelected())
        {
            mAdapter.getSelected().add((Integer)view.getTag());
            view.setSelected(true);
        }
        else
        {
            mAdapter.getSelected().remove(view.getTag());
            view.setSelected(false);
        }
        Log.d("DEBUG", "End addToSelected.  isSelected now = "+view.isSelected());
    }

    public void backToMenu(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }
}
