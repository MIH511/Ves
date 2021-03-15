package com.delivery.ves.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delivery.ves.Activities.SpecificOrderActivity;
import com.delivery.ves.Apis.ApiClient;
import com.delivery.ves.Models.ShowOrders.AllOrders.AllOrderResponse;
import com.delivery.ves.Models.ShowOrders.AllOrders.ProductAllOrder;
import com.delivery.ves.Models.ShowOrders.Order;
import com.delivery.ves.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItemsMyOrderAdapter extends RecyclerView.Adapter<RecyclerViewItemsMyOrderAdapter.ItemMyOrderViewHolder> {

    Context c;
    List<ProductAllOrder> orders;
    TextView price;
    int total;
    int getTotal=0;
    int amount;
    String id;
    public RecyclerViewItemsMyOrderAdapter(Context c, List<ProductAllOrder> orders, TextView price, String id) {
        this.c = c;
        this.orders = orders;
        this.price=price;
        this.id=id;
    }

    @NonNull
    @Override
    public ItemMyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemMyOrderViewHolder(LayoutInflater.from(c).inflate(R.layout.ticket_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMyOrderViewHolder holder, final int position) {

        total=(Integer.parseInt(orders.get(position).getPrice())*Integer.parseInt(orders.get(position).getQuantity2()))
                +(Integer.parseInt(orders.get(position).getPrice2()) * Integer.parseInt(orders.get(position).getQuantity()));

        amount=Integer.parseInt(orders.get(position).getQuantity())+Integer.parseInt(orders.get(position).getQuantity2());
        holder.productName.setText(orders.get(position).getName());
        holder.productPrice.setText(String.valueOf(total));
        holder.productAmount.setText(String.valueOf(amount));
        Picasso.get().load(ApiClient.BASE_URL_PHOTO+orders.get(position).getImage())
                .into(holder.productImage);

        getTotal=getTotal+total;
        price.setText(String.valueOf(getTotal)+c.getResources().getString(R.string.money));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(c, SpecificOrderActivity.class);
                intent.putExtra("orderId",id);
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ItemMyOrderViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productAmount;
        TextView productPrice;
       public ItemMyOrderViewHolder(@NonNull View itemView) {
           super(itemView);
           productAmount=itemView.findViewById(R.id.product_amount_my_order);
           productImage=itemView.findViewById(R.id.product_image_my_order);
           productName=itemView.findViewById(R.id.product_name_my_order);
           productPrice=itemView.findViewById(R.id.product_price_my_order);
       }
   }
}
