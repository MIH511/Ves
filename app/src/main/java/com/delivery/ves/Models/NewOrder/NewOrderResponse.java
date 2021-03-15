package com.delivery.ves.Models.NewOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewOrderResponse {

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("message")
    @Expose
    String message;

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
