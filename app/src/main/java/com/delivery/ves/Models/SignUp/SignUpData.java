package com.delivery.ves.Models.SignUp;

import com.google.gson.annotations.SerializedName;

public class SignUpData {

    @SerializedName("auth")
    String auth;
    @SerializedName("fullname")
    String fullName;
    @SerializedName("phone")
    String phoneNumber;
    @SerializedName("email")
    String email;
    @SerializedName("gender")
    String gender;
    @SerializedName("address")
    String address;
    @SerializedName("birthdate")
    String birthDate;
    @SerializedName("password")
    String password;

    public SignUpData(String auth, String fullName, String phoneNumber, String email, String gender, String address, String birthDate, String password) {
        this.auth = auth;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.birthDate = birthDate;
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }
}
