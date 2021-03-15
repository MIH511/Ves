package com.delivery.ves.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.delivery.ves.Models.Login.LoginSuccessResponse;
import com.delivery.ves.Models.SignUp.ApiSignUpResponse;
import com.delivery.ves.Models.SignUp.SignUpData;
import com.delivery.ves.Models.SignUp.SignUpSuccessfully;
import com.delivery.ves.Models.SignUp.User;
import com.delivery.ves.R;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.utils.Utils;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signUpActivity extends AppCompatActivity {

    Spinner spinner;
    Button signup;
    EditText date;
    String current = "";
    String ddmmyyyy = "DDMMYYYY";
    Calendar cal = Calendar.getInstance();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String dateReverse;

    User messageLogin;

    EditText fullnameEtx, phoneNumberEtx, emailEtx,addressEtx;
    EditText passwordEtx,confirmPasswordEtx;
    ProgressBar progressBar;
    String fullname, phoneNumber, email, address, password, confirmPassword,dateSt,gender;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        date=findViewById(R.id.birth_date_sign_up);
        spinner=findViewById(R.id.spinner_gender_sign_up);
        signup=findViewById(R.id.sign_up_bt);
        fullnameEtx=findViewById(R.id.full_name_sign_up);
        phoneNumberEtx=findViewById(R.id.phone_number_sign_up);
        emailEtx=findViewById(R.id.email_sign_up);
        addressEtx=findViewById(R.id.address_sign_up);
        passwordEtx=findViewById(R.id.password_sign_up);
        confirmPasswordEtx=findViewById(R.id.confirm_pass_sign_up);
        progressBar=findViewById(R.id.progress_bar_sign_up);
        progressBar.setVisibility(View.INVISIBLE);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname=fullnameEtx.getText().toString().trim();
                phoneNumber=phoneNumberEtx.getText().toString().trim();
                email=emailEtx.getText().toString().trim();
                address=addressEtx.getText().toString().trim();
                password=passwordEtx.getText().toString().trim();
                confirmPassword=confirmPasswordEtx.getText().toString().trim();
                dateSt=date.getText().toString().trim();
                String[] separated=dateSt.split("/");
                if(dateSt.isEmpty()){
                    date.setError("enter your birth date");
                    date.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                else {
                String day=separated[0];
                String month=separated[1];
                String year=separated[2];
                dateReverse=year+"-"+month+"-"+day;
                }
                gender=spinner.getSelectedItem().toString();
                signUpProccess(fullname,password,phoneNumber,email,address,confirmPassword,dateReverse,gender);

            }
        });


        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");
                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2010)?2010:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });


    }

    private void signUpProccess(String fullname, String password, String phoneNumber, String email, String address, String confirmPassword,String dateReverse,String gender) {

        if(fullname.isEmpty())
        {
            fullnameEtx.setError("enter your name");
            fullnameEtx.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else if (phoneNumber.isEmpty())
        {
            phoneNumberEtx.setError("enter your phone number");
            phoneNumberEtx.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else if (email.isEmpty())
        {
            emailEtx.setError("enter your email");
            emailEtx.requestFocus();
            return;
        }
        if (!email.matches(emailPattern))
        {
            emailEtx.setError("Invalid email address");
            emailEtx.requestFocus();
            return;
        }
        else if (password.isEmpty())
        {
            passwordEtx.setError("enter a password");
            passwordEtx.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else if (confirmPassword.isEmpty()){
            confirmPasswordEtx.setError("confirm the password");
            confirmPasswordEtx.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else if (dateSt.isEmpty())
        {
            date.setError("enter your birth date");
            date.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else if (address.isEmpty())
        {
            addressEtx.setError("enter your address");
            addressEtx.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        else if(!password.equals(confirmPassword)){
            confirmPasswordEtx.setError("password not the same");
            confirmPasswordEtx.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        else{

            SignUpData data=new SignUpData("signup",fullname,phoneNumber,email,gender,address,dateReverse,password);
            SignUpprocess(data);
            return;
        }
//        SignUpData data=new SignUpData("signup",fullname,phoneNumber,email,gender,address,dateReverse,password);
//            SignUpprocess(data);
    }

    private void SignUpprocess(SignUpData data) {

        Utils.getApi().setSignUpDataSuccess("signup",data.getFullName(),data.getPhoneNumber(),data.getEmail(),
                data.getGender(),data.getAddress(),data.getBirthDate(),data.getPassword()).enqueue(new Callback<SignUpSuccessfully>() {
            @Override
            public void onResponse(Call<SignUpSuccessfully> call, Response<SignUpSuccessfully> response) {
                if(response.body().getStatus()) {
                    messageLogin=response.body().getUserInfo();
                    Intent intent=new Intent(signUpActivity.this,MainActivity.class);
                    PreferenceUtils.SaveId(messageLogin.getId(),signUpActivity.this);
                    PreferenceUtils.saveUserData(messageLogin,signUpActivity.this);
                    Toast.makeText(signUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                }
               else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(signUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SignUpSuccessfully> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }

        });
    }

}
