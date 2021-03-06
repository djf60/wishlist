package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MyListActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private WishList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        //initialize list object

        //
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(list, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addItem(View view)
    {
        final ListItem newItem = new ListItem(null, 0, null, 0, null, 0, false, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.add_edit_dialogue, null));
        builder.setTitle("Add an Item");
        builder.setPositiveButton("Add item", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText field = (EditText)findViewById(R.id.add_name);
                String name = field.getText().toString();
                newItem.name = name;

                field = (EditText)findViewById(R.id.add_description);
                String description = field.getText().toString();
                newItem.description = description;

                field = (EditText)findViewById(R.id.add_price);
                double price = Double.parseDouble(field.getText().toString());
                newItem.price = price;

                field = (EditText)findViewById(R.id.add_source);
                String source = field.getText().toString();
                newItem.source = source;

                field = (EditText)findViewById(R.id.add_priority);
                int priority = Integer.parseInt(field.getText().toString());
                newItem.priority = priority;

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
            }
        });

        if (newItem.name != null)
            mAdapter.addItem(newItem);
    }

    public void editItem(View view)
    {
        if (mAdapter.getNumSelected() > 1)
        {
            Context context = getApplicationContext();
            CharSequence text = "Only one item can be edited at once";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if (mAdapter.getNumSelected() == 0){}
        else
        {
            int index = 0;
            for (int i = 0; i < mAdapter.selected.length; i++)
            {
                if (mAdapter.selected[i] == true)
                {
                    index = i;
                    break;
                }
            }
            final ListItem newItem = new ListItem(null, 0, null, 0, null, 0, false, null);
            ListItem oldItem = list.getList().get(index);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Get the layout inflater
            LayoutInflater inflater = this.getLayoutInflater();
            // Inflate and set the layout for the dialog
            builder.setView(inflater.inflate(R.layout.add_edit_dialogue, null));
            //preload fields
            EditText field = (EditText)findViewById(R.id.add_name);
            field.setText(oldItem.getName());

            field = (EditText)findViewById(R.id.add_priority);
            field.setText(oldItem.getPriority());

            field = (EditText)findViewById(R.id.add_price);
            field.setText(String.valueOf(oldItem.getPrice()));

            field = (EditText)findViewById(R.id.add_description);
            field.setText(oldItem.getDescription());

            field = (EditText)findViewById(R.id.add_source);
            field.setText(oldItem.getSource());
            //
            builder.setTitle("Edit Item");
            builder.setPositiveButton("Confirm Changes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    EditText field = (EditText)findViewById(R.id.add_name);
                    String name = field.getText().toString();
                    newItem.name = name;

                    field = (EditText)findViewById(R.id.add_description);
                    String description = field.getText().toString();
                    newItem.description = description;

                    field = (EditText)findViewById(R.id.add_price);
                    double price = Double.parseDouble(field.getText().toString());
                    newItem.price = price;

                    field = (EditText)findViewById(R.id.add_source);
                    String source = field.getText().toString();
                    newItem.source = source;

                    field = (EditText)findViewById(R.id.add_priority);
                    int priority = Integer.parseInt(field.getText().toString());
                    newItem.priority = priority;

                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    dialog.dismiss();
                }
            });

            mAdapter.editItem(index, newItem);
        }
    }

    public void removeItems(View view)
    {
        mAdapter.removeSelected();
    }
}
