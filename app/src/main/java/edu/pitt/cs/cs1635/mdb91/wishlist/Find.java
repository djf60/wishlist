package edu.pitt.cs.cs1635.mdb91.wishlist;

/**
 * Created by ChildeQi on 3/27/16.
 */

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
        nameList.add("Uncle Fester");
        nameList.add("Peter");
        nameList.add("Tom Jones");
        nameList.add("Bigsby");
        nameList.add("Charlemagne");
        nameList.add("Old Greg");
        nameList.add("Tim");
        nameList.add("Eric");

        userList = new MyAdapter(nameList, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
    //    mAdapter = new MyAdapter(nameList);
        mRecyclerView.setAdapter(userList);

        final TextView editText = (TextView) findViewById(R.id.searchText);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                matchList = new ArrayList<String>();    // new empty list for matched users
                text = editText.getText().toString();
                for (int i = 0; i < nameList.size(); i++) {
                    if (nameList.get(i).toLowerCase().contains(text.toLowerCase())) {
                        matchList.add(nameList.get(i));
                    }
                }
                userList = new MyAdapter(matchList, editText.getContext());
                mRecyclerView.setAdapter(userList);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        editText.addTextChangedListener(textWatcher);

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                matchList = new ArrayList<String>();    // new empty list for matched users
                text = editText.getText().toString();
                for(int i = 0; i < nameList.size(); i++)
                {
                    if (nameList.get(i).toLowerCase().contains(text.toLowerCase()))
                    {
                        matchList.add(nameList.get(i));
                    }
                }
                userList = new MyAdapter(matchList, v.getContext());
                mRecyclerView.setAdapter(userList);
                }
            };
        findViewById(R.id.button).setOnClickListener(handler);
    }

    public void backToMenu(View v)
    {
        NavUtils.navigateUpFromSameTask(this);
    }

}

