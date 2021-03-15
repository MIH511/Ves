package com.delivery.ves.Models.SignUp;

import com.delivery.ves.Models.Login.MessageLogin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpSuccessfully {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("user")
    @Expose
    private User userInfo;

    public Boolean getStatus() {
        return status;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public String getMsg() {
        return msg;
    }
}
