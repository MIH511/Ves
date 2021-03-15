package com.delivery.ves.Models.ShowOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowOrderPost {

    @SerializedName("auth")
    @Expose
    String auth;

    @SerializedName("userid")
    @Expose
    String userid;

    public ShowOrderPost(String auth, String userid) {
        this.auth = auth;
        this.userid = userid;
    }

    public String getAuth() {
        return auth;
    }

    public String getUserid() {
        return userid;
    }
}
