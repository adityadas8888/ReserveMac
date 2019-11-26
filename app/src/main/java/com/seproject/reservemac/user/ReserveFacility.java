package com.seproject.reservemac.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.model.FacilityModel;
import com.seproject.reservemac.model.UserCreds;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.ui.common.ViewReservation;

import org.json.JSONException;
import org.json.JSONObject;

public class ReserveFacility extends AppCompatActivity implements GetRequests.AsyncResponse {

    Button BtnMakeReservation;
    TextView FacilityName;
    TextView FacilityCode;
    TextView FacilityDescription;
    TextView Date;
    TextView StartTime, EndTime;
    TextView Deposit;
    UserCreds userCreds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_facility);

        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");

        BtnMakeReservation = findViewById(R.id.BtnMakeReservation);

        FacilityName = findViewById(R.id.TxtFName);
        FacilityName.setText(facilityModel.getFacilityname());

        FacilityCode = findViewById(R.id.TxtFCode);
        FacilityCode.setText(facilityModel.getFacilitycode());

        Date = findViewById(R.id.TxtDate);
        Date.setText(getIntent().getStringExtra("date"));/// fix this

        StartTime = findViewById(R.id.TxtStartTime);
        StartTime.setText(facilityModel.getStartTime());/// fix this

        EndTime = findViewById(R.id.TxtEndTime);
        EndTime.setText(facilityModel.getEndTime());/// fix this

        Deposit = findViewById(R.id.TxtDeposit);
        Deposit.setText("$" + String.valueOf(facilityModel.getDeposit()));/// fix this


        BtnMakeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "MakeReservation";
                StringBuilder stringBuilder = new StringBuilder();
                UserCreds userCreds = UserCreds.getInstance();

//                http://mohammedmurtuzabhaiji.uta.cloud/se1project/user/reserve_facility.php?facilitycode=OVBC2&date=2019-12-29&start=2:00&end=3:00&user=aditdas&facilitytype=Outdoor

                stringBuilder.append("user/reserve_facility.php?facilitycode=").append(facilityModel.getFacilitycode());
                stringBuilder.append("&date=").append(Date.getText().toString());
                stringBuilder.append("&start=").append(facilityModel.getStartTime());
                stringBuilder.append("&end=").append(facilityModel.getEndTime());
                stringBuilder.append("&user=").append(userCreds.getUsername());
                stringBuilder.append("&facilitytype=").append(facilityModel.getFacilitytype());
                String url = stringBuilder.toString();
                new GetRequests(ReserveFacility.this, url, ReserveFacility.this, "MakeReservation").execute("");

            }
        });

    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            String result = "";
            try {
                String resp = String.valueOf(jsonObject.getString("response_desc"));
                if (resp.equalsIgnoreCase("OK")) {
                    Toast.makeText(this, "Reservation done successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReserveFacility.this, ViewReservation.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Reservation unsuccessful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReserveFacility.this, SearchFacilityActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                    startActivity(intent);
                    finish();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
