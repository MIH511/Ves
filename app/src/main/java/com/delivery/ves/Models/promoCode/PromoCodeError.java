package com.delivery.ves.Models.promoCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCodeError {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("message")
    @Expose
    private String msg;

    public Boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
