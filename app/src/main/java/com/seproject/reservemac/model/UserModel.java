package com.seproject.reservemac.model;

public class UserModel {


    public String username = "";
    public String firstname = "";
    public String lastname = "";
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

    public UserModel(String username, String firstname, String lastname, String utaid, String role, String contactno, String streetaddress, String zipcode, int noshow, int revoked, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.utaid = utaid;
        this.role = role;
        this.contactno = contactno;
        this.streetaddress = streetaddress;
        this.zipcode = zipcode;
        this.noshow = noshow;
        this.revoked = revoked;
        this.email = email;
    }


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


}
