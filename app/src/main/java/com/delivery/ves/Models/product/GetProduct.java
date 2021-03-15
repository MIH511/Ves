package com.delivery.ves.Models.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetProduct {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;


    public ArrayList<Product> getProducts() {
        return products;
    }

    public Boolean getStatus() {
        return status;
    }
}
