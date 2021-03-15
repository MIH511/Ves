package com.delivery.ves.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.delivery.ves.Models.Login.LoginResponse;
import com.delivery.ves.Models.Login.LoginSuccessResponse;
import com.delivery.ves.Models.Login.MessageLogin;
import com.delivery.ves.Models.Login.SignInData;
import com.delivery.ves.Models.SignUp.User;
import com.delivery.ves.Models.product.Product;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.R;
import com.delivery.ves.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signInActivity extends AppCompatActivity {

    Button signInBt;
    EditText emailSignIn,passwordSignIn;
    ProgressBar progressBar;
    User messageLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInBt=findViewById(R.id.sign_in_bt);
        emailSignIn=findViewById(R.id.email_sign_in);
        passwordSignIn=findViewById(R.id.password_sign_in);
        progressBar=findViewById(R.id.progress_bar_sign_in);
        progressBar.setVisibility(View.INVISIBLE);
        signInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                checkSignIn(emailSignIn.getText().toString(),passwordSignIn.getText().toString());
            }
        });

    }

    private void checkSignIn(String emailOrPhone, String password) {
        if(emailOrPhone.isEmpty())
        {
            emailSignIn.setError("enter an email or phone number");
            emailSignIn.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else if (password.isEmpty())
        {
            passwordSignIn.setError("enter your password");
            passwordSignIn.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else {
//            Intent intent=new Intent(signInActivity.this,MainActivity.class);
//            startActivity(intent);
            SignInData signInData=new SignInData("login",emailOrPhone,password);
            LoginProcess(signInData);
        }
    }

    private void LoginProcess(final SignInData signInData) {
        Utils.getApi().setSignInSuccess(signInData.getAuth(),signInData.getPhoneNumberOrEmail(),signInData.getPassword())
                .enqueue(new Callback<LoginSuccessResponse>() {
                    @Override
                    public void onResponse(Call<LoginSuccessResponse> call, Response<LoginSuccessResponse> response) {
                        if(response.body().getStatus()){
                            if(response.body().getMsg()!=null)
                            {
                            messageLogin=response.body().getMsg();
                            Intent intent=new Intent(signInActivity.this,MainActivity.class);
                            intent.putExtra("userId",messageLogin.getId());
                            PreferenceUtils.SaveId(messageLogin.getId(),signInActivity.this);
                            PreferenceUtils.saveUserData(messageLogin,signInActivity.this);
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginSuccessResponse> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

        Utils.getApi().setSignInData(signInData.getAuth(),signInData.getPhoneNumberOrEmail(),signInData.getPassword())
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(!response.body().getStatue()){
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(signInActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

    }


}
