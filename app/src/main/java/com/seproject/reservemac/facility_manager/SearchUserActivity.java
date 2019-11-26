package com.seproject.reservemac.facility_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;

public class SearchUserActivity extends AppCompatActivity {
    EditText username;
    Button Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        username = findViewById(R.id.EtxUsername);
        Search = findViewById(R.id.BtnSearchUser);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = username.getText().toString();
                Intent intent = new Intent(getApplicationContext(),ViewUser_fm.class);
                intent.putExtra("username",usr);
                startActivity(intent);
            }
        });
    }
}
