package com.seproject.reservemac.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservationModel implements Parcelable {


    public int reservationid = 0;
    public String date = "";
    public String starttime = "";
    public String endtime = "";
    public int violation = 0;
    public String viodetails = "";
    public int resstatus = 0;
    public String username = "";
    public String facilitycode = "";
    public String name = "";

    public String facilitydescription = "";
    public int deposit = 0;

    private static ReservationModel instance;

    public ReservationModel() {

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


    protected ReservationModel(Parcel in) {
        username = in.readString();
        date = in.readString();
        starttime = in.readString();
        endtime = in.readString();
        facilitycode = in.readString();
        name = in.readString();
        facilitydescription = in.readString();
        deposit = in.readInt();
        reservationid = in.readInt();
        resstatus = in.readInt();
        violation = in.readInt();
        viodetails = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(date);
        dest.writeString(starttime);
        dest.writeString(endtime);
        dest.writeString(facilitycode);
        dest.writeString(name);
        dest.writeString(facilitydescription);
        dest.writeInt(deposit);
        dest.writeInt(reservationid);
        dest.writeInt(resstatus);
        dest.writeInt(violation);
        dest.writeString(viodetails);
    }

    public static final Creator<ReservationModel> CREATOR = new Creator<ReservationModel>() {
        @Override
        public ReservationModel createFromParcel(Parcel in) {
            return new ReservationModel(in);
        }

        @Override
        public ReservationModel[] newArray(int size) {
            return new ReservationModel[size];
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getstarttime() {
        return starttime;
    }

    public void setstarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndTime() {
        return endtime;
    }

    public void setEndTime(String endtime) {
        this.endtime = endtime;
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

    public String getFacilityName() {
        return name;
    }

    public void setFacilityName(String name) {
        this.name = name;
    }

    public String getFacilitydescription() {
        return facilitydescription;
    }

    public void setFacilitydescription(String facilitydescription) {
        this.facilitydescription = facilitydescription;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }


    public static void setInstance(ReservationModel instance) {
        ReservationModel.instance = instance;
    }

    public static synchronized ReservationModel getInstance() {
        if (instance == null) {
            instance = new ReservationModel();
        }
        return instance;
    }


    @Override
    public int describeContents() {
        return 0;
    }


}
