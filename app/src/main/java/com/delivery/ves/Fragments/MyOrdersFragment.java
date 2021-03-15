package com.delivery.ves.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.delivery.ves.Adapters.RecyclerViewMyOrderAdapter;
import com.delivery.ves.Models.ShowOrders.AllOrders.AllOrderResponse;
import com.delivery.ves.Models.ShowOrders.AllOrders.OrderDetailsAllOrders;
import com.delivery.ves.Models.ShowOrders.Order;
import com.delivery.ves.Models.ShowOrders.AllOrders.ProductAllOrder;
import com.delivery.ves.Models.ShowOrders.ShowOrderPost;
import com.delivery.ves.R;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    RecyclerView myOrderRecyclerView;
    RecyclerViewMyOrderAdapter myOrderAdapter;
    RecyclerView orderItemsRecyclerView;
    ProgressBar progressBar;

    ArrayList<Order> orders=new ArrayList<>();
    ArrayList<Order> ordersSize=new ArrayList<>();
    ArrayList<List<ProductAllOrder>> productAllOrders=new ArrayList<>();
    ArrayList<List<OrderDetailsAllOrders>> orderDetails=new ArrayList<>();
    public MyOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_my_orders, container, false);
        myOrderRecyclerView=view.findViewById(R.id.my_order_recyclerView);
        progressBar=view.findViewById(R.id.progress_bar_my_orders);
//        orderItemsRecyclerView=view.findViewById(R.id.my_order_items_recyclerView);
//        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        myOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        getAllOrder();
        return view;
    }

    private void getAllOrder() {

        progressBar.setVisibility(View.VISIBLE);
        ShowOrderPost post=new ShowOrderPost("allorders", PreferenceUtils.getId(getContext()));
        Utils.getApi().AllOrder(post.getAuth(),post.getUserid()).enqueue(new Callback<AllOrderResponse>() {
            @Override
            public void onResponse(Call<AllOrderResponse> call, Response<AllOrderResponse> response) {
                if (response.body().getStatus()){
                orders=response.body().getOrders();
                orderDetails=response.body().getDetailsAllOrders();

                productAllOrders=response.body().getProductAllOrders();
                myOrderAdapter=new RecyclerViewMyOrderAdapter(getContext(),orders,productAllOrders,orderDetails);
                myOrderRecyclerView.setAdapter(myOrderAdapter);
                myOrderAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AllOrderResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
