package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AllMyListsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> listNames;
    private AllMyListsAdapter allLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_my_lists);
        listNames = new ArrayList<String>();
        listNames.add("Birthday");

        allLists = new AllMyListsAdapter(listNames, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.all_my_lists_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(allLists);



    }

    public void addList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_list_dialogue, null));
        builder.setTitle("Add a List");
        builder.setNeutralButton("Add List", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_list);
                String name = field.getText().toString();
                //listNames.add(name);
                AllMyListsActivity.this.allLists.add(0, name);
                dialog.dismiss();
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void backToMenu(View v)
    {
        NavUtils.navigateUpFromSameTask(this);
    }
}
