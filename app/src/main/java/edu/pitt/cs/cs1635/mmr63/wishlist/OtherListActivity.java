package edu.pitt.cs.cs1635.mmr63.wishlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class OtherListActivity extends Activity {
    private RecyclerView mRecyclerView;
    private OtherListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private WishList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_list);
    }
}
