package com.delivery.ves.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.delivery.ves.Adapters.RecyclerViewReceiptAdapter;
import com.delivery.ves.Fragments.ReceiptFragment;
import com.delivery.ves.Models.ShowOrders.AllOrders.SpecificOrderPost;
import com.delivery.ves.Models.ShowOrders.OrderDetails;
import com.delivery.ves.Models.ShowOrders.ShowLastOrderAndSpecificOrderResponse;
import com.delivery.ves.R;
import com.delivery.ves.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecificOrderActivity extends AppCompatActivity {

    String orderId;
    Fragment fragment;

    RecyclerView receiptRecyclerView;
    RecyclerViewReceiptAdapter receiptAdapter;

    TextView totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_order);

        totalPrice=findViewById(R.id.total_price_specific_order);
        receiptRecyclerView=findViewById(R.id.specific_order_recycler_view);
        receiptRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        Intent intent=getIntent();
        orderId=intent.getStringExtra("orderId");
        Log.d("orderId", String.valueOf(orderId));

        getData(orderId);

    }

    private void getData(String orderId) {

        SpecificOrderPost orderPost=new SpecificOrderPost("oneorder",orderId);

        Utils.getApi().SpecificOrder(orderPost.getAuth(),orderPost.getOrderId())
                .enqueue(new Callback<ShowLastOrderAndSpecificOrderResponse>() {
                    @Override
                    public void onResponse(Call<ShowLastOrderAndSpecificOrderResponse> call, Response<ShowLastOrderAndSpecificOrderResponse> response) {

                        ArrayList<OrderDetails> orderDetails=response.body().getOrderDetails();
                        receiptAdapter=new RecyclerViewReceiptAdapter(SpecificOrderActivity.this,orderDetails,totalPrice);
                        receiptRecyclerView.setAdapter(receiptAdapter);
                        receiptAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ShowLastOrderAndSpecificOrderResponse> call, Throwable t) {

                    }
                });
    }
}