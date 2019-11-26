package com.seproject.reservemac.model;

public class UserCreds {


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

    private static UserCreds instance;

    public UserCreds() {

    }

    public UserCreds(String username, String role) {
        this.username = username;
        this.role = role;

    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }





    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void setInstance(UserCreds instance) {
        UserCreds.instance = instance;
    }

    public static synchronized UserCreds getInstance() {
        if (instance == null) {
            instance = new UserCreds();
        }
        return instance;
    }


}
