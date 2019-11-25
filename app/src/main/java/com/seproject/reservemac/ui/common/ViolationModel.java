package com.seproject.reservemac.ui.common;

import android.os.Parcel;
import android.os.Parcelable;


public  class ViolationModel implements Parcelable {


    public int reservationid = 0;
    public String datetime = "";
    public int violation = 0;
    public String viodetails = "";
    public int resstatus = 0;
    public String username = "";
    public String facilitycode = "";
    public String facilityname = "";
    public String violations = "";

    private  static  ViolationModel instance;
    public ViolationModel(){}

    protected ViolationModel(Parcel in) {
        reservationid = in.readInt();
        datetime = in.readString();
        violation = in.readInt();
        viodetails = in.readString();
        resstatus = in.readInt();
        username = in.readString();
        facilitycode = in.readString();
        facilityname = in.readString();
        violations = in.readString();
    }

    public static final Creator<ViolationModel> CREATOR = new Creator<ViolationModel>() {
        @Override
        public ViolationModel createFromParcel(Parcel in) {
            return new ViolationModel(in);
        }

        @Override
        public ViolationModel[] newArray(int size) {
            return new ViolationModel[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getReservationid() {
        return reservationid;
    }

    public void setReservationid(int reservationid) {
        this.reservationid = reservationid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getViolation() {
        return violation;
    }

    public void setViolation(int violation) {
        this.violation = violation;
    }

    public String getViodetails() {
        return viodetails;
    }

    public void setViodetails(String viodetails) {
        this.viodetails = viodetails;
    }

    public int getResstatus() {
        return resstatus;
    }

    public void setResstatus(int resstatus) {
        this.resstatus = resstatus;
    }

    public String getFacilitycode() {
        return facilitycode;
    }

    public void setFacilitycode(String facilitycode) {
        this.facilitycode = facilitycode;
    }


    public void setViolations(String violations) {
        this.violations= violations;
    }


    public String getFacilityName() {
        return facilityname;
    }

    public void setFacilityName(String facilityname) {
        this.facilityname = facilityname;
    }


    public static void setInstance(ViolationModel instance) {
        ViolationModel.instance = instance;
    }

    public static synchronized ViolationModel getInstance() {
        if (instance == null) {
            instance = new ViolationModel();
        }
        return instance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(reservationid);
        dest.writeString(datetime);
        dest.writeInt(violation);
        dest.writeString(viodetails);
        dest.writeInt(resstatus);
        dest.writeString(username);
        dest.writeString(facilitycode);
        dest.writeString(facilityname);
        dest.writeString(violations);
    }
}
