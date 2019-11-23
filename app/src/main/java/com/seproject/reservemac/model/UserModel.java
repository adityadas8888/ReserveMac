package com.seproject.reservemac.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {


    public String username = "";
    public String firstname = "";
    public String lastname = "";
    public String password = "";
    public String utaid = "";
    public String role = "";
    public String contactno = "";
    public String streetaddress = "";
    public String zipcode = "";
    public int noshow = 0;
    public int revoked = 0;
    public String email = "";

    private static UserModel instance;

    public UserModel() {

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


    protected UserModel(Parcel in) {
        username = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        password = in.readString();
        utaid = in.readString();
        role = in.readString();
        contactno = in.readString();
        streetaddress = in.readString();
        zipcode = in.readString();
        noshow = in.readInt();
        revoked = in.readInt();
        email = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password){ this.password = password; }

    public String getPassword(){return password;    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUtaid() {
        return utaid;
    }

    public void setUtaid(String utaid) {
        this.utaid = utaid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getNoshow() {
        return noshow;
    }

    public void setNoshow(int noshow) {
        this.noshow = noshow;
    }

    public int getRevoked() {
        return revoked;
    }

    public void setRevoked(int revoked) {
        this.revoked = revoked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static void setInstance(UserModel instance) {
        UserModel.instance = instance;
    }

    public static synchronized UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel();
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
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(password);
        dest.writeString(utaid);
        dest.writeString(role);
        dest.writeString(contactno);
        dest.writeString(streetaddress);
        dest.writeString(zipcode);
        dest.writeInt(noshow);
        dest.writeInt(revoked);
        dest.writeString(email);
    }
}
