package edu.pitt.cs.cs1635.mdb91.wishlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();
        username = intent.getStringExtra(username);
    }

    public void gotoMyLists(View view)
    {
        /*
        //go to my lists
        Intent intent = new Intent(this, MyListActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        */
        Intent intent = new Intent(this, AllMyListsActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    public void gotoPurchased(View view)
    {

        Intent intent = new Intent(this, MyPurchases.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    public void gotoFindLists(View view)
    {

        Intent intent = new Intent(this, Find.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    public void logout(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
