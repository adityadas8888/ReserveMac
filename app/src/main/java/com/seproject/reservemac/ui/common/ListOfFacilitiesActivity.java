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
import com.seproject.reservemac.model.FacilityModel;
import com.seproject.reservemac.user.ReserveFacility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListOfFacilitiesActivity extends AppCompatActivity implements GetRequests.AsyncResponse {

    RecyclerView RecycleAvilableFacilites;
    AvailableFacilitiesAdapter availableFacilitiesAdapter;
    ArrayList<FacilityModel> facilityModelArrayList;
    String facilityCode = "MR", date = "", time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_facilities);

        RecycleAvilableFacilites = (RecyclerView) findViewById(R.id.RecycleAvilableFacilites);

        facilityCode = getIntent().getStringExtra("facilitycode");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");

        RecycleAvilableFacilites.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RecycleAvilableFacilites.setLayoutManager(llm);
        loadAvailableFacilitiesArray();


    }

    private void loadAvailableFacilitiesArray() {

        String type = "ViewFaclities";
        StringBuilder stringBuilder = new StringBuilder();
        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");
        stringBuilder.append("user/facility_search.php?facilitycode=").append(facilityCode);
        stringBuilder.append("&date=").append(date);
        stringBuilder.append("&time=").append(time);

        String url = stringBuilder.toString();
        new GetRequests(ListOfFacilitiesActivity.this, url, ListOfFacilitiesActivity.this, "ListOfFacilitiesActivity").execute("");

    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            facilityModelArrayList = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("content");
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        FacilityModel facilityModel = new FacilityModel();
                        JSONObject json = jsonArray.getJSONObject(i);
                        facilityModel.setDeposit(Integer.parseInt((json.getString("deposit"))));
                        facilityModel.setFacilitycode((json.getString("facilitycode")));
                        facilityModel.setFacilitytype((json.getString("type")));
                        facilityModel.setFacilityname((json.getString("name")));
                        facilityModel.setFacilitydescription((json.getString("description")));
                        facilityModel.setStartTime(json.getString("start"));
                        facilityModel.setEndTime(json.getString("end"));
                        facilityModelArrayList.add(facilityModel);

                    }
                } else {
                    Toast.makeText(this, "Nothing Available.!!", Toast.LENGTH_SHORT).show();
                }

                availableFacilitiesAdapter = new AvailableFacilitiesAdapter(facilityModelArrayList);
                RecycleAvilableFacilites.setAdapter(availableFacilitiesAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public class AvailableFacilitiesAdapter extends RecyclerView.Adapter<AvailableFacilitiesAdapter.MyViewHolder> {
        ArrayList<FacilityModel> listoffacilities;

        public AvailableFacilitiesAdapter(ArrayList<FacilityModel> listoffacilities) {
            this.listoffacilities = listoffacilities;
            //  loadDailyTaskArray();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView TxtFacilityName, TxtFacilityCode, TxtStartTime, TxtEndTime;
            public LinearLayout LnrFacility;

            public MyViewHolder(View v) {
                super(v);


                LnrFacility = (LinearLayout) v.findViewById(R.id.LnrFacility);
                TxtFacilityName = (TextView) v.findViewById(R.id.TxtFacilityName);
                TxtFacilityCode = (TextView) v.findViewById(R.id.TxtFacilityCode);
                TxtStartTime = (TextView) v.findViewById(R.id.TxtStartTime);
                TxtEndTime = (TextView) v.findViewById(R.id.TxtEndTime);

            }


        }


        @Override
        public AvailableFacilitiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_available_facilities, parent, false);
            AvailableFacilitiesAdapter.MyViewHolder vh = new AvailableFacilitiesAdapter.MyViewHolder(v);
            return vh;

        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final FacilityModel facilityModel = listoffacilities.get(position);


            holder.TxtFacilityName.setText(facilityModel.getFacilityname());
            holder.TxtFacilityCode.setText(facilityModel.getFacilitycode());
            holder.TxtStartTime.setText(facilityModel.getStartTime());
            holder.TxtEndTime.setText(facilityModel.getEndTime());

            holder.LnrFacility.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ReserveFacility.class);
                    i.putExtra("facilityModel", (Parcelable) facilityModel);
                    i.putExtra("date", date);
                    startActivity(i);
//                    i.putExtra("FacilityName", facilityModel.getFacilityname());
//                    i.putExtra("FacilityCode", facilityModel.getFacilitycode());
//                    i.putExtra("StartTime", facilityModel.getStartTime());
                }
            });
        }

        @Override
        public int getItemCount() {
            return listoffacilities.size();
        }
    }


}
