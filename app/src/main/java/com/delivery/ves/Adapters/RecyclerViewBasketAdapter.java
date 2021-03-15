package com.delivery.ves.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.delivery.ves.Apis.ApiClient;
import com.delivery.ves.Fragments.BasketFragment;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.R;
import com.delivery.ves.utils.PreferenceUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewBasketAdapter extends RecyclerView.Adapter<RecyclerViewBasketAdapter.basketViewHolder> {

    Context c;
    ArrayList<Product> products;

    public RecyclerViewBasketAdapter(Context c, ArrayList<Product> products) {
        this.c = c;
        this.products = products;
    }

    @NonNull
    @Override
    public basketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new basketViewHolder(LayoutInflater.from(c).inflate(R.layout.basket_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull basketViewHolder holder, final int position) {

        String total= String.valueOf(products.get(position).getTotal());
        holder.productprice.setText(total);
        holder.productName.setText(products.get(position).getName());
        holder.productquantity.setText(products.get(position).getCount());
        holder.quantityCleaningLaundry.setText(String.valueOf(products.get(position).getCountLaundry())+" "+c.getResources().getString(R.string.laundry)+"&\n"+c.getResources().getString(R.string.cleaning));
        holder.quantityCleaning.setText(String.valueOf(products.get(position).getCountCleaningLaundry())+" "+c.getResources().getString(R.string.laundry));

        Picasso.get().load(ApiClient.BASE_URL_PHOTO+products.get(position).getImage()).into(holder.productImage);


        holder.removeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(c,products.get(position).getCat(), Toast.LENGTH_SHORT).show();
                if (products.get(position).getCat().equals("women"))
                {
                    String id=products.get(position).getId();
                    BasketFragment.removeWomenItem(position,id,c);
                    Fragment fragment=new BasketFragment();
                    BasketFragment.fragmentTransaction.replace(R.id.frame_container,fragment).commit();

                }
                else if (products.get(position).getCat().equals("men"))
                {
                    String id=products.get(position).getId();
                    BasketFragment.removeMenItem(position,id,c);
                    Fragment fragment=new BasketFragment();
                    BasketFragment.fragmentTransaction.replace(R.id.frame_container,fragment).commit();
                }
                else {
                    String id=products.get(position).getId();
                    BasketFragment.removeKidsItem(position,id,c);
                    Fragment fragment=new BasketFragment();
                    BasketFragment.fragmentTransaction.replace(R.id.frame_container,fragment).commit();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class basketViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productName;
        TextView productquantity;
        TextView productprice;
        TextView quantityCleaning;
        TextView quantityCleaningLaundry;
        ImageView removeBt;
        public basketViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage=itemView.findViewById(R.id.product_image_basket);
            productName=itemView.findViewById(R.id.product_name_basket);
            productquantity=itemView.findViewById(R.id.product_count_basket);
            productprice=itemView.findViewById(R.id.product_price_basket);
            quantityCleaning=itemView.findViewById(R.id.product_cleaning_basket);
            quantityCleaningLaundry=itemView.findViewById(R.id.product_cleaning_laundry_basket);
            removeBt=itemView.findViewById(R.id.remove_bt);
        }
    }
}
