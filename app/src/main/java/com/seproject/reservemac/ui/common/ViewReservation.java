package com.seproject.reservemac.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        final UserModel userModel = getIntent().getParcelableExtra("usermodel");
        stringBuilder.append("user_reservations.php?username=").append(userModel.getUsername());
        String url = stringBuilder.toString();
        new GetRequests(ViewReservation.this, url, ViewReservation.this, "ViewReservation").execute("");

    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            reservationModelArrayList = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("content");

                for (int i = 0; i < jsonArray.length(); i++) {
                    ReservationModel reservationModel = new ReservationModel();
                    JSONObject json = jsonArray.getJSONObject(i);
                    reservationModel.setReservationid(Integer.parseInt((json.getString("reservationid"))));
                    reservationModel.setDatetime((json.getString("datetime")));
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
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    public class ViewReservationsAdapter extends RecyclerView.Adapter<ViewReservationsAdapter.MyViewHolder> {
        ArrayList<ReservationModel> listofreservation;

        public ViewReservationsAdapter(ArrayList<ReservationModel> listofspecialoccasions) {
            this.listofreservation = listofspecialoccasions;
            //  loadDailyTaskArray();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView TxtFacilityName, TxtFacilityCode, TxtReservationID, TxtDate;


            public MyViewHolder(View v) {
                super(v);


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


            holder.TxtFacilityName.setText(reservationModel.getFacilityName());
            holder.TxtFacilityCode.setText(reservationModel.getFacilitycode());
            holder.TxtReservationID.setText("Res ID: " + reservationModel.getReservationid());
            holder.TxtDate.setText(reservationModel.getDatetime());

        }

        @Override
        public int getItemCount() {
            return listofreservation.size();
        }
    }


}





