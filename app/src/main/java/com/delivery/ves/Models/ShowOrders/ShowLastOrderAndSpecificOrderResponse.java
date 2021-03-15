package com.delivery.ves.Models.ShowOrders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ShowLastOrderAndSpecificOrderResponse {

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("order")
    @Expose
    Order order;

    @SerializedName("orderdetails")
    @Expose
    ArrayList<OrderDetails> orderDetails;

    public ShowLastOrderAndSpecificOrderResponse(Boolean status, Order order, ArrayList<OrderDetails> orderDetails) {
        this.status = status;
        this.order = order;
        this.orderDetails = orderDetails;
    }

    public Boolean getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
}
