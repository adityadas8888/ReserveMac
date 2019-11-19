package com.seproject.reservemac.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seproject.reservemac.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username = null;
    EditText password = null;
    EditText email = null;
    EditText role = null;
    EditText address = null;
    EditText zipcode = null;
    EditText utaid = null;
    EditText firstname = null;
    EditText lastname = null;
    EditText contact = null;
    Button loginButton = null;  //this should be register button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setContentView(R.layout.activity_register);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        username = findViewById(R.id.username);
        password= findViewById(R.id.password);
        address = findViewById(R.id.address);
        zipcode= findViewById(R.id.zipcode);
        utaid = findViewById(R.id.UTA_ID);
        firstname= findViewById(R.id.first_name);
        lastname= findViewById(R.id.last_name);
        contact= findViewById(R.id.contact_no);
        email = findViewById(R.id.email);
        role = findViewById(R.id.role);

        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }



}
