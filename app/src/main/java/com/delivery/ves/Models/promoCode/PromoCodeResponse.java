package com.delivery.ves.Models.promoCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoCodeResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("total")
    @Expose
    private int total;

    public Boolean getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }
}
