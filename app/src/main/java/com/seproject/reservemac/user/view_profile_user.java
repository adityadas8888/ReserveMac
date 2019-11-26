package com.seproject.reservemac.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.PostRequests;
import com.seproject.reservemac.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class view_profile_user extends AppCompatActivity implements PostRequests.PostAsyncResponse {
    private boolean connection = false;
    TextView extUsername;
    EditText password;
    EditText firstName;
    EditText lastName;
    TextView utaid;
    EditText phone;
    EditText email;
    EditText address;
    EditText zipcode;
    TextView role;
    Button Update;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_user);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");
        extUsername = findViewById(R.id.EtxUsername);
        extUsername.setText(usermodel.getUsername());

        password = findViewById(R.id.EtxPassword);
        password.setText(usermodel.getPassword());

        firstName = findViewById(R.id.EtxFirstName);
        firstName.setText(usermodel.getFirstname());

        lastName = findViewById(R.id.EtxLastName);
        lastName.setText(usermodel.getLastname());

        utaid = findViewById(R.id.EtxUtaID);
        utaid.setText(usermodel.getUtaid());

        phone = findViewById(R.id.EtxPhone);
        phone.setText(usermodel.getContactno());

        email = findViewById(R.id.EtxEmail);
        email.setText(usermodel.getEmail());

        address = findViewById(R.id.EtxStreetAddress);
        address.setText(usermodel.getStreetaddress());

        zipcode = findViewById(R.id.EtxZipcode);
        zipcode.setText(usermodel.getZipcode());

        role = findViewById(R.id.EtxRole);
        role.setText(usermodel.getRole());

        Update = findViewById(R.id.Update);
        assert connectivityManager != null;
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connection) {
                    updateUser();

                } else {
                    Toast.makeText(getApplicationContext(), "Switch on the Internet", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void updateUser() {

        String type = "update";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_update_profile.php");
        String url = stringBuilder.toString();
        try {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("username", extUsername.getText().toString()));
            pairs.add(new BasicNameValuePair("firstname", firstName.getText().toString()));
            pairs.add(new BasicNameValuePair("lastname", lastName.getText().toString()));
            pairs.add(new BasicNameValuePair("password", password.getText().toString()));
            pairs.add(new BasicNameValuePair("contactno", phone.getText().toString()));
            pairs.add(new BasicNameValuePair("streetaddress", address.getText().toString()));
            pairs.add(new BasicNameValuePair("zipcode", zipcode.getText().toString()));
            pairs.add(new BasicNameValuePair("email", email.getText().toString()));
            new PostRequests(view_profile_user.this, url, view_profile_user.this, "Update", pairs).execute("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {

            try {
//                String msg = String.valueOf(jsonObject.getString("content"));
                userModel = new UserModel();
                JSONObject jsonContent = jsonObject.getJSONObject("content");
                userModel.setUsername((jsonContent.getString("username")));
                userModel.setFirstname((jsonContent.getString("firstname")));
                userModel.setPassword((jsonContent.getString("password")));
                userModel.setLastname((jsonContent.getString("lastname")));
                userModel.setUtaid((jsonContent.getString("utaid")));
                userModel.setRole((jsonContent.getString("role")));
                userModel.setContactno((jsonContent.getString("contactno")));
                userModel.setStreetaddress((jsonContent.getString("streetaddress")));
                userModel.setZipcode((jsonContent.getString("zipcode")));
                userModel.setNoshow((jsonContent.getInt("noshow")));
                userModel.setRevoked((jsonContent.getInt("revoked")));
                userModel.setEmail((jsonContent.getString("email")));


                Toast.makeText(this, "Profile updated.!", Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
