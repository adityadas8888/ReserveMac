package com.seproject.reservemac.mainscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seproject.reservemac.R;

public class Admin_screen extends AppCompatActivity {


    EditText username = null;

    Button signout = null;
    Button BtnChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
        BtnChangePassword = findViewById(R.id.BtnChangePassword);

        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_screen.this, ChangePassword.class);
                intent.putExtra("username",username.getText().toString());
                startActivity(intent);
            }
        });

    }
}
