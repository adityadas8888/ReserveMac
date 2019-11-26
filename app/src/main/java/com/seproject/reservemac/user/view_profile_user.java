//package com.seproject.reservemac.user;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.seproject.reservemac.R;
//import com.seproject.reservemac.background.PostRequests;
//import com.seproject.reservemac.model.UserModel;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cz.msebera.android.httpclient.NameValuePair;
//import cz.msebera.android.httpclient.message.BasicNameValuePair;
//
//public class view_profile_user extends AppCompatActivity implements PostRequests.PostAsyncResponse {
//    private boolean connection = false;
//    TextView extUsername;
//    EditText password;
//    EditText firstName;
//    EditText lastName;
//    TextView utaid;
//    EditText phone;
//    EditText email;
//    EditText address;
//    EditText zipcode;
//    TextView role;
//    Button Update;
//    UserModel userModel;
//    String usr ="";
//    String pass ="";
//    String fname ="";
//    String lname ="";
//    String uta ="";
//    String phn ="";
//    String emal ="";
//    String addr ="";
//    String zpd ="";
//    String rol ="";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_profile_user);
//        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
//        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");
//        extUsername = findViewById(R.id.EtxUsername);
//        extUsername.setText(usermodel.getUsername());
//        usr = extUsername.getText().toString();
//        password = findViewById(R.id.EtxPassword);
//        password.setText(usermodel.getPassword());
//        pass = password.getText().toString();
//        firstName = findViewById(R.id.EtxFirstName);
//        firstName.setText(usermodel.getFirstname());
//        fname= firstName.getText().toString();
//
//
//        lastName = findViewById(R.id.EtxLastName);
//        lastName.setText(usermodel.getLastname());
//        lname= lastName.getText().toString();
//
//        utaid = findViewById(R.id.EtxUtaID);
//        utaid.setText(usermodel.getUtaid());
//        uta= utaid.getText().toString();
//
//        phone = findViewById(R.id.EtxPhone);
//        phone.setText(usermodel.getContactno());
//        phn= phone.getText().toString();
//
//        email = findViewById(R.id.EtxEmail);
//        email.setText(usermodel.getEmail());
//        emal= email.getText().toString();
//
//        address = findViewById(R.id.EtxStreetAddress);
//        address.setText(usermodel.getStreetaddress());
//        addr= address.getText().toString();
//        zipcode = findViewById(R.id.EtxZipcode);
//        zipcode.setText(usermodel.getZipcode());
//        zpd= zipcode.getText().toString();
//
//        role = findViewById(R.id.EtxRole);
//        role.setText(usermodel.getRole());
//        rol= role.getText().toString();
//
//        Update = findViewById(R.id.Update);
//        assert connectivityManager != null;
//        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());
//        Update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (connection) {
//                    updateUser();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Switch on the Internet", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//    }
//
//    public void updateUser() {
//
//        String type = "update";
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("user_update_profile.php");
//        String url = stringBuilder.toString();
//        try {
//            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//            pairs.add(new BasicNameValuePair("username",extUsername.getText().toString()));
//            pairs.add(new BasicNameValuePair("firstname", fname));
//            pairs.add(new BasicNameValuePair("lastname", lname));
//            pairs.add(new BasicNameValuePair("password", pass));
//            pairs.add(new BasicNameValuePair("contactno", phn));
//            pairs.add(new BasicNameValuePair("streetaddress",addr));
//            pairs.add(new BasicNameValuePair("zipcode", zpd));
//            pairs.add(new BasicNameValuePair("email", email.getText().toString()));
//            new PostRequests(view_profile_user.this, url, view_profile_user.this, "Update", pairs).execute("");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
//        if (jsonObject != null) {
//
//            try {
////                String msg = String.valueOf(jsonObject.getString("content"));
////                userModel = new UserModel();
//                JSONObject jsonContent = jsonObject.getJSONObject("content");
//                userModel.setUsername((jsonContent.getString("username")));
//                extUsername.setText(userModel.getUsername());
//                userModel.setFirstname((jsonContent.getString("firstname")));
//                firstName.setText(userModel.getFirstname());
//                userModel.setPassword((jsonContent.getString("password")));
//                password.setText(userModel.getPassword());
//                userModel.setLastname((jsonContent.getString("lastname")));
//                lastName.setText(userModel.getLastname());
//                userModel.setUtaid((jsonContent.getString("utaid")));
//                utaid.setText(userModel.getUtaid());
//                userModel.setRole((jsonContent.getString("role")));
//                role.setText(userModel.getRole());
//                userModel.setContactno((jsonContent.getString("contactno")));
//                phone.setText(userModel.getContactno());
//                userModel.setStreetaddress((jsonContent.getString("streetaddress")));
//                address.setText(userModel.getStreetaddress());
//                userModel.setZipcode((jsonContent.getString("zipcode")));
//                zipcode.setText(userModel.getZipcode());
//                userModel.setNoshow((jsonContent.getInt("noshow")));
//                userModel.setRevoked((jsonContent.getInt("revoked")));
//                userModel.setEmail((jsonContent.getString("email")));
//                email.setText(userModel.getEmail());
//
//                Intent intent = new Intent(view_profile_user.this,User_screen.class);
//                startActivity(intent);
//                Toast.makeText(this, "Profile updated.!", Toast.LENGTH_SHORT).show();
//
//
//            } catch (JSONException e) {
////                e.printStackTrace();
//            }
//
//
//        }
//    }
//}

