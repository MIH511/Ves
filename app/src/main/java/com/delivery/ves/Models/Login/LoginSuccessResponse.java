package com.delivery.ves.Models.Login;

import com.delivery.ves.Models.SignUp.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSuccessResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private User msg;

    public Boolean getStatus() {
        return status;
    }

    public User getMsg() {
        return msg;
    }
}
