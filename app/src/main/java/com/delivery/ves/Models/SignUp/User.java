package com.delivery.ves.Models.SignUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("6")
    @Expose
    private String _6;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("7")
    @Expose
    private String _7;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("8")
    @Expose
    private String _8;

    public User() {
    }

    public User(String id, String fullname, String phone, String email, String password, String birthdate, String gender, String address, String image) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

}
