package com.delivery.ves.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.delivery.ves.Adapters.RecyclerViewReceiptAdapter;
import com.delivery.ves.Fragments.ReceiptFragment;
import com.delivery.ves.Models.RateOrder.RateOrderBody;
import com.delivery.ves.Models.RateOrder.RateOrderResponse;
import com.delivery.ves.Models.ShowOrders.OrderDetails;
import com.delivery.ves.Models.ShowOrders.ShowLastOrderAndSpecificOrderResponse;
import com.delivery.ves.Models.ShowOrders.ShowOrderPost;
import com.delivery.ves.R;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class processActivity extends AppCompatActivity {

    Toolbar toolbar;
    RatingBar ratingBar;
    ImageView recivedOnOff,ironingOnOff,deliveringOnOff,deliverdOnOff;
    ImageView arrow1,arrow2,arrow3,arrow4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        toolbar=findViewById(R.id.toolbar_process);
        ratingBar=findViewById(R.id.rating);
        recivedOnOff=findViewById(R.id.recived_on_off);
        ironingOnOff=findViewById(R.id.ironing_on_off);
        deliveringOnOff=findViewById(R.id.delivering_on_off);
        deliverdOnOff=findViewById(R.id.deliverd_on_off);
        arrow1=findViewById(R.id.arrow_1);
        arrow2=findViewById(R.id.arrow_2);
        arrow3=findViewById(R.id.arrow_3);
        arrow4=findViewById(R.id.arrow_4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        if(ratingBar.getNumStars()!=0){
            Log.d("numStars", String.valueOf(ratingBar.getNumStars()));
//            Log.d("prodID",PreferenceUtils.getLastOrderId(processActivity.this));

        }
//
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.d("getRating", String.valueOf(v));
                RateProcess(v);
            }
        });

        getStatus();
    }

    private void getStatus() {
        ShowOrderPost orderPost=new ShowOrderPost("lastorder", PreferenceUtils.getId(this));

        Utils.getApi().LastOrder(orderPost.getAuth(),orderPost.getUserid())
                .enqueue(new Callback<ShowLastOrderAndSpecificOrderResponse>() {
                    @Override
                    public void onResponse(Call<ShowLastOrderAndSpecificOrderResponse> call, Response<ShowLastOrderAndSpecificOrderResponse> response) {
                        if(response.body().getStatus()){
                            String order=response.body().getOrder().getStatus();
                            OrderStatus(order);
                        }

                    }

                    @Override
                    public void onFailure(Call<ShowLastOrderAndSpecificOrderResponse> call, Throwable t) {

                    }
                });
    }

    private void OrderStatus(String order) {
        if(order.equalsIgnoreCase("0")){
            Picasso.get().load(R.drawable.recived_on).into(recivedOnOff);
        }
        else if(order.equalsIgnoreCase("1")){
            Picasso.get().load(R.drawable.recived_on).into(recivedOnOff);
            Picasso.get().load(R.drawable.ironing_on).into(ironingOnOff);
        }
        else if(order.equalsIgnoreCase("2")){
            Picasso.get().load(R.drawable.recived_on).into(recivedOnOff);
            Picasso.get().load(R.drawable.ironing_on).into(ironingOnOff);
            Picasso.get().load(R.drawable.delivering_on).into(deliveringOnOff);
        }
        else if(order.equalsIgnoreCase("3")){
            Picasso.get().load(R.drawable.recived_on).into(recivedOnOff);
            Picasso.get().load(R.drawable.ironing_on).into(ironingOnOff);
            Picasso.get().load(R.drawable.delivering_on).into(deliveringOnOff);
            Picasso.get().load(R.drawable.deliverd_on).into(deliverdOnOff);
        }
        else {
            Picasso.get().load(R.drawable.recived_off).into(recivedOnOff);
            Picasso.get().load(R.drawable.ironing_off).into(ironingOnOff);
            Picasso.get().load(R.drawable.delivering_off).into(deliveringOnOff);
            Picasso.get().load(R.drawable.deliverd_off).into(deliverdOnOff);
        }
    }

    private void RateProcess(float numStars) {

        RateOrderBody body=new RateOrderBody("rate", PreferenceUtils.getLastOrderId(processActivity.this),String.valueOf(numStars));

        Utils.getApi().rateOrder(body.getAuth(),body.getOrderId(),body.getRate()).enqueue(new Callback<RateOrderResponse>() {
            @Override
            public void onResponse(Call<RateOrderResponse> call, Response<RateOrderResponse> response) {
                if (response.body().getStatus()) {
                    Toast.makeText(processActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RateOrderResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(welcomeActivity.language.equalsIgnoreCase("ar"))
        {
            arrow1.setRotation(90);
            arrow2.setRotation(270);
            arrow3.setRotation(90);
            arrow4.setRotation(270);
        }

    }
}