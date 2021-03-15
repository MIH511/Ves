package com.delivery.ves.Models.RateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateOrderResponse {

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("msg")
    @Expose
    String msg;

    public Boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
