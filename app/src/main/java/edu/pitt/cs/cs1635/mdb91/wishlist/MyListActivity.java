package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyListActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private WishList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        Intent intent = getIntent();
        String title = intent.getStringExtra("listName");
        TextView titleView = (TextView)findViewById(R.id.title);
        titleView.setText(title);
        //initialize list object
        list = new WishList();
        list.addItem(new ListItem( "play-doh", 1, "that fake clay stuff", .99, "playdoh.web", 0, false, null));
        list.addItem(new ListItem( "iphone 500s", 2, "don't like the 499", 999.99, "apple store", 0, false, null));
        list.addItem(new ListItem( "sno-cone", 3, "preferably cherry", .50, "the sno cone zone", 0, false, null));
        list.addItem(new ListItem( "a bike", 1, "just a bike", 13.45, "bike store", 0, false, null));
        list.addItem(new ListItem( "cds", 4, "they're collector's items", 20.45, "the dump", 0, false, null));
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
        final ListItem newItem = new ListItem("hut", 0, "but", 0, "chunt", 0, false, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.add_edit_dialogue, null));
        builder.setTitle("Add an Item");
        builder.setNeutralButton("Add item", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_name);
                String name = field.getText().toString();
                newItem.name = name;

                field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_description);
                String description = field.getText().toString();
                newItem.description = description;

                field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_price);
                double price = Double.parseDouble(field.getText().toString());
                newItem.price = price;

                field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_source);
                String source = field.getText().toString();
                newItem.source = source;

                field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_priority);
                int priority = Integer.parseInt(field.getText().toString());
                newItem.priority = priority;

                MyListActivity.this.mAdapter.addItem(newItem);
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

    public void editItem(View view)
    {
        Log.d("DEBUG", "In MyList.editItem.  selected.size() = " + mAdapter.getSelected().size());
        if (mAdapter.getSelected().size() > 1)
        {
            Context context = getApplicationContext();
            CharSequence text = "Only one item can be edited at once";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if (mAdapter.getSelected().size() == 0){}
        else
        {
            final int index = mAdapter.getSelected().get(0);
            final ListItem newItem = new ListItem(null, 0, null, 0, null, 0, false, null);
            ListItem oldItem = list.getList().get(index);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Get the layout inflater
            LayoutInflater inflater = this.getLayoutInflater();
            // Inflate and set the layout for the dialog
            final View alertView = inflater.inflate(R.layout.add_edit_dialogue, null);
            builder.setView(alertView);
            //preload fields
            EditText field = (EditText)alertView.findViewById(R.id.add_name);
            field.setText(oldItem.getName());

            field = (EditText)alertView.findViewById(R.id.add_priority);
            field.setText(String.valueOf(oldItem.getPriority()));

            field = (EditText)alertView.findViewById(R.id.add_price);
            field.setText(String.valueOf(oldItem.getPrice()));

            field = (EditText)alertView.findViewById(R.id.add_description);
            field.setText(oldItem.getDescription());

            field = (EditText)alertView.findViewById(R.id.add_source);
            field.setText(oldItem.getSource());
            //
            builder.setTitle("Edit Item");
            builder.setNeutralButton("Confirm Changes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    EditText field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_name);
                    String name = field.getText().toString();
                    newItem.name = name;

                    field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_description);
                    String description = field.getText().toString();
                    newItem.description = description;

                    field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_price);
                    double price = Double.parseDouble(field.getText().toString());
                    newItem.price = price;

                    field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_source);
                    String source = field.getText().toString();
                    newItem.source = source;

                    field = (EditText) ((AlertDialog) dialog).findViewById(R.id.add_priority);
                    int priority = Integer.parseInt(field.getText().toString());
                    newItem.priority = priority;

                    mAdapter.editItem(index, newItem);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.show();

            View selected = mRecyclerView.getChildAt(index);
            CheckBox c = (CheckBox)selected.findViewById(R.id.checkbox);
            c.setSelected(false);
            c.setChecked(false);
        }
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

    public void gotoMyLists(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }
}
