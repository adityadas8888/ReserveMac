package com.seproject.reservemac.facility_manager;

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

import org.json.JSONException;
import org.json.JSONObject;

public class ViewFacilityFM extends AppCompatActivity implements GetRequests.AsyncResponse {

    Button BtnMakeUnavailable;
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
        setContentView(R.layout.activity_view_facility_fm);

        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");

        BtnMakeUnavailable = findViewById(R.id.BtnMakeUnavailable);

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


        BtnMakeUnavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "MakeUnavailable";
                StringBuilder stringBuilder = new StringBuilder();
                UserCreds userCreds = UserCreds.getInstance();

                //http://mohammedmurtuzabhaiji.uta.cloud/se1project/fm/facility_unavailable.php?
                // facilitycode=BMC5&avail=0
                stringBuilder.append("fm/facility_unavailable.php?facilitycode=")
                        .append(facilityModel.getFacilitycode());
                stringBuilder.append("&avail=").append("0");
//
                String url = stringBuilder.toString();
                new GetRequests(ViewFacilityFM.this, url, ViewFacilityFM.this, "MakeUnavailable").execute("");

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
                    Toast.makeText(this, "Reservation made Unavailable.!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewFacilityFM.this, SearchFacilityActivity.class);
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
