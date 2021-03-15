package com.delivery.ves.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delivery.ves.Activities.MainActivity;
import com.delivery.ves.Apis.ApiClient;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.R;
import com.delivery.ves.utils.ConvertArabicNameToString;
import com.delivery.ves.utils.PreferenceUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.paperdb.Paper;

import static com.delivery.ves.Activities.MainActivity.item;

import static com.delivery.ves.Activities.MainActivity.mNotificationsCount;
import static com.delivery.ves.Activities.welcomeActivity.language;

public class RecyclerViewWomenAdapter extends RecyclerView.Adapter<RecyclerViewWomenAdapter.WomenViewHolder> {

    Context c;
    ArrayList<Product> getProducts=new ArrayList<>();
    ArrayList<Product> setProducts=new ArrayList<>();

    int productCountCleaningLaundry=0;
    int productCountLaundry=0;
    String url;
    int totalCleaningLaundry=0;
    int totalLaundry=0;
    int total=0;
    int quantity;


    public RecyclerViewWomenAdapter(Context c, ArrayList<Product> getProducts) {
        this.c = c;
        this.getProducts = getProducts;
    }

    @NonNull
    @Override
    public WomenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WomenViewHolder(LayoutInflater.from(c).inflate(R.layout.product_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final WomenViewHolder holder, final int position) {

        holder.productPrice.setVisibility(View.INVISIBLE);

        String arabicName=ConvertArabicNameToString.getArabicString(getProducts.get(position).getNameAr());

        Paper.init(c);
        if(language.equals("en")){
            holder.productName.setText(getProducts.get(position).getName());
        }
        else {
            holder.productName.setText(arabicName);
        }
        Log.d("arabicText",arabicName);

        holder.productDecreaseCln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productCountCleaningLaundry>0){
                    holder.productCountCln.setText(String.valueOf(--productCountCleaningLaundry));
                    totalCleaningLaundry=totalCleaningLaundry-Integer.parseInt(getProducts.get(position).getPrice2());

                    holder.productPrice.setText(String.valueOf(totalCleaningLaundry)+" "+c.getResources().getString(R.string.money));
                }
                if (productCountCleaningLaundry==0 &&productCountLaundry==0){
                    holder.productCountCln.setText("0");
                    productCountCleaningLaundry=0;
                    holder.productPrice.setVisibility(View.INVISIBLE);

                }
            }
        });

        holder.productInecreaseCln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.productCountCln.setText(String.valueOf(++productCountCleaningLaundry));
                holder.productPrice.setVisibility(View.VISIBLE);
                totalCleaningLaundry=Integer.parseInt(getProducts.get(position).getPrice2())*productCountCleaningLaundry;
                holder.productPrice.setText(String.valueOf(totalCleaningLaundry)+" "+c.getResources().getString(R.string.money));
            }
        });

        holder.productInecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.productCount.setText(String.valueOf(++productCountLaundry));
                holder.productPrice.setVisibility(View.VISIBLE);
                totalLaundry= Integer.parseInt(getProducts.get(position).getPrice())*productCountLaundry;

                holder.productPrice.setText(String.valueOf(totalLaundry)+" "+c.getResources().getString(R.string.money));
                Log.d("total price", String.valueOf(totalLaundry)+" "+productCountLaundry);
            }
        });

        holder.productDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productCountLaundry>0){
                    holder.productCount.setText(String.valueOf(--productCountLaundry));
                    totalLaundry=totalLaundry-Integer.parseInt(getProducts.get(position).getPrice());
                    holder.productPrice.setText(String.valueOf(totalLaundry)+" "+c.getResources().getString(R.string.money));
                }
                if (productCountLaundry==0 && productCountCleaningLaundry==0){
                    holder.productCount.setText("0");
                    productCountLaundry=0;
                    holder.productPrice.setVisibility(View.INVISIBLE);
                }

            }
        });

        url= ApiClient.BASE_URL_PHOTO+getProducts.get(position).getImage();

        Log.d("urlImage",url);
        Log.d("porductNameurl",getProducts.get(position).getName());
        Picasso.get().load(url).into(holder.productImage);



        holder.addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total=totalCleaningLaundry+totalLaundry;
                quantity=Integer.parseInt(holder.productCount.getText().toString())+Integer.parseInt(holder.productCountCln.getText().toString());

                if(total!=0)
                {

                    setProducts.add(new Product(getProducts.get(position).getId(),getProducts.get(position).getName(),getProducts.get(position).getNameAr(),
                            getProducts.get(position).getPrice(),getProducts.get(position).getImage(),
                            String.valueOf(quantity),total,getProducts.get(position).getCat(),String.valueOf(productCountCleaningLaundry),String.valueOf(productCountLaundry)));

                    PreferenceUtils.setProductDetailsWomen(setProducts,c);

                    MainActivity.menu=MainActivity.bottomNavigationView.getMenu();
                    item=MainActivity.menu.findItem(R.id.basket);
                    MainActivity.badge=MainActivity.bottomNavigationView.getOrCreateBadge(item.getItemId());
                    MainActivity.mNotificationsCount=true;
                    MainActivity.badge.setVisible(mNotificationsCount);
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

    public class WomenViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        ImageView productDecrease;
        TextView productCount;
        ImageView productInecrease;
        ImageView addToBasket;
        ImageView productDecreaseCln;
        ImageView productInecreaseCln;
        TextView productCountCln;

        public WomenViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
            productDecrease=itemView.findViewById(R.id.product_count_decrease);
            productCount=itemView.findViewById(R.id.product_count);
            productInecrease=itemView.findViewById(R.id.product_count_increase);
            addToBasket=itemView.findViewById(R.id.add_to_basket_bt);
            productCountCln=itemView.findViewById(R.id.product_count_cleaning_luandry);
            productDecreaseCln=itemView.findViewById(R.id.product_count_decrease_cleaning_laundry);
            productInecreaseCln=itemView.findViewById(R.id.product_count_increase_cleaning_laundry);

        }
    }
}
