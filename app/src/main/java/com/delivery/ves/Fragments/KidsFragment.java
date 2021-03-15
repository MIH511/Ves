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

import com.delivery.ves.Activities.MainActivity;
import com.delivery.ves.Activities.welcomeActivity;
import com.delivery.ves.Adapters.RecyclerViewKidsAdapter;
import com.delivery.ves.Adapters.RecyclerViewProductAdapter;
import com.delivery.ves.Models.product.GetProduct;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.R;
import com.delivery.ves.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KidsFragment extends Fragment {


    RecyclerView KidsRecyclerView;
    RecyclerViewKidsAdapter kidsAdapter;
    ProgressBar progressBar;
    ArrayList<Product> products=new ArrayList<>();
    public KidsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_kids, container, false);
        KidsRecyclerView=view.findViewById(R.id.recyclerView_kids);
        progressBar=view.findViewById(R.id.progress_bar_kids);
        KidsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        getProductData();
        if (welcomeActivity.language.equals("ar"))
        {
//            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//            tabLayout.setTextDirection(View.TEXT_DIRECTION_LTR);
            KidsRecyclerView.setRotationY(180);
        }
        return view;
    }

    private void getProductData() {

        progressBar.setVisibility(View.VISIBLE);
        Utils.getApi().getproduct("getproduct","kids").enqueue(new Callback<GetProduct>() {
            @Override
            public void onResponse(Call<GetProduct> call, Response<GetProduct> response) {

                products=response.body().getProducts();
                kidsAdapter=new RecyclerViewKidsAdapter(getContext(),products);
                KidsRecyclerView.setAdapter(kidsAdapter);
                kidsAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<GetProduct> call, Throwable t) {

                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }
}
