package com.seproject.reservemac.ui.login;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.ui.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    private boolean connection = false;

    EditText usernameEditText = null;
    EditText passwordEditText = null;
    Button loginButton = null;
    Button registerButton = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));            //Setting the connectivity manager
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);

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

    registerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myintent = new Intent(getBaseContext(), RegisterActivity.class);
            startActivity(myintent);
        }
    });

    }


    public void loginUser() {

        String password = passwordEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        if (usernameEditText.getText().toString().trim().equals("")) {
            usernameEditText.setError("The username is required");

            usernameEditText.setHint("Please enter your username");
        } else if (passwordEditText.getText().toString().trim().equals("")) {
            passwordEditText.setError("Password is required!");

            passwordEditText.setHint("Please enter your Password");
        }

        else{
            String type = "login";
            Backgroundworker backgroundworker = new Backgroundworker(this);
            backgroundworker.execute(type,username,password);
        }
    }
}