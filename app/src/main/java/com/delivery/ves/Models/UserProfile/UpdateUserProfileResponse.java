package com.delivery.ves.Models.UserProfile;

import com.delivery.ves.Models.SignUp.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserProfileResponse {

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("msg")
    @Expose
    String msg;

    @SerializedName("user")
    @Expose
    User user;
    public Boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }
}
