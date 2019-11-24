package com.seproject.reservemac.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservationModel implements Parcelable {


    public int reservationid = 0;
    public String datetime = "";
    public int violation = 0;
    public String viodetails = "";
    public int resstatus = 0;
    public String username = "";
    public String facilitycode = "";
    public String facilityname = "";

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
        datetime = in.readString();
        facilitycode = in.readString();
        viodetails = in.readString();
        reservationid = in.readInt();
        violation = in.readInt();
        resstatus = in.readInt();
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

    public String getFacilityName() {
        return facilityname;
    }

    public void setFacilityName(String facilityname) {
        this.facilityname = facilityname;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(datetime);
        dest.writeString(facilitycode);
        dest.writeString(facilityname);
        dest.writeInt(reservationid);
        dest.writeInt(resstatus);
        dest.writeInt(violation);
        dest.writeString(viodetails);
    }
}
