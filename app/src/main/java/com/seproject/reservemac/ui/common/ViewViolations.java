package com.seproject.reservemac.ui.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.model.UserCreds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewViolations extends AppCompatActivity implements GetRequests.AsyncResponse {

    //username,violation,violationdetails,facilitycode
    RecyclerView RecycleViewviolation;
    ViewViolationsAdapter viewViolationsAdapter;
    ArrayList<ViolationModel> violationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_violations);
        RecycleViewviolation = findViewById(R.id.RecycleViewViolations);
        RecycleViewviolation.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        RecycleViewviolation.setLayoutManager(llm);
        loadViolationsArray();
    }

    private void loadViolationsArray() {
        String type = "ViewViolation";
        StringBuilder stringBuilder = new StringBuilder();
        UserCreds userCreds = UserCreds.getInstance();
        stringBuilder.append("user/view_violation.php?username=").append(userCreds.getUsername());
        String url = stringBuilder.toString();
        new GetRequests(ViewViolations.this, url, ViewViolations.this, "ViewViolation").execute("");

    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {

            violationModelArrayList = new ArrayList<>();
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("content");
                for (int i = 0; i < jsonArray.length(); i++) {
                    ViolationModel violationModel = new ViolationModel();

                    JSONObject json = jsonArray.getJSONObject(i);
                    violationModel.setReservationid(Integer.parseInt((json.getString("reservationid"))));
                    violationModel.setViodetails(json.getString("viodetails"));
                    violationModel.setFacilitycode(json.getString("facilitycode"));
                    violationModelArrayList.add(violationModel);
                }
                viewViolationsAdapter = new ViewViolationsAdapter(violationModelArrayList);
                RecycleViewviolation.setAdapter(viewViolationsAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public class ViewViolationsAdapter extends RecyclerView.Adapter<ViewViolationsAdapter.MyViewHolder> {
        ArrayList<ViolationModel> listofviolation;

        public ViewViolationsAdapter(ArrayList<ViolationModel> listofviolation) {
            this.listofviolation = listofviolation;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView ReservationID, FacilityCode, Violation;

            public MyViewHolder(View v) {
                super(v);

                ReservationID = (TextView) v.findViewById(R.id.TxtReservationID);
                FacilityCode = (TextView) v.findViewById(R.id.TxtFacilityCode);
                Violation =  (TextView)v.findViewById(R.id.TxtViolation);
            }
        }

        @NonNull
        @Override
        public ViewViolationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_view_violations, parent, false);
            ViewViolationsAdapter.MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewViolationsAdapter.MyViewHolder holder, int position) {
            final ViolationModel violationModel = listofviolation.get(position);

            holder.ReservationID.setText("Res ID: "+String.valueOf(violationModel.getReservationid()));
            holder.FacilityCode.setText(violationModel.getFacilitycode());
            holder.Violation.setText(violationModel.getViodetails());
        }

        @Override
        public int getItemCount() {
            return listofviolation.size();
        }

    }
}
