package com.seproject.reservemac.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;

public class view_profile_user extends AppCompatActivity {
    TextView ExtUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_user);
        final UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");
        ExtUsername= findViewById(R.id.EtxUsername);
        ExtUsername.setText(usermodel.getUsername());

    }
}
