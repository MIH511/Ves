package com.delivery.ves.Models.SignUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiSignUpResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;


    public Boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


}
