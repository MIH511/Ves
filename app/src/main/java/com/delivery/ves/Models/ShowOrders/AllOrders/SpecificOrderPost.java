package com.delivery.ves.Models.ShowOrders.AllOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecificOrderPost {

    @SerializedName("auth")
    @Expose
    String auth;

    @SerializedName("orderid")
    @Expose
    String orderId;

    public SpecificOrderPost(String auth, String orderId) {
        this.auth = auth;
        this.orderId = orderId;
    }

    public String getAuth() {
        return auth;
    }

    public String getOrderId() {
        return orderId;
    }
}
