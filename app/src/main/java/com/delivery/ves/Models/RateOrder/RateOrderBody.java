package com.delivery.ves.Models.RateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateOrderBody {

    @SerializedName("auth")
    @Expose
    String auth;

    @SerializedName("orderid")
    @Expose
    String orderId;

    @SerializedName("rate")
    @Expose
    String rate;

    public RateOrderBody(String auth, String orderId, String rate) {
        this.auth = auth;
        this.orderId = orderId;
        this.rate = rate;
    }

    public String getAuth() {
        return auth;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getRate() {
        return rate;
    }
}
