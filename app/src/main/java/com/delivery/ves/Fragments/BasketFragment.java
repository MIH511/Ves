package com.delivery.ves.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.ves.Adapters.RecyclerViewBasketAdapter;
import com.delivery.ves.Models.NewOrder.NewOrder;
import com.delivery.ves.Models.NewOrder.NewOrderResponse;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.Models.promoCode.PromoCode;
import com.delivery.ves.Models.promoCode.PromoCodeError;
import com.delivery.ves.Models.promoCode.PromoCodeResponse;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.R;
import com.delivery.ves.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFragment extends Fragment {

    RecyclerView basketRecyclerView;
    RecyclerViewBasketAdapter basketAdapter;
    Button orderNowBt,checkPromo;
    EditText promoCodeEtx;
    TextView totalPrice;
    ProgressBar progressBar;
    Spinner spinner;

    public static ArrayList<Product> products=new ArrayList<>();
    static ArrayList<Product> productsMen=new ArrayList<>();
    static ArrayList<Product> productsWomen=new ArrayList<>();
    static ArrayList<Product> productsKids=new ArrayList<>();


    ArrayList<String> productID=new ArrayList<>();
    ArrayList<String> quantity=new ArrayList<>();
    ArrayList<String> quantity2=new ArrayList<>();

    String promocode;
    String city;


    public static FragmentTransaction fragmentTransaction;

    int total=0;
    public static String totalReceipt;
    public BasketFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        View view=inflater.inflate(R.layout.fragment_basket, container, false);
        totalPrice=view.findViewById(R.id.total_price);
        basketRecyclerView=view.findViewById(R.id.basket_recyclerView);
        orderNowBt=view.findViewById(R.id.order_now_bt);
        checkPromo=view.findViewById(R.id.check_promo_code);
        promoCodeEtx=view.findViewById(R.id.promocode_edit_tx);
        progressBar=view.findViewById(R.id.progress_bar_basket);
        spinner=view.findViewById(R.id.spinner_address_items);
        progressBar.setVisibility(View.INVISIBLE);
        basketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Product>>() {}.getType();
        String jsonMen= PreferenceUtils.getProductDetailsMen(getContext());
        String jsonWomen=PreferenceUtils.getProductDetailsWomen(getContext());
        String jsonKids=PreferenceUtils.getProductDetailsKids(getContext());

        products.clear();




        if(products.isEmpty()){
            products.clear();
        getData(jsonMen,jsonWomen,jsonKids,type,gson);

        }

        basketAdapter=new RecyclerViewBasketAdapter(getContext(), (ArrayList<Product>) products);
        basketRecyclerView.setAdapter(basketAdapter);
        basketAdapter.notifyDataSetChanged();

        if(products!=null)
        {

            for(int i=0;i<products.size();i++)
            {
                total=total+products.get(i).getTotal();
                productID.add(products.get(i).getId());
                quantity.add(products.get(i).getCountLaundry());
                quantity2.add(products.get(i).getCountCleaningLaundry());

            }
            totalPrice.setText(String.valueOf(total+" EGP"));
        }

        orderNowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city=spinner.getSelectedItem().toString();
                if(city.equalsIgnoreCase("El-Shikh Zayed") ||city.equalsIgnoreCase("الشيخ زايد"))
                {
                    addNewOrder(productID,quantity,promocode);
                }
                else if (city.equalsIgnoreCase("--Choose your city--")||city.equalsIgnoreCase("اختر المدينة")){

                    Toast.makeText(getContext(), "select your city,please", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(getContext(), "we are not support another area", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checkPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (promoCodeEtx.getText().toString().isEmpty()){
                    promoCodeEtx.setError("enter your promo code");
                    promoCodeEtx.requestFocus();
                    return;
                }
                else {
                    getPromoCode(promoCodeEtx.getText().toString(),String.valueOf(total));
                }
            }
        });

        totalReceipt=totalPrice.getText().toString();
        return view;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void getData(String jsonMen, String jsonWomen, String jsonKids, Type type, Gson gson) {

        if(jsonMen!=null )
        {
            productsMen=gson.fromJson(jsonMen,type);
            for (int i=0; i<productsMen.size(); i++){

                products.add(productsMen.get(i));

            }

        }
        if(jsonWomen!=null)
        {
            productsWomen=gson.fromJson(jsonWomen,type);
            for (int i=0; i<productsWomen.size(); i++){

                products.add(productsWomen.get(i));
            }
        }
        if(jsonKids!=null)
        {
            productsKids=gson.fromJson(jsonWomen,type);
            for (int i=0; i<productsWomen.size(); i++){

                products.add(productsKids.get(i));
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void addNewOrder(ArrayList<String> productID, ArrayList<String> quantity,String promocode) {
        progressBar.setVisibility(View.VISIBLE);
        String userID=PreferenceUtils.getId(getContext());
        if (promocode==null){
            promocode=null;
        }
        NewOrder newOrder=new NewOrder("neworder",userID,productID,quantity,promocode,quantity2);
        Utils.getApi().setNewOrder(newOrder.getAuth(),newOrder.getUserId(),newOrder.getProductsId(),
                newOrder.getQuantity(),newOrder.getPromocode(),newOrder.getQuantity2())
                .enqueue(new Callback<NewOrderResponse>() {
                    @Override
                    public void onResponse(Call<NewOrderResponse> call, Response<NewOrderResponse> response) {
                        if(response.body().getStatus())
                        {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            PreferenceUtils.deleteProductMen(getContext());
                            PreferenceUtils.deleteProductWomen(getContext());
                            PreferenceUtils.deleteProductKids(getContext());
                            FragmentTransaction t=getActivity().getSupportFragmentManager().beginTransaction();
                            Fragment fragment=new ReceiptFragment(products);
                            t.replace(R.id.frame_container,fragment);
                            t.commit();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                        else {
                            Toast.makeText(getContext(), "there is no items", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewOrderResponse> call, Throwable t) {

                    }
                });
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void getPromoCode(String promo,String total) {

        final PromoCode promoCode=new PromoCode("promocode",promo,total);
        progressBar.setVisibility(View.VISIBLE);
        Utils.getApi().getPromoCodeSuccess(promoCode.getAuth(),promoCode.getPromo(),promoCode.getTotal())
                .enqueue(new Callback<PromoCodeResponse>() {
                    @Override
                    public void onResponse(Call<PromoCodeResponse> call, Response<PromoCodeResponse> response) {

                        if (response.body().getStatus())
                        {
                            progressBar.setVisibility(View.INVISIBLE);

                            totalPrice.setText(String.valueOf(response.body().getTotal())+" EGP");
                            promocode=promoCodeEtx.getText().toString();

                        }
                    }

                    @Override
                    public void onFailure(Call<PromoCodeResponse> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

        Utils.getApi().getPromoCode(promoCode.getAuth(),promoCode.getPromo(),promoCode.getTotal())
                .enqueue(new Callback<PromoCodeError>() {
                    @Override
                    public void onResponse(Call<PromoCodeError> call, Response<PromoCodeError> response) {
                        if(!response.body().getStatus())
                        {
                            promoCodeEtx.setError(response.body().getMsg().toString());
                            promoCodeEtx.requestFocus();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<PromoCodeError> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void removeItem(int position, String id){



    }

    public static void removeWomenItem(int position, String id, Context c) {
        products.remove(position);

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Product>>() {}.getType();
        String jsonWomen=PreferenceUtils.getProductDetailsWomen(c);

        productsWomen=gson.fromJson(jsonWomen,type);
        for (int i=0; i<productsWomen.size(); i++){

            if(productsWomen.get(i).getId().equalsIgnoreCase(id))
            {
                productsWomen.remove(i);
            }
        }
        PreferenceUtils.setProductDetailsWomen(productsWomen,c);
    }

    public static void removeMenItem(int position, String id, Context c) {
        products.remove(position);

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Product>>() {}.getType();
        String jsonMen=PreferenceUtils.getProductDetailsMen(c);

        productsMen=gson.fromJson(jsonMen,type);
        for (int i=0; i<productsMen.size(); i++){

            if(productsMen.get(i).getId().equalsIgnoreCase(id))
            {
                productsMen.remove(i);
            }
        }
        PreferenceUtils.setProductDetailsMen(productsMen,c);
    }

    public static void removeKidsItem(int position, String id, Context c) {
        products.remove(position);

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Product>>() {}.getType();
        String jsonKids=PreferenceUtils.getProductDetailsKids(c);

        productsKids=gson.fromJson(jsonKids,type);
        for (int i=0; i<productsMen.size(); i++){

            if(productsKids.get(i).getId().equalsIgnoreCase(id))
            {
                productsKids.remove(i);
            }
        }
        PreferenceUtils.setProductDetailsKids(productsKids,c);
    }


}
