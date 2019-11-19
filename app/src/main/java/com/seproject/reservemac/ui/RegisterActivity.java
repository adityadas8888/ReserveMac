package com.seproject.reservemac.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.ui.login.Backgroundworker;

public class RegisterActivity extends AppCompatActivity {

    private boolean connection = false;
    EditText usernameEditText = null;
    EditText passwordEditText = null;
    EditText emailEditText = null;
    EditText roleEditText = null;
    EditText addressEditText = null;
    EditText zipcodeEditText = null;
    EditText utaidEditText = null;
    EditText firstnameEditText = null;
    EditText lastnameEditText = null;
    EditText contactEditText = null;
    Button loginButton = null;  //this should be register button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setContentView(R.layout.activity_register);

        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        usernameEditText = findViewById(R.id.username);
        passwordEditText= findViewById(R.id.password);
        addressEditText = findViewById(R.id.address);
        zipcodeEditText = findViewById(R.id.zipcode);
        utaidEditText = findViewById(R.id.UTA_ID);
        firstnameEditText = findViewById(R.id.first_name);
        lastnameEditText = findViewById(R.id.last_name);
        contactEditText = findViewById(R.id.contact_no);
        emailEditText = findViewById(R.id.email);
        roleEditText = findViewById(R.id.role);


        assert connectivityManager != null;
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connection) {
                    createuser();
                } else {
                    Toast.makeText(getApplicationContext(), "Switch on the Internet", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void createuser() {

        String username = usernameEditText.getText().toString().trim();
        if (usernameEditText.getText().toString().trim().equals("")) {

            usernameEditText.setError("The username is required");
            usernameEditText.setHint("Please enter your username");
        }
        else if (passwordEditText.getText().toString().trim().equals("")) {
            passwordEditText.setError("Password is required!");

            passwordEditText.setHint("Please enter your Password");
        }
        else if (addressEditText.getText().toString().trim().equals("")) {
            addressEditText.setError("Address is required!");

            addressEditText.setHint("Please enter your Address");
        }
        else if (zipcodeEditText.getText().toString().trim().equals("")) {
            zipcodeEditText.setError("Zipcode is required!");

            zipcodeEditText.setHint("Please enter your Zipcode");
        }
        else if (utaidEditText.getText().toString().trim().equals("")) {
            utaidEditText.setError("UTA id is required!");

            utaidEditText.setHint("Please enter your UTA id");
        }
        else if (firstnameEditText.getText().toString().trim().equals("")) {
            firstnameEditText.setError("First name is required!");

            firstnameEditText.setHint("Please enter your First name");
        }
        else if (lastnameEditText.getText().toString().trim().equals("")) {
            lastnameEditText.setError("Last name is required!");

            lastnameEditText.setHint("Please enter your last name");
        }
        else if (contactEditText.getText().toString().trim().equals("")) {
            contactEditText.setError("Contatct number is required!");

            contactEditText.setHint("Please enter your Password");
        }
        else if (emailEditText.getText().toString().trim().equals("")) {
            emailEditText.setError("Email address is required!");

            emailEditText.setHint("Please enter your Email address");
        }
        else if (roleEditText.getText().toString().trim().equals("")) {
            roleEditText.setError("User role is required!");

            roleEditText.setHint("Please select your Role");
        }



        else{
            String type = "login";
//            Backgroundworker backgroundworker = new Backgroundworker(this);
//            backgroundworker.execute();
        }
    }

}
