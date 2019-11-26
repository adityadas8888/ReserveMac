package com.seproject.reservemac.administrator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.facility_manager.SearchUserActivity;
import com.seproject.reservemac.mainscreens.ChangePassword;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.login.LoginActivity;
import com.seproject.reservemac.user.User_screen;
import com.seproject.reservemac.user.view_profile_user;

public class Admin_screen extends AppCompatActivity {


    TextView username = null;

    Button signout = null;
    Button search_user = null;
    Button view_profile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);
        final UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");
        username = findViewById(R.id.Txtusername);
        signout = findViewById(R.id.signout);
        search_user = findViewById(R.id.search_user);
        view_profile = findViewById(R.id.BtnViewProfile);
        Toast.makeText(getApplicationContext(), "Logged in successfully " , Toast.LENGTH_SHORT).show();
        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), view_profile_user_admin.class);
                intent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(intent);
            }
        });


        username.setText(usermodel.getUsername());
        search_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SearchUserActivity_admin.class);
                intent.putExtra("role",usermodel.getRole());              // to determine what activity to move to from the search user activity
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
