package com.seproject.reservemac.administrator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.mainscreens.ChangePassword;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.login.LoginActivity;
import com.seproject.reservemac.user.User_screen;

public class Admin_screen extends AppCompatActivity {


    TextView username = null;

    Button signout = null;
    Button BtnChangePassword = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
        BtnChangePassword = findViewById(R.id.BtnChangePassword);

        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");


        username.setText("Welcome "+usermodel.getUsername());


        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ChangePassword.class);
                intent.putExtra("username",username.getText().toString());
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Admin_screen.this,LoginActivity.class);
                        startActivity(intent);
                        Admin_screen.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
