package com.delivery.ves.Models.ShowOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetails {

    @SerializedName("prodid")
    @Expose
    String prodid;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("name_ar")
    @Expose
    String name_ar;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("price")
    @Expose
    String price;

    @SerializedName("price2")
    @Expose
    String price2;

    @SerializedName("quantity")
    @Expose
    String quantity;

    @SerializedName("quantity2")
    @Expose
    String quantity2;

    public String getProdid() {
        return prodid;
    }

    public String getName() {
        return name;
    }

    public String getName_ar() {
        return name_ar;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice2() {
        return price2;
    }

    public String getQuantity2() {
        return quantity2;
    }
}
