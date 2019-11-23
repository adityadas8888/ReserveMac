package com.seproject.reservemac.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;

public class view_profile_user extends AppCompatActivity {
    TextView extUsername;
    TextView password;
    EditText firstName;
    EditText lastName;
    TextView utaid;
    EditText phone;
    EditText email;
    EditText address;
    EditText zipcode;
    TextView role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_user);
        final UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");
        extUsername= findViewById(R.id.EtxUsername);
        extUsername.setText(usermodel.getUsername());

        password= findViewById(R.id.EtxPassword);
        password.setText(usermodel.getPassword());


        firstName= findViewById(R.id.EtxFirstName);
        firstName.setText(usermodel.getFirstname());

        lastName= findViewById(R.id.EtxLastName);
        lastName.setText(usermodel.getLastname());

        utaid= findViewById(R.id.EtxUtaID);
        utaid.setText(usermodel.getUtaid());

        phone= findViewById(R.id.EtxPhone);
        phone.setText(usermodel.getContactno());

        email= findViewById(R.id.EtxEmail);
        email.setText(usermodel.getEmail());

        address= findViewById(R.id.EtxStreetAddress);
        address.setText(usermodel.getStreetaddress());

        zipcode= findViewById(R.id.EtxZipcode);
        zipcode.setText(usermodel.getZipcode());

        role= findViewById(R.id.EtxRole);
        role.setText(usermodel.getRole());
     }
}
