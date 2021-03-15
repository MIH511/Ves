package com.delivery.ves.Models.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SetProductData {

    @SerializedName("auth")
    @Expose
    String auth;
    @SerializedName("cat")
    @Expose
    String cat;

    public SetProductData(String auth, String category) {
        this.auth = auth;
        this.cat = category;
    }
}
