package com.delivery.ves.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;


import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.delivery.ves.Apis.ApiClient;
import com.delivery.ves.Models.SignUp.User;
import com.delivery.ves.Models.UserProfile.UpdateUser;
import com.delivery.ves.Models.UserProfile.UpdateUserProfileResponse;
import com.delivery.ves.R;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;


import java.io.File;

import java.io.IOException;
import java.lang.reflect.Type;


import de.hdodenhof.circleimageview.CircleImageView;
import local.org.apache.http.entity.mime.HttpMultipartMode;
import local.org.apache.http.entity.mime.MultipartEntity;
import local.org.apache.http.entity.mime.content.FileBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView profileImage;
    Toolbar toolbar;
    Button editBt;
    TextView userName;
    TextView location;
    TextView name;
    TextView email;
    TextView phoneNumber;
    ProgressBar progressBar;

    User userDetails;
    String data;

    String part_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar=findViewById(R.id.toolbar_profile);
        profileImage=findViewById(R.id.profile_image);
        editBt=findViewById(R.id.edit_bt);
        userName=findViewById(R.id.person_name);
        location=findViewById(R.id.location);
        name=findViewById(R.id.user_name_profile);
        phoneNumber=findViewById(R.id.user_phone_profile);
        email=findViewById(R.id.user_email_profile);
        progressBar=findViewById(R.id.progress_bar_profile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        Gson gson=new Gson();
        Type type=new TypeToken<User>() {}.getType();
        data= PreferenceUtils.getUserData(this);
        userDetails=gson.fromJson(data,type);

        if(userDetails.getImage()!=null){
//            progressBar.setVisibility(View.VISIBLE);

            Picasso.get().load(ApiClient.BASE_URL_PHOTO_PROFILE+userDetails.getImage()).into(profileImage);

//            progressBar.setVisibility(View.INVISIBLE);

        }
        if(userDetails!=null){
            userName.setText(userDetails.getFullname());
            location.setText(userDetails.getAddress());
            name.setText(userDetails.getFullname());
            phoneNumber.setText(userDetails.getPhone());
            email.setText(userDetails.getEmail());
        }


        editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,UpdateProfileActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////Back Button///////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        supportFinishAfterTransition();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
}
