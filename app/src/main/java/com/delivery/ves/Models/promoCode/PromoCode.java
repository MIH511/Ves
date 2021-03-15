package com.delivery.ves.Models.promoCode;

import com.google.gson.annotations.SerializedName;

public class PromoCode {

    @SerializedName("auth")
    String auth;

    @SerializedName("promo")
    String promo;

    @SerializedName("total")
    String total;

    public PromoCode(String auth, String promo, String total) {
        this.auth = auth;
        this.promo = promo;
        this.total = total;
    }

    public String getAuth() {
        return auth;
    }

    public String getPromo() {
        return promo;
    }

    public String getTotal() {
        return total;
    }
}
