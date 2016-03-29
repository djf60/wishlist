package edu.pitt.cs.cs1635.mmr63.wishlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {

    EditText userBox;
    EditText passwordBox;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userBox = (EditText)findViewById(R.id.username_input);
        passwordBox = (EditText)findViewById(R.id.password_input);
        prefs = getPreferences(MODE_PRIVATE);
        userBox.setText(prefs.getString("username", ""));
        passwordBox.setText(prefs.getString("password", ""));
    }

    public void login(View view)
    {
        String username = userBox.getText().toString();
        String password = passwordBox.getText().toString();
        boolean isValid = validate(username, password);
        if (isValid)
        {
            //save username and pword in preferences
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putString("username", username);
            prefEditor.putString("password", password);
            prefEditor.commit();
            //for testing purposes
            Context context = getApplicationContext();
            CharSequence text = "Login Successful!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //launch main menu activity

            Intent intent = new Intent(this, MainMenu.class);
            intent.putExtra("username", username);
            startActivity(intent);

        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Incorrect Username or Password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void quit(View view)
    {
        finish();
    }

    private boolean validate(String user, String password)
    {
        return true; //dummy implementation for now
    }
}
