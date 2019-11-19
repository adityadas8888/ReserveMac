package com.seproject.reservemac.mainscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.ui.login.LoginActivity;

public class ChangePassword extends AppCompatActivity {


    EditText EtxChangePassword;
    TextView TxtUserName;
    String ChangePassword;
    Button BtnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        EtxChangePassword = findViewById(R.id.EtxChangePassword);
        TxtUserName = findViewById(R.id.TxtUserName);
        BtnChangePassword = findViewById(R.id.BtnChangePassword);
        TxtUserName.setText(getIntent().getStringExtra("username"));

        ChangePassword = EtxChangePassword.getText().toString();

        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword(ChangePassword);
            }
        });


    }


    public  void changePassword(String ChangePassword){
//        SQLiteDatabase database = new DatabaseHelpers(this).getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelpers.PASSWORD, EtxChangePassword.getText().toString());
//
////        long newRowId = database.insert(  .TASK_TABLE_NAME, null, values);
//
//        long newRowId = database.update(DatabaseHelpers.USER_TABLE_NAME, values,
//                "USERNAME='"+TxtUserName.getText().toString()+"' ",
//                null);

        Toast.makeText(this, "Password Updated !!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(i);

    }
}
