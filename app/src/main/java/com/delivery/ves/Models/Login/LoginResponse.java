package com.delivery.ves.Models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("statue")
    @Expose
    private Boolean statue;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getStatue() {
        return statue;
    }

    public String getMsg() {
        return msg;
    }
}
