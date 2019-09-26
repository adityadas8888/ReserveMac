package com.seproject.reservemac.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.data.model.DatabaseHelpers;
import com.seproject.reservemac.ui.login.LoginActivity;

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
                saveToDB();
            }
        });


    }

    private void saveToDB() {

        SQLiteDatabase database = new DatabaseHelpers(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelpers.USERNAME, username.getText().toString());
        values.put(DatabaseHelpers.FIRST_NAME, firstname.getText().toString());
        values.put(DatabaseHelpers.LAST_NAME, lastname.getText().toString());
        values.put(DatabaseHelpers.ROLE,role.getText().toString());
        values.put(DatabaseHelpers.PASSWORD,password.getText().toString());
        values.put(DatabaseHelpers.UTA_ID,utaid.getText().toString());
        values.put(DatabaseHelpers.STREETADDRESS, address.getText().toString());
        values.put(DatabaseHelpers.CONTACTNO, contact.getText().toString());
        values.put(DatabaseHelpers.ZIPCODE, zipcode.getText().toString());
        values.put(DatabaseHelpers.REVOKED, 0);
        values.put(DatabaseHelpers.NOSHOWS,0);
        values.put(DatabaseHelpers.EMAIL, email.getText().toString());
        long newRowId = database.insert(DatabaseHelpers.USER_TABLE_NAME, null, values);
        database.close();
        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();

        Intent myintent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(myintent);
    }


}
