package edu.pitt.cs.cs1635.mew115.wishlist;

/**
 * Created by ChildeQi on 3/27/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Find extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> nameList;
    private ArrayList<String> matchList;
    private MyAdapter userList;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        nameList = new ArrayList<String>();
        nameList.add("UsernameA");
        nameList.add("UsernameB");
        nameList.add("UsernameC");
        nameList.add("UsernameD");
        nameList.add("UsernameE");
        nameList.add("UsernameF");

        userList = new MyAdapter(nameList);



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
    //    mAdapter = new MyAdapter(nameList);
        mRecyclerView.setAdapter(userList);

        final TextView editText = (TextView) findViewById(R.id.searchText);
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                matchList = new ArrayList<String>();    // new empty list for matched users
                text = editText.getText().toString();
                for(int i = 0; i < nameList.size(); i++)
                {
                    if (text.equals(nameList.get(i)))
                    {
                        matchList.add(text);
                    }
                }
                userList = new MyAdapter(matchList);
                mRecyclerView.setAdapter(userList);
                }
            };
        findViewById(R.id.button).setOnClickListener(handler);
    }


}

