package com.seproject.reservemac.ui.login;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.seproject.reservemac.R;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {

    private boolean connection = false;
    private static String url = null;
    private static String loginurl = "http://zenithwrites.org/login.php";


    EditText usernameEditText = null;
    EditText passwordEditText = null;
    Button loginButton = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        assert connectivityManager != null;
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connection) {
                    loginUser();
                } else {
                    Toast.makeText(getApplicationContext(), "Switch on the Internet", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    public void loginUser() {


        String username = usernameEditText.getText().toString().trim().toLowerCase();
        if (usernameEditText.getText().toString().trim().equals("")) {
            usernameEditText.setError("The username is required");

            usernameEditText.setHint("Please enter your username");
        }

        String password = passwordEditText.getText().toString().trim().toLowerCase();

        if (passwordEditText.getText().toString().trim().equals("")) {
            passwordEditText.setError("Password is required!");

            passwordEditText.setHint("Please enter your Password");
        }

        login(username, password);

    }

    private void login(final String username, final String password) {
        new LoginUserClass(this).execute(username,password);
    }
}