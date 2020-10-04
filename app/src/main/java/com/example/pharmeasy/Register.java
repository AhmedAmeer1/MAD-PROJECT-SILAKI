package com.example.pharmeasy;

public class Register {


    private String email;
    private String password;
    private int contact_number;
    private String Fullname;
    private String id;


    public Register() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String address) {
        this.Fullname = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

