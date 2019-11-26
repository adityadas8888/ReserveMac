package com.seproject.reservemac.facility_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.model.FacilityModel;
import com.seproject.reservemac.model.UserCreds;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.user.ModifyReservation;
import com.seproject.reservemac.user.ReserveFacility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewUser_fm extends AppCompatActivity implements GetRequests.AsyncResponse{
    EditText username;
    EditText password;
    EditText firstname;
    EditText lastname;
    EditText utaid;
    EditText phone;
    EditText email;
    EditText address;
    EditText zipcode;
    EditText role;
    EditText NoVio;
    EditText Noshow;
    Button ViolationDetails;
    Button Updateuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_details_fm);
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
        ViolationDetails = findViewById(R.id.BtnViodetails);
        Updateuser = findViewById(R.id.BtnDetails);
        String usr = getIntent().getStringExtra("username");/// fix this

        String type = "ViewUser";
        StringBuilder stringBuilder = new StringBuilder();
        UserCreds userCreds =   UserCreds.getInstance();

        stringBuilder.append("user/view_profile.php?username=").append(usr);
        String url = stringBuilder.toString();
        new GetRequests(ViewUser_fm.this, url, ViewUser_fm.this, "ViewUser").execute("");

        ViolationDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            String result = "";
            try {
                if (Identity.equalsIgnoreCase("ViewUser")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    if (jsonArray.length() != 0) {
                        JSONObject json = jsonArray.getJSONObject(0);
                        username.setText(json.getString("username"));
                        password.setText(json.getString("password"));
                        firstname.setText(json.getString("firstname"));
                        lastname.setText(json.getString("lastname"));
                        utaid.setText(json.getString("utaid"));
                        role.setText(json.getString("role"));
                        phone.setText(json.getString("contactno"));
                        address.setText(json.getString("streetaddress"));
                        zipcode.setText(json.getString("zipcode"));
                        Noshow.setText(json.getString("noshow"));
                        NoVio.setText(json.getString("revoked"));
                        email.setText(json.getString("email"));
                    } else if (Identity.equalsIgnoreCase("ViewUser")) {
                        Toast.makeText(this, "Nothing Available at this specific time.!!", Toast.LENGTH_SHORT).show();

                    }

                } else {

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
