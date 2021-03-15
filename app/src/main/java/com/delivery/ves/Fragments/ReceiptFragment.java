package com.delivery.ves.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.delivery.ves.Adapters.RecyclerViewReceiptAdapter;
import com.delivery.ves.Apis.ApiClient;
import com.delivery.ves.Models.ShowOrders.OrderDetails;
import com.delivery.ves.Models.ShowOrders.ShowLastOrderAndSpecificOrderResponse;
import com.delivery.ves.Models.ShowOrders.ShowOrderPost;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.R;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReceiptFragment extends Fragment {


    RecyclerView receiptRecyclerView;
    RecyclerViewReceiptAdapter receiptAdapter;
    ArrayList<Product> products=new ArrayList<>();
    TextView totalPrice;
    ProgressBar progressBar;
    int total;
    public static String orderId;

    public ReceiptFragment(ArrayList<Product> products) {
        this.products = products;
    }

    public ReceiptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_receipt, container, false);
        totalPrice=view.findViewById(R.id.total_price_receipt);
        receiptRecyclerView=view.findViewById(R.id.receipt_recycler_view);
        progressBar=view.findViewById(R.id.progress_bar_receipt);
        receiptRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        getLastProduct();
        return view;
    }

    private void getLastProduct() {

        progressBar.setVisibility(View.VISIBLE);
        ShowOrderPost orderPost=new ShowOrderPost("lastorder", PreferenceUtils.getId(getContext()));
        Utils.getApi().LastOrder(orderPost.getAuth(),orderPost.getUserid())
                .enqueue(new Callback<ShowLastOrderAndSpecificOrderResponse>() {
                    @Override
                    public void onResponse(Call<ShowLastOrderAndSpecificOrderResponse> call, Response<ShowLastOrderAndSpecificOrderResponse> response) {
                        Log.d("lastOrder",response.body().getOrderDetails().get(0).getName());
                        orderId=response.body().getOrder().getId();
                        PreferenceUtils.saveLastOrderId(orderId,getContext());
                        ArrayList<OrderDetails> orderDetails=response.body().getOrderDetails();
                        receiptAdapter=new RecyclerViewReceiptAdapter(getContext(),orderDetails,totalPrice);
                        receiptRecyclerView.setAdapter(receiptAdapter);
                        receiptAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ShowLastOrderAndSpecificOrderResponse> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });


    }
}