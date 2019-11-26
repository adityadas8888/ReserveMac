package com.seproject.reservemac.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.model.ReservationModel;
import com.seproject.reservemac.model.UserCreds;
import com.seproject.reservemac.user.ModifyReservation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewReservation extends AppCompatActivity implements GetRequests.AsyncResponse {


    RecyclerView RecycleViewReservation;
    ViewReservationsAdapter viewReservationsAdapter;
    //    ArrayList<ReservationModel> listofreservations;
    ArrayList<ReservationModel> reservationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);
        RecycleViewReservation = findViewById(R.id.RecycleViewReservation);
        RecycleViewReservation.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RecycleViewReservation.setLayoutManager(llm);
        loadReservationsArray();
    }

    private void loadReservationsArray() {
        String type = "ViewReservation";
        StringBuilder stringBuilder = new StringBuilder();
//        final UserModel userModel = getIntent().getParcelableExtra("usermodel");
//        http://mohammedmurtuzabhaiji.uta.cloud/se1project/user/my_reservations.php?username=mohammed
        UserCreds userCreds = UserCreds.getInstance();
        stringBuilder.append("user/my_reservations.php?username=").append(userCreds.getUsername());
        String url = stringBuilder.toString();
        new GetRequests(ViewReservation.this, url, ViewReservation.this, "ViewReservation").execute("");

    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            reservationModelArrayList = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("content");
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ReservationModel reservationModel = new ReservationModel();
                        JSONObject json = jsonArray.getJSONObject(i);
                        reservationModel.setReservationid(Integer.parseInt((json.getString("reservationid"))));
                        reservationModel.setstarttime((json.getString("start")));
                        reservationModel.setEndTime((json.getString("end")));
                        reservationModel.setViolation(Integer.parseInt((json.getString("violation"))));
                        reservationModel.setViodetails((json.getString("viodetails")));
                        reservationModel.setResstatus(Integer.parseInt((json.getString("resstatus"))));
                        reservationModel.setUsername((json.getString("username")));
                        reservationModel.setFacilitycode((json.getString("facilitycode")));
                        reservationModel.setFacilityName((json.getString("name")));
                        reservationModel.setFacilitydescription((json.getString("description")));
                        reservationModel.setDeposit(Integer.parseInt((json.getString("deposit"))));
                        reservationModelArrayList.add(reservationModel);
                    }

                    viewReservationsAdapter = new ViewReservationsAdapter(reservationModelArrayList);
                    RecycleViewReservation.setAdapter(viewReservationsAdapter);
                } else {
                    Toast.makeText(this, "Nothing Reserved.!!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    public class ViewReservationsAdapter extends RecyclerView.Adapter<ViewReservationsAdapter.MyViewHolder> {
        ArrayList<ReservationModel> listofreservation;

        public ViewReservationsAdapter(ArrayList<ReservationModel> listofreservation) {
            this.listofreservation = listofreservation;
            //  loadDailyTaskArray();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView TxtFacilityName, TxtFacilityCode, TxtReservationID, TxtDate, TxtStartTime, TxtEndTime;
            public LinearLayout LnrReservation;

            public MyViewHolder(View v) {
                super(v);


                LnrReservation = (LinearLayout) v.findViewById(R.id.LnrReservation);
                TxtFacilityName = (TextView) v.findViewById(R.id.TxtFacilityName);
                TxtFacilityCode = (TextView) v.findViewById(R.id.TxtFacilityCode);
                TxtReservationID = (TextView) v.findViewById(R.id.TxtReservationID);
                TxtDate = (TextView) v.findViewById(R.id.TxtDate);
                TxtStartTime = (TextView) v.findViewById(R.id.TxtStartTime);
                TxtEndTime = (TextView) v.findViewById(R.id.TxtEndTime);

            }


        }


        @Override
        public ViewReservationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_view_reservations, parent, false);
            ViewReservationsAdapter.MyViewHolder vh = new MyViewHolder(v);
            return vh;

        }


        @Override
        public void onBindViewHolder(ViewReservationsAdapter.MyViewHolder holder, final int position) {
            final ReservationModel reservationModel = listofreservation.get(position);

            String startTime = reservationModel.getstarttime();
            String endTime = reservationModel.getEndTime();

            String[] parts = startTime.split(" ");
            String date = parts[0];
            String start = parts[1].substring(0, 5);

            String[] parts2 = endTime.split(" ");
            String end = parts2[1].substring(0, 5);


            reservationModel.setDate(date);
            reservationModel.setstarttime(start);
            reservationModel.setEndTime(end);

            holder.TxtFacilityName.setText(reservationModel.getFacilityName());
            holder.TxtFacilityCode.setText(reservationModel.getFacilitycode());
            holder.TxtReservationID.setText("Res ID: " + reservationModel.getReservationid());
            holder.TxtDate.setText("Date: " + reservationModel.getDate());
            holder.TxtStartTime.setText("Time: " + reservationModel.getstarttime());
            holder.TxtEndTime.setText(reservationModel.getEndTime());

            holder.LnrReservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ModifyReservation.class);
                    i.putExtra("reservationModel", (Parcelable) reservationModel);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listofreservation.size();
        }
    }


}





