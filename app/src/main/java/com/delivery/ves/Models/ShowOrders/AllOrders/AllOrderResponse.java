package com.delivery.ves.Models.ShowOrders.AllOrders;

import com.delivery.ves.Models.ShowOrders.Order;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllOrderResponse {

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("order")
    @Expose
    ArrayList<Order> orders;

    @SerializedName("orderdetails")
    @Expose
    ArrayList<List<OrderDetailsAllOrders>> detailsAllOrders;

    @SerializedName("products")
    @Expose
    ArrayList<List<ProductAllOrder>> productAllOrders;


    public Boolean getStatus() {
        return status;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<List<ProductAllOrder>> getProductAllOrders() {
        return productAllOrders;
    }

    public ArrayList<List<OrderDetailsAllOrders>> getDetailsAllOrders() {
        return detailsAllOrders;
    }

}
