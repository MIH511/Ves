package com.delivery.ves.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.ves.Models.SignUp.User;
import com.delivery.ves.Models.UserProfile.UpdateUser;
import com.delivery.ves.Models.UserProfile.UpdateUserProfileResponse;
import com.delivery.ves.R;
import com.delivery.ves.utils.GetRealPath;
import com.delivery.ves.utils.LocaleHelper;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import local.org.apache.http.entity.mime.HttpMultipartMode;
import local.org.apache.http.entity.mime.MultipartEntity;
import local.org.apache.http.entity.mime.content.FileBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.delivery.ves.Activities.welcomeActivity.language;


public class UpdateProfileActivity extends AppCompatActivity {

    User userDetails;
    User newUserData;
    String data;
    EditText userName,Password,email,address,phone;
    TextView changePhoto,changeLanguage;
    Button saveDataBt;
    CircleImageView profileImageUpdate;
    Toolbar toolbar;

    Resources resources;

    private static final int PICK_IMAGE=1;
    String realPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        userName=findViewById(R.id.user_name_update_profile_EdTx);
        Password=findViewById(R.id.password_update_profile_EdTx);
        email=findViewById(R.id.email_update_profile_EdTx);
        address=findViewById(R.id.address_update_profile_EdTx);
        phone=findViewById(R.id.phone_update_profile_EdTx);
        changePhoto=findViewById(R.id.add_profile_pic);
        profileImageUpdate=findViewById(R.id.profile_image_update);
        saveDataBt=findViewById(R.id.save_data);
        toolbar=findViewById(R.id.toolbar_update_profile);
        changeLanguage=findViewById(R.id.change_language_update_profile_bt);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.EditProfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Gson gson=new Gson();
        Type type=new TypeToken<User>() {}.getType();
        data= PreferenceUtils.getUserData(this);
        userDetails=gson.fromJson(data,type);

        userName.setText(userDetails.getFullname());
        Password.setText(userDetails.getPassword());
        email.setText(userDetails.getEmail());
        address.setText(userDetails.getAddress());
        phone.setText(userDetails.getPhone());

        changePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"select picture"),PICK_IMAGE);
            }
        });

        saveDataBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName=userName.getText().toString();
                String password=Password.getText().toString();
                String userEmail=email.getText().toString();
                String userAddress=address.getText().toString();
                String userphone=phone.getText().toString();
                api(realPath,fullName,password,userEmail,userAddress,userphone);
            }
        });

        Paper.init(this);
        language=PreferenceUtils.getDefaultLanguage(this);

        if (language==null){
            Paper.book().write("language","en");
        }
//        updateView((String) Paper.book().read("language"));

//        LocaleHelper.setLocale(UpdateProfileActivity.this,language.toLowerCase());

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent refreash=new Intent(getIntent());
                if (language.equals("en"))
                {
//                    Paper.book().write("language","ar");
                    PreferenceUtils.setDefaultLanguage("ar",UpdateProfileActivity.this);
                    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
                    updateView(PreferenceUtils.getDefaultLanguage(UpdateProfileActivity.this));
                    LocaleHelper.setLocale(UpdateProfileActivity.this,"ar");
                }
                else {
                    PreferenceUtils.setDefaultLanguage("en",UpdateProfileActivity.this);
                    updateView(PreferenceUtils.getDefaultLanguage(UpdateProfileActivity.this));
                    LocaleHelper.setLocale(UpdateProfileActivity.this,"en");

                }

                startActivity(refreash);
            }
        });

    }

    private void updateView(String language) {

        Context context= LocaleHelper.setLocale(this,language);
        resources=context.getResources();
        resources.updateConfiguration(resources.getConfiguration(),resources.getDisplayMetrics());
        String la=resources.getString(R.string.Language);
        Log.d("asd",la);
        Log.d("language",language);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){

            Uri imageUri=data.getData();
            realPath= GetRealPath.getRealPath(this,imageUri);
//            File file=new File(imageUri.getPath());
//            editProfile(imageUri);

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);

                profileImageUpdate.setImageBitmap(bitmap);

//                editProfile(file,userDetails);
                //editProfile(imageUri,b);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void api(final String realPath, final String fullName, final String password, final String userEmail, final String userAddress, final String userphone) {

        final File image = new File(realPath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("image", image.getName(), requestBody);
        RequestBody name = RequestBody.create(
                MediaType.parse("text/plain"),
                image.getName());
        MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        mpEntity.addPart("image", new FileBody(image, "application/octet"));


        RequestBody builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("auth","update").
                        addFormDataPart("id",userDetails.getId()).
                        addFormDataPart("fullname",fullName)
                .addFormDataPart("phone",userphone)
                .addFormDataPart("email",userEmail)
                .addFormDataPart("gender",userDetails.getGender())
                .addFormDataPart("address",userAddress)
                .addFormDataPart("birthdate",userDetails.getBirthdate())
                .addFormDataPart("password",password)
                .addFormDataPart("image",image.getName(),RequestBody.create(MediaType.parse("application/octet-stream"),image))
                .build();



        Utils.getApi().UpdateUser(builder)
                .enqueue(new Callback<UpdateUserProfileResponse>() {
                    @Override
                    public void onResponse(Call<UpdateUserProfileResponse> call, retrofit2.Response<UpdateUserProfileResponse> response) {
                        if(response.body().getStatus()){
                            Toast.makeText(UpdateProfileActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            newUserData=response.body().getUser();
                            PreferenceUtils.saveUserData(newUserData,UpdateProfileActivity.this);
                        }

                    }

                    @Override
                    public void onFailure(Call<UpdateUserProfileResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}