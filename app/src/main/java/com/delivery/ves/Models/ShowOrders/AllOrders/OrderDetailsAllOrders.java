package com.delivery.ves.Models.ShowOrders.AllOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsAllOrders {

    @SerializedName("id")
    @Expose
    String Id;

    @SerializedName("order_id")
    @Expose
    String orderId;


    @SerializedName("prod_id")
    @Expose
    String prodcutId;


    @SerializedName("quantity")
    @Expose
    String quantity;


    @SerializedName("quantity2")
    @Expose
    String quantity2;


    public String getId() {
        return Id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProdcutId() {
        return prodcutId;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getQuantity2() {
        return quantity2;
    }
}
