package com.delivery.ves.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.delivery.ves.Activities.SpecificOrderActivity;
import com.delivery.ves.Models.ShowOrders.AllOrders.OrderDetailsAllOrders;
import com.delivery.ves.Models.ShowOrders.AllOrders.ProductAllOrder;
import com.delivery.ves.Models.ShowOrders.Order;
import com.delivery.ves.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMyOrderAdapter extends RecyclerView.Adapter<RecyclerViewMyOrderAdapter.MyOrderViewHolder> {

    Context c;
    Order orders=new Order();
    ArrayList<Order> ordersSize=new ArrayList<>();
    ArrayList<List<ProductAllOrder>> allOrderDetails;
    ArrayList<List<OrderDetailsAllOrders>> details;
    RecyclerViewItemsMyOrderAdapter itemsMyOrderAdapter;

    public RecyclerViewMyOrderAdapter(Context c, ArrayList<Order> orders, ArrayList<List<ProductAllOrder>> allOrderDetails, ArrayList<List<OrderDetailsAllOrders>> orderDetails) {
        this.c = c;
        this.ordersSize = orders;
        this.allOrderDetails = allOrderDetails;
        this.details=orderDetails;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyOrderViewHolder(LayoutInflater.from(c).inflate(R.layout.my_order_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, final int position) {

        holder.orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.VERTICAL,false));
        itemsMyOrderAdapter=new RecyclerViewItemsMyOrderAdapter(c,allOrderDetails.get(position),holder.price,ordersSize.get(position).getId());
        holder.orderItemsRecyclerView.setAdapter(itemsMyOrderAdapter);
        itemsMyOrderAdapter.notifyDataSetChanged();
        orders=ordersSize.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(c, String.valueOf(position+" "+details.get(position).get(0).getOrderId()), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(c, SpecificOrderActivity.class);
                intent.putExtra("orderId",details.get(position).get(0).getOrderId());
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersSize.size();
    }

    public class MyOrderViewHolder extends RecyclerView.ViewHolder {

        RecyclerView orderItemsRecyclerView;
        TextView price;
        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemsRecyclerView=itemView.findViewById(R.id.my_order_items_recyclerView);
            price=itemView.findViewById(R.id.price_my_order);

        }
    }
}
