package com.seproject.reservemac.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.facility_manager.ViewFacilityFM;
import com.seproject.reservemac.model.FacilityModel;
import com.seproject.reservemac.model.UserCreds;
import com.seproject.reservemac.user.ReserveFacility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AllFacilitiesActivity extends AppCompatActivity implements GetRequests.AsyncResponse {

    RecyclerView RecycleAvilableFacilites;
    TextView TxtTotalFacilities;
    AvailableFacilitiesAdapter availableFacilitiesAdapter;
    ArrayList<FacilityModel> facilityModelArrayList;
    String facilityCode = "MR", date = "", time = "";
    UserCreds userCreds = UserCreds.getInstance();
    Spinner SpinnerFType;
    HashMap<String, String> facilitycode = new HashMap<>();

    String faclity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all_facilities);

        RecycleAvilableFacilites = (RecyclerView) findViewById(R.id.RecycleAvilableFacilites);
        TxtTotalFacilities = (TextView) findViewById(R.id.TxtTotalFacilities);
        SpinnerFType = (Spinner) findViewById(R.id.SpinnerFType);
        SpinnerFType.setSelection(0);

        RecycleAvilableFacilites.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RecycleAvilableFacilites.setLayoutManager(llm);

        facilitycode.put("4 Multipurpose Rooms", "MR");
        facilitycode.put("5 Indoor Basketball Court", "IBBC");
        facilitycode.put("9 Volleyball Court", "IVBC");
        facilitycode.put("Indoor Soccer Gymnasium", "SCG");
        facilitycode.put("5 Racquetball Courts", "RBC");
        facilitycode.put("10 Badminton Courts", "BMC");
        facilitycode.put("Table Tennis", "TT");
        facilitycode.put("Conference Rooms", "CR");
        facilitycode.put("2 Outdoor Volleyball Courts", "OVBC");
        facilitycode.put("2 Outdoor Basketball Courts", "OBBC");

        SpinnerFType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                faclity = SpinnerFType.getSelectedItem().toString();

                facilityCode = facilitycode.get(faclity);
                loadAvailableFacilitiesArray();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                facilityCode = "MR";
                SpinnerFType.setSelection(0);
                loadAvailableFacilitiesArray();
            }
        });


//        facilityCode = getIntent().getStringExtra("facilitycode");
//        date = getIntent().getStringExtra("date");
//        time = getIntent().getStringExtra("time");


    }

    private void loadAvailableFacilitiesArray() {

//        http://mohammedmurtuzabhaiji.uta.cloud/se1project/fm/search_all_facility.php?name=BMC

        String type = "SearchAll";
        StringBuilder stringBuilder = new StringBuilder();
        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");
        stringBuilder.append("fm/search_all_facility.php?name=").append(facilityCode);

        String url = stringBuilder.toString();
        new GetRequests(AllFacilitiesActivity.this, url, AllFacilitiesActivity.this, "ListOfFacilitiesActivity").execute("");

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
//                        facilityModel.setStartTime(json.getString("start"));
//                        facilityModel.setEndTime(json.getString("end"));
                        facilityModelArrayList.add(facilityModel);

                    }
                } else {
                    Toast.makeText(this, "Nothing Available.!!", Toast.LENGTH_SHORT).show();
                }


                String size = String.valueOf(facilityModelArrayList.size());
                if (userCreds.getRole().equalsIgnoreCase("fm") && Integer.parseInt(size) > 0) {
                    TxtTotalFacilities.setVisibility(View.VISIBLE);
                    TxtTotalFacilities.setText("Total Available facilities: " + size);
                } else {
                    TxtTotalFacilities.setText("No Facilities Available.!!");
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
                    if (userCreds.getRole().equalsIgnoreCase("fm")) {
                        Intent i = new Intent(view.getContext(), ViewFacilityFM.class);
                        i.putExtra("facilityModel", (Parcelable) facilityModel);
                        i.putExtra("date", date);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(view.getContext(), ReserveFacility.class);
                        i.putExtra("facilityModel", (Parcelable) facilityModel);
                        i.putExtra("date", date);
                        startActivity(i);
                    }
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
