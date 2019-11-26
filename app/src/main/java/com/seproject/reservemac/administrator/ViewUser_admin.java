package com.seproject.reservemac.administrator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.background.PostRequests;
import com.seproject.reservemac.model.UserCreds;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class ViewUser_admin extends AppCompatActivity implements GetRequests.AsyncResponse, PostRequests.PostAsyncResponse {
    TextView username;
    TextView password;
    EditText firstname;     //
    EditText lastname;         //
    EditText utaid;         //
    EditText phone;         //
    EditText email;         //
    EditText address;       //
    EditText zipcode;       //
    Spinner role;
    TextView NoVio;         //
    TextView Noshow;          //
    Button ViolationDetails;
    Button Updateuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_details_admin);
        username = findViewById(R.id.EtxUsername);
        password = findViewById(R.id.EtxPassword);
        firstname = findViewById(R.id.etxfirstName);
        lastname = findViewById(R.id.EtxLastName);
        utaid = findViewById(R.id.EtxUtaID);
        phone = findViewById(R.id.EtxPhone);
        email = findViewById(R.id.EtxEmail);
        address = findViewById(R.id.EtxStreetAddress);
        zipcode = findViewById(R.id.EtxZipcode);
        role = findViewById(R.id.EtxRole);
        NoVio = findViewById(R.id.NoViolatations);
        Noshow = findViewById(R.id.Nonoshows);
//        ViolationDetails = findViewById(R.id.BtnViodetails);
        Updateuser = findViewById(R.id.BtnDetails);
        final String usr = getIntent().getStringExtra("username");
        String[] items = new String[]{"user", "fm", "admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        role.setSelection(0);
        role.setAdapter(adapter);
        String type = "ViewUser";
        StringBuilder stringBuilder = new StringBuilder();
        UserCreds userCreds = UserCreds.getInstance();

        stringBuilder.append("user/view_profile.php?username=").append(usr);
        String url = stringBuilder.toString();
        new GetRequests(ViewUser_admin.this, url, ViewUser_admin.this, "ViewUser").execute("");


        Updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            String result = "";
            try {
                if (Identity.equalsIgnoreCase("ViewUser")) {
                    JSONObject json = jsonObject.getJSONObject("content");
                    if (json != null) {
                        username.setText(json.getString("username"));
                        password.setText(json.getString("password"));
                        firstname.setText(json.getString("firstname"));
                        lastname.setText(json.getString("lastname"));
                        utaid.setText(json.getString("utaid"));
//                        role.setText(json.getString("role"));
                        String[] items = new String[]{"user", "fm", "admin"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
                        role.setSelection(((ArrayAdapter<String>)role.getAdapter()).getPosition(json.getString("role")));
                        phone.setText(json.getString("contactno"));
                        address.setText(json.getString("streetaddress"));
                        zipcode.setText(json.getString("zipcode"));
                        Noshow.setText(json.getString("noshow"));
                        NoVio.setText(json.getString("revoked"));
                        email.setText(json.getString("email"));
                    } else if (Identity.equalsIgnoreCase("ViewUser")) {
                        Toast.makeText(this, "Nothing Available at this specific time.!!", Toast.LENGTH_SHORT).show();

                    }

                } else if (Identity.equalsIgnoreCase("Update")) {
                    Toast.makeText(this, "Profile updated.!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewUser_admin.this,SearchUserActivity_admin.class);
                    startActivity(intent);
                    ViewUser_admin.this.finish();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser() {

        String type = "update";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("admin/update_profile_admin.php");
        String url = stringBuilder.toString();
        try {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("username", username.getText().toString()));
            pairs.add(new BasicNameValuePair("firstname", firstname.getText().toString()));
            pairs.add(new BasicNameValuePair("lastname", lastname.getText().toString()));
            pairs.add(new BasicNameValuePair("password", password.getText().toString()));
            pairs.add(new BasicNameValuePair("role", role.getSelectedItem().toString()));
            pairs.add(new BasicNameValuePair("contactno", phone.getText().toString()));
            pairs.add(new BasicNameValuePair("streetaddress", address.getText().toString()));
            pairs.add(new BasicNameValuePair("zipcode", zipcode.getText().toString()));
            pairs.add(new BasicNameValuePair("email", email.getText().toString()));
            new PostRequests(ViewUser_admin.this, url, ViewUser_admin.this, "Update", pairs).execute("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
