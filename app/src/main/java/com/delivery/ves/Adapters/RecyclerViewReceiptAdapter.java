package com.delivery.ves.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delivery.ves.Models.ShowOrders.OrderDetails;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.R;

import java.util.ArrayList;

public class RecyclerViewReceiptAdapter extends RecyclerView.Adapter<RecyclerViewReceiptAdapter.ReceiptViewHolder> {

    Context context;
    ArrayList<OrderDetails> orderDetails=new ArrayList<>();
    int quantity;
    int price=0;
    int totalPriceOfAProduct;
    TextView total;

    public RecyclerViewReceiptAdapter(Context context, ArrayList<OrderDetails> orderDetails, TextView totalPrice) {
        this.context = context;
        this.orderDetails = orderDetails;
        this.total=totalPrice;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReceiptViewHolder(LayoutInflater.from(context).inflate(R.layout.receipt_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, int position) {

        holder.productName.setText(orderDetails.get(position).getName());
        holder.productQuantityLaundry.setText(orderDetails.get(position).getQuantity2()+" "+context.getResources().getString(R.string.laundry));
        holder.productQuantityClnLaundry.setText(orderDetails.get(position).getQuantity()+" "+context.getResources().getString(R.string.laundryAndCleaning));
        holder.productPriceLaundry.setText(orderDetails.get(position).getPrice()+" "+context.getResources().getString(R.string.money));
        holder.productPriceClnLaundry.setText(orderDetails.get(position).getPrice2()+" "+context.getResources().getString(R.string.money));

        if(!orderDetails.get(position).getQuantity().equals("0") && !orderDetails.get(position).getQuantity2().equals("0"))
        {
            holder.productPriceClnLaundry.setVisibility(View.VISIBLE);
            holder.productQuantityClnLaundry.setVisibility(View.VISIBLE);
            holder.productPriceLaundry.setVisibility(View.VISIBLE);
            holder.productQuantityLaundry.setVisibility(View.VISIBLE);
            price+=(Integer.parseInt(orderDetails.get(position).getPrice2())*Integer.parseInt(orderDetails.get(position).getQuantity()))
                    + (Integer.parseInt(orderDetails.get(position).getPrice()) * Integer.parseInt(orderDetails.get(position).getQuantity2()));

        }
        else {

            if(!orderDetails.get(position).getQuantity2().equals("0"))
            {
                holder.productPriceLaundry.setVisibility(View.VISIBLE);
                holder.productQuantityLaundry.setVisibility(View.VISIBLE);

                price+=(Integer.parseInt(orderDetails.get(position).getPrice())*Integer.parseInt(orderDetails.get(position).getQuantity2()));
            }
            else if(!orderDetails.get(position).getQuantity().equals("0"))
            {
                holder.productPriceClnLaundry.setVisibility(View.VISIBLE);
                holder.productQuantityClnLaundry.setVisibility(View.VISIBLE);

                price+=(Integer.parseInt(orderDetails.get(position).getPrice2())*Integer.parseInt(orderDetails.get(position).getQuantity()));
            }
        }



        total.setText(String.valueOf(price)+" "+context.getResources().getString(R.string.money));
        total.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public class ReceiptViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPriceClnLaundry;
        TextView productPriceLaundry;
        TextView productQuantityClnLaundry;
        TextView productQuantityLaundry;

        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);

            productName=itemView.findViewById(R.id.product_name_receipt);
            productPriceClnLaundry=itemView.findViewById(R.id.price_laundry_cleaning_receipt);
            productPriceLaundry=itemView.findViewById(R.id.price_laundry_receipt);
            productQuantityClnLaundry=itemView.findViewById(R.id.quantity_laundry_cleaning_receipt);
            productQuantityLaundry=itemView.findViewById(R.id.quantity_laundry_receipt);
        }
    }
}
