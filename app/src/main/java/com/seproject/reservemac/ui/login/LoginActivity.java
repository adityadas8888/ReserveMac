package com.seproject.reservemac.ui.login;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.data.model.DatabaseHelpers;
import com.seproject.reservemac.mainscreens.Admin_screen;
import com.seproject.reservemac.mainscreens.Facility_manager_screen;
import com.seproject.reservemac.mainscreens.User_screen;
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
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
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
//                readFromDB(usernameEditText.getText().toString(), passwordEditText.getText().toString());

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




    private void readFromDB(String user, String password) {
        String username="";
        String role="";
        String pass="";

        SQLiteDatabase database = new DatabaseHelpers(this).getReadableDatabase();

        String[] projection = {
                DatabaseHelpers.ROLE,
                DatabaseHelpers.USERNAME,
           DatabaseHelpers.PASSWORD

        };

        String selection =
                DatabaseHelpers.USERNAME + " = '"+user+"' and " +
                        DatabaseHelpers.PASSWORD + " = '"+password+"'";

//        String[] selectionArgs = {"%" + Task_name + "%", Task_Category, "%" + Task_Description + "%" + Task_Date + "%" + Task_Time + "%" + Snooze_Time + "%"};


        try {
            Cursor cursor = database.query(
                    DatabaseHelpers.USER_TABLE_NAME,   // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    null,                            // The   for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                      // don't sort
            );
            if (cursor.moveToFirst()) {
                do {
                     role = ((cursor.getString(0)));   // ID //
                     username = (cursor.getString(1));
                     pass = (cursor.getString(2));


                } while (cursor.moveToNext());
            }

            if (username.isEmpty()){
                Toast.makeText(this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "user:" + username + " role: " + role, Toast.LENGTH_SHORT).show();
            }
            switch (role){
                case "user":
                    Intent myintent = new Intent(getBaseContext(), User_screen.class);
                    myintent.putExtra("username",username);
                    startActivity(myintent);
                    break;

                case "admin":
                    Intent myintent1 = new Intent(getBaseContext(), Admin_screen.class);
                    myintent1.putExtra("username",username);
                    startActivity(myintent1);
                    break;

                case "facility manager":
                    Intent myintent2 = new Intent(getBaseContext(), Facility_manager_screen.class);
                    myintent2.putExtra("username",username);
                    startActivity(myintent2);
                    break;

//                default:
//                    Intent myintent3 = new Intent(getBaseContext(), Facility_manager_screen.class);
//                    myintent3.putExtra("username",username);
//                    startActivity(myintent3);
//                    break;
            }


            Log.d("TAG", "The total cursor count is " + cursor.getCount());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}