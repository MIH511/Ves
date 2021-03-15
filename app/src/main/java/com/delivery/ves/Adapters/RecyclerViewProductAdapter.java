package com.delivery.ves.Adapters;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.delivery.ves.Activities.MainActivity;
import com.delivery.ves.Apis.ApiClient;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.utils.ConvertArabicNameToString;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.paperdb.Paper;

import static com.delivery.ves.Activities.MainActivity.item;
import static com.delivery.ves.Activities.MainActivity.mNotificationsCount;
import static com.delivery.ves.Activities.welcomeActivity.language;

public class RecyclerViewProductAdapter extends RecyclerView.Adapter<RecyclerViewProductAdapter.ProductViewHolder> {


    Context c;
    ArrayList<Product> getProducts;
    ArrayList<Product> setProducts=new ArrayList<>();
    String url;
    int totalCleaningLaundry=0;
    int totalLaundry=0;
    int total=0;
    int productCountCleaningLaundry;
    int productCountLaundry;
    int quantity;

    public RecyclerViewProductAdapter(Context c, ArrayList<Product> getProducts) {
        this.c = c;
        this.getProducts = getProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ProductViewHolder(LayoutInflater.from(c).inflate(R.layout.product_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {

        holder.productPrice.setVisibility(View.INVISIBLE);

//        productCountLaundry= Integer.parseInt(getProducts.get(position).getCountLaundry());
//        productCountCleaningLaundry= Integer.parseInt(getProducts.get(position).getCountCleaningLaundry());

        String arabicName= ConvertArabicNameToString.getArabicString(getProducts.get(position).getNameAr());
        Paper.init(c);
        if(language.equals("en")){
            holder.productName.setText(getProducts.get(position).getName());
        }
        else {
            holder.productName.setText(arabicName);
        }

        holder.productDecreaseClnLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getProducts.get(position).getCountCleaningLaundry().equalsIgnoreCase("0")){
                    getProducts.get(position).setCountCleaningLuandry(Integer.parseInt(getProducts.get(position).getCountCleaningLaundry())-1);
                    holder.productCountClnLaundry.setText(getProducts.get(position).getCountCleaningLaundry());
                    totalCleaningLaundry=totalCleaningLaundry-Integer.parseInt(getProducts.get(position).getPrice2());

                    holder.productPrice.setText(String.valueOf(totalCleaningLaundry)+" "+c.getResources().getString(R.string.money));
                }
                if (productCountCleaningLaundry==0 &&productCountLaundry==0){
                    holder.productCountClnLaundry.setText("0");
                    productCountCleaningLaundry=0;
                    getProducts.get(position).setCountCleaningLuandry(0);
                    holder.productPrice.setVisibility(View.INVISIBLE);

                }
            }
        });

        holder.productInecreaseClnLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProducts.get(position).setCountCleaningLuandry(Integer.parseInt(getProducts.get(position).getCountCleaningLaundry())+1);
                holder.productCountClnLaundry.setText(getProducts.get(position).getCountCleaningLaundry());
                holder.productPrice.setVisibility(View.VISIBLE);
                totalCleaningLaundry=Integer.parseInt(getProducts.get(position).getPrice2())*Integer.parseInt(getProducts.get(position).getCountCleaningLaundry());
                holder.productPrice.setText(String.valueOf(totalCleaningLaundry)+" "+c.getResources().getString(R.string.money));
            }
        });

        holder.productInecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProducts.get(position).setCountLaundry(Integer.parseInt(getProducts.get(position).getCountLaundry())+1);
                holder.productCount.setText(getProducts.get(position).getCountLaundry());
                holder.productPrice.setVisibility(View.VISIBLE);
                totalLaundry= Integer.parseInt(getProducts.get(position).getPrice())*Integer.parseInt(getProducts.get(position).getCountLaundry());

                holder.productPrice.setText(String.valueOf(totalLaundry)+" "+c.getResources().getString(R.string.money));
                Log.d("total price", String.valueOf(totalLaundry)+" "+Integer.parseInt(getProducts.get(position).getCountLaundry()));
            }
        });

        holder.productDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productCountLaundry>0){
                    getProducts.get(position).setCountLaundry(Integer.parseInt(getProducts.get(position).getCountLaundry())-1);
                    holder.productCount.setText(getProducts.get(position).getCountLaundry());
                    totalLaundry=totalLaundry-Integer.parseInt(getProducts.get(position).getPrice());
                    holder.productPrice.setText(String.valueOf(totalLaundry)+" "+c.getResources().getString(R.string.money));
                }
                if (productCountLaundry==0 && productCountCleaningLaundry==0){
                    holder.productCount.setText("0");
                    productCountLaundry=0;
                    getProducts.get(position).setCountLaundry(0);
                    holder.productPrice.setVisibility(View.INVISIBLE);
                }

            }
        });

        url= ApiClient.BASE_URL_PHOTO+getProducts.get(position).getImage();

        Picasso.get().load(url).into(holder.productImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        holder.addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total=totalCleaningLaundry+totalLaundry;
                quantity=Integer.parseInt(holder.productCount.getText().toString())+Integer.parseInt(holder.productCountClnLaundry.getText().toString());


                if(total!=0)
                {

                    setProducts.add(new Product(getProducts.get(position).getId(),getProducts.get(position).getName(),getProducts.get(position).getNameAr(),
                            getProducts.get(position).getPrice(),getProducts.get(position).getImage(),
                            String.valueOf(quantity),total,getProducts.get(position).getCat(),getProducts.get(position).getCountCleaningLaundry(),getProducts.get(position).getCountLaundry()));

                    PreferenceUtils.setProductDetailsMen(setProducts,c);
                    MainActivity.menu=MainActivity.bottomNavigationView.getMenu();
                    item=MainActivity.menu.findItem(R.id.basket);
                    MainActivity.badge=MainActivity.bottomNavigationView.getOrCreateBadge(item.getItemId());
                    MainActivity.mNotificationsCount=true;
                    MainActivity.badge.setVisible(mNotificationsCount);
                    Toast.makeText(c, "Item added", Toast.LENGTH_SHORT).show();
                }
                else if(total==0)
                {
                    Toast.makeText(c, "set your quantity, please", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



    @Override
    public int getItemCount() {
        return getProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        ImageView productDecrease;
        TextView productCount;
        ImageView productInecrease;
        ImageView addToBasket;
        ImageView productDecreaseClnLaundry;
        ImageView productInecreaseClnLaundry;
        TextView productCountClnLaundry;
        ProgressBar progressBar;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
            productDecrease=itemView.findViewById(R.id.product_count_decrease);
            productCount=itemView.findViewById(R.id.product_count);
            productInecrease=itemView.findViewById(R.id.product_count_increase);
            addToBasket=itemView.findViewById(R.id.add_to_basket_bt);
            productCountClnLaundry=itemView.findViewById(R.id.product_count_cleaning_luandry);
            productDecreaseClnLaundry=itemView.findViewById(R.id.product_count_decrease_cleaning_laundry);
            productInecreaseClnLaundry=itemView.findViewById(R.id.product_count_increase_cleaning_laundry);
            progressBar=itemView.findViewById(R.id.load_image_progress_bar);

        }
    }
}
