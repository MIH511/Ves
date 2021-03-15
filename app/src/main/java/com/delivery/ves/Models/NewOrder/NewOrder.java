package com.delivery.ves.Models.NewOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewOrder {

    @SerializedName("auth")
    @Expose
    String auth;
    @SerializedName("userid")
    @Expose
    String userId;
    @SerializedName("products[]")
    @Expose
    ArrayList<String> productsId;
    @SerializedName("quantity[]")
    @Expose
    ArrayList<String> quantity;
    @SerializedName("quantity2[]")
    @Expose
    ArrayList<String> quantity2;
    @SerializedName("promocode")
    @Expose
    String promocode;

    public NewOrder(String auth, String userId, ArrayList<String> products, ArrayList<String> quantity, String promocode, ArrayList<String> quantity2) {
        this.auth = auth;
        this.userId = userId;
        this.productsId = products;
        this.quantity = quantity;
        this.promocode = promocode;
        this.quantity2=quantity2;
    }

    public String getAuth() {
        return auth;
    }

    public ArrayList<String> getProductsId() {
        return productsId;
    }

    public ArrayList<String> getQuantity() {
        return quantity;
    }

    public String getUserId() {
        return userId;
    }

    public String getPromocode() {
        return promocode;
    }

    public ArrayList<String> getQuantity2() {
        return quantity2;
    }
}