package com.seproject.reservemac.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.seproject.reservemac.R;
import com.seproject.reservemac.administrator.Admin_screen;
import com.seproject.reservemac.administrator.SearchUserActivity_admin;
import com.seproject.reservemac.administrator.ViewUser_admin;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.background.PostRequests;
import com.seproject.reservemac.model.UserCreds;
import com.seproject.reservemac.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class view_profile_user extends AppCompatActivity implements PostRequests.PostAsyncResponse , GetRequests.AsyncResponse {
    private boolean connection = false;
    TextView username;
    TextView password;
    EditText firstname;
    EditText lastname;
    TextView utaid;
    EditText phone;
    EditText email;
    EditText address;
    EditText zipcode;
    TextView role;
    Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_user);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");
        username = findViewById(R.id.EtxUsername);
//        username.setText(usermodel.getUsername());
        password = findViewById(R.id.EtxPassword);
        firstname = findViewById(R.id.EtxFirstName);
        lastname = findViewById(R.id.EtxLastName);
        utaid = findViewById(R.id.EtxUtaID);
        phone = findViewById(R.id.EtxPhone);
        email = findViewById(R.id.EtxEmail);
        address = findViewById(R.id.EtxStreetAddress);
        zipcode = findViewById(R.id.EtxZipcode);
        role = findViewById(R.id.EtxRole);
        Update = findViewById(R.id.Update);

        assert connectivityManager != null;
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());
        String type = "ViewUser";
        StringBuilder stringBuilder = new StringBuilder();
        UserCreds userCreds = UserCreds.getInstance();
        stringBuilder.append("user/view_profile.php?username=").append(usermodel.getUsername());
        String url = stringBuilder.toString();
        new GetRequests(view_profile_user.this, url, view_profile_user.this, "ViewUser").execute("");
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
        stringBuilder.append("user/update_profile.php");
        String url = stringBuilder.toString();
        try {
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("username", username.getText().toString()));
            pairs.add(new BasicNameValuePair("firstname", firstname.getText().toString()));
            pairs.add(new BasicNameValuePair("lastname", lastname.getText().toString()));
            pairs.add(new BasicNameValuePair("password", password.getText().toString()));
            pairs.add(new BasicNameValuePair("role", role.getText().toString()));
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
                        role.setText(json.getString("role"));
                        phone.setText(json.getString("contactno"));
                        address.setText(json.getString("streetaddress"));
                        zipcode.setText(json.getString("zipcode"));
                        email.setText(json.getString("email"));
                    } else if (Identity.equalsIgnoreCase("ViewUser")) {
                        Toast.makeText(this, "Nothing Available at this specific time.!!", Toast.LENGTH_SHORT).show();

                    }

                } else if (Identity.equalsIgnoreCase("Update")) {
                    Toast.makeText(this, "Profile updated.!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(view_profile_user.this, User_screen.class);
//                    startActivity(intent);
//                    view_profile_user.this.finish();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
