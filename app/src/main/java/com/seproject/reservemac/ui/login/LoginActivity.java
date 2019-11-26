package com.seproject.reservemac.ui.login;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.administrator.Admin_screen;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.facility_manager.Facility_manager_screen;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.model.UserCreds;
import com.seproject.reservemac.ui.RegisterActivity;
import com.seproject.reservemac.user.User_screen;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity implements GetRequests.AsyncResponse {

    private boolean connection = false;

    EditText usernameEditText = null;
    EditText passwordEditText = null;
    Button loginButton = null;
    Button registerButton = null;
    UserModel userModel ;
    String password, username;
    UserCreds userCreds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ConnectivityManager connectivityManager = ((ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE));
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);

        assert connectivityManager != null;
        connection = (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connection) {
                    loginUser();
                } else {
                    Toast.makeText(getApplicationContext(), "Switch on the Internet", Toast.LENGTH_LONG).show();
                }


            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(myintent);
            }
        });

    }
    
    public void loginUser() {

        password = passwordEditText.getText().toString().trim();
        username = usernameEditText.getText().toString().trim();
        if (usernameEditText.getText().toString().trim().equals("")) {
            usernameEditText.setError("The username is required");

            usernameEditText.setHint("Please enter your username");
        } else if (passwordEditText.getText().toString().trim().equals("")) {
            passwordEditText.setError("Password is required!");

            passwordEditText.setHint("Please enter your Password");
        } else {
            String type = "login";
            //            "login.php?username"+"="+username+"&"+"password"+"="+password;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("login.php?username=").append(username);
            stringBuilder.append("&password=").append(password);
            String url = stringBuilder.toString();

            new GetRequests(LoginActivity.this, url, LoginActivity.this, "Login").execute("");

        }


    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        AfterReceived(jsonObject, output);
    }

    private void AfterReceived(JSONObject jsonObject, String output) {

        if (jsonObject != null) {
            try {
                userModel = new UserModel();
                UserCreds userCreds = UserCreds.getInstance();
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
                userCreds.setUsername(jsonContent.getString("username"));
                userCreds.setRole(jsonContent.getString("role"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (userModel.getUsername().equals(username)) {
                Intent intent;
                if(userModel.getRole().equals("user")) {
                    intent = new Intent(LoginActivity.this, User_screen.class);
                    intent.putExtra("usermodel", (Parcelable) userModel);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                if(userModel.getRole().equals("fm")) {
                    intent = new Intent(LoginActivity.this, Facility_manager_screen.class);
                    intent.putExtra("usermodel", (Parcelable) userModel);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }

                if(userModel.getRole().equals("admin")) {
                    intent = new Intent(LoginActivity.this, Admin_screen.class);
                    intent.putExtra("usermodel", (Parcelable) userModel);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }

            }

        }
        else
            Toast.makeText(getApplicationContext(), "Username or Password is Invalid" , Toast.LENGTH_SHORT).show();


    }

}