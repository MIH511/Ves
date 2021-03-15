package com.delivery.ves.Models.ShowOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.POST;

public class Order {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("0")
    @Expose
    String zero;

    @SerializedName("user_id")
    @Expose
    String userId;

    @SerializedName("1")
    @Expose
    String one;

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("2")
    @Expose
    String two;

    @SerializedName("rating")
    @Expose
    String rating;

    @SerializedName("3")
    @Expose
    String three;

    @SerializedName("promo")
    @Expose
    String promo;

    @SerializedName("4")
    @Expose
    String four;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public String getRating() {
        return rating;
    }

    public String getPromo() {
        return promo;
    }
}
