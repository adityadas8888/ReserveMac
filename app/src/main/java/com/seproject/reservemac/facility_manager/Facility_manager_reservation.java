package com.seproject.reservemac.facility_manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.ui.login.LoginActivity;
import com.seproject.reservemac.user.view_profile_user;

public class Facility_manager_reservation extends AppCompatActivity {

    Button DeleteReservation = null;
    Button ReportViolation = null;
    TextView ReservationId;
    TextView username;
    TextView FacilityCode;
    TextView Bookingtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_details);

        DeleteReservation = findViewById(R.id.BtnDltReservation);
        ReportViolation = findViewById(R.id.BtnReportVio);
        ReservationId = findViewById(R.id.reservationid);
        username = findViewById(R.id.username);
        FacilityCode = findViewById(R.id.FacilityCode);
        Bookingtime = findViewById(R.id.reservationdates);



    }
}
