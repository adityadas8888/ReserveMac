package com.seproject.reservemac.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FacilityModel implements Parcelable {


    public String facilitycode = "";
    public String facilityname = "";
    public String facilitytype = "";
    public String facilitydescription = "";
    public float interval = 0;
    public String starttime = "";
    public String endtime = "";
    public int availability = 0;
    public int deposit = 0;

    private static FacilityModel instance;

    public FacilityModel() {

    }

//    public UserModel(String username, String firstname, String lastname, String utaid, String role, String contactno, String streetaddress, String zipcode, int noshow, int revoked, String email) {
//        this.username = username;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.utaid = utaid;
//        this.role = role;
//        this.contactno = contactno;
//        this.streetaddress = streetaddress;
//        this.zipcode = zipcode;
//        this.noshow = noshow;
//        this.revoked = revoked;
//        this.email = email;
//    }


    protected FacilityModel(Parcel in) {
        facilityname = in.readString();
        facilitycode = in.readString();
        facilitydescription = in.readString();
        facilitytype = in.readString();
        starttime = in.readString();
        endtime = in.readString();
        availability = in.readInt();
        deposit = in.readInt();

        interval = in.readFloat();
    }

    public static final Creator<FacilityModel> CREATOR = new Creator<FacilityModel>() {
        @Override
        public FacilityModel createFromParcel(Parcel in) {
            return new FacilityModel(in);
        }

        @Override
        public FacilityModel[] newArray(int size) {
            return new FacilityModel[size];
        }
    };

    public String getFacilitycode() {
        return facilitycode;
    }

    public void setFacilitycode(String facilitycode) {
        this.facilitycode = facilitycode;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    public String getFacilitydescription() {
        return facilitydescription;
    }

    public void setFacilitydescription(String facilitydescription) {
        this.facilitydescription = facilitydescription;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public String getStartTime() {
        return starttime;
    }

    public void setStartTime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndTime() {
        return endtime;
    }

    public void setEndTime(String endtime) {
        this.endtime = endtime;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public static void setInstance(FacilityModel instance) {
        FacilityModel.instance = instance;
    }

    public static synchronized FacilityModel getInstance() {
        if (instance == null) {
            instance = new FacilityModel();
        }
        return instance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(facilitytype);
        dest.writeString(facilitycode);
        dest.writeString(facilityname);
        dest.writeString(facilitydescription);

        dest.writeInt(deposit);
        dest.writeInt(availability);
        dest.writeString(starttime);
        dest.writeString(endtime);
        dest.writeFloat(interval);
    }
}
