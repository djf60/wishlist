package edu.pitt.cs.cs1635.mdb91.wishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AddEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Intent intent = getIntent();
        String addOrEdit = intent.getStringExtra("mode");
        if (addOrEdit.equals("add"))
        {
            //code to add a new item
        }
        else
        {
            //code to edit old item
        }
    }
}
