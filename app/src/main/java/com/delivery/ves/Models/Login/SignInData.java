package com.delivery.ves.Models.Login;

import com.google.gson.annotations.SerializedName;

public class SignInData {

    @SerializedName("auth")
    String auth;
    @SerializedName("phoneoremail")
    String phoneNumberOrEmail;
    @SerializedName("password")
    String password;

    public SignInData(String auth, String phoneNumberOrEmail, String password) {
        this.auth = auth;
        this.phoneNumberOrEmail = phoneNumberOrEmail;
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public String getPhoneNumberOrEmail() {
        return phoneNumberOrEmail;
    }

    public String getPassword() {
        return password;
    }
}
