package com.seproject.reservemac.ui.common;

import android.os.Bundle;
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
import com.seproject.reservemac.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        final UserModel userModel = getIntent().getParcelableExtra("usermodel");
//        http://mohammedmurtuzabhaiji.uta.cloud/se1project/user/my_reservations.php?username=mohammed

        stringBuilder.append("user/my_reservations.php?username=").append(userModel.getUsername());
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

            public TextView TxtFacilityName, TxtFacilityCode, TxtReservationID, TxtDate;
            public LinearLayout LnrReservation;

            public MyViewHolder(View v) {
                super(v);


                LnrReservation = (LinearLayout) v.findViewById(R.id.LnrReservation);
                TxtFacilityName = (TextView) v.findViewById(R.id.TxtFacilityName);
                TxtFacilityCode = (TextView) v.findViewById(R.id.TxtFacilityCode);
                TxtReservationID = (TextView) v.findViewById(R.id.TxtReservationID);
                TxtDate = (TextView) v.findViewById(R.id.TxtDate);

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

            String date = reservationModel.getstarttime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {

                Date date1 = (Date) sdf.parse(date);
                String newDate = sdf.format(date1);


                holder.TxtFacilityName.setText(reservationModel.getFacilityName());
                holder.TxtFacilityCode.setText(reservationModel.getFacilitycode());
                holder.TxtReservationID.setText("Res ID: " + reservationModel.getReservationid());
                holder.TxtDate.setText(newDate);

                holder.LnrReservation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent i = new Intent(view.getContext(),  );
                    }
                });

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return listofreservation.size();
        }
    }


}





