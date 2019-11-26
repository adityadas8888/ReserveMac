package com.seproject.reservemac.administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.facility_manager.SearchUserActivity;
import com.seproject.reservemac.facility_manager.ViewUser_fm;

public class SearchUserActivity_admin extends AppCompatActivity {
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
                Intent intent = new Intent(getApplicationContext(),ViewUser_admin.class);
                intent.putExtra("username",usr);
                startActivity(intent);
                SearchUserActivity_admin.this.finish();
            }
        });
    }
}
