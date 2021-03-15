package com.delivery.ves.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.delivery.ves.utils.LocaleHelper;
import com.delivery.ves.utils.PreferenceUtils;
import com.delivery.ves.R;

import io.paperdb.Paper;

public class welcomeActivity extends AppCompatActivity {

    Button sign_up_bt, sign_in_bt,changeLanguage;

    public  static String language;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sign_up_bt=findViewById(R.id.sign_up_welcome_bt);
        sign_in_bt=findViewById(R.id.sign_in_welcome_bt);
        changeLanguage=findViewById(R.id.change_language_welcome_bt);
        sign_up_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(welcomeActivity.this, signUpActivity.class);
                startActivity(intent);
            }
        });

        sign_in_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(welcomeActivity.this, signInActivity.class);
                startActivity(intent);
            }
        });

        Paper.init(this);
//        language=Paper.book().read("language");
        language= PreferenceUtils.getDefaultLanguage(this);
//
        if (language==null){
            PreferenceUtils.setDefaultLanguage("en",this);
            language=PreferenceUtils.getDefaultLanguage(this);
        }
////        updateView((String) Paper.book().read("language"));
//        updateView(PreferenceUtils.getDefaultLanguage(this));
////
        LocaleHelper.setLocale(welcomeActivity.this,language.toLowerCase());
        if(language.equalsIgnoreCase("ar"))
        {
            Context context= LocaleHelper.setLocale(this,language);
            resources=context.getResources();
            resources.updateConfiguration(resources.getConfiguration(),resources.getDisplayMetrics());
            sign_in_bt.setText(resources.getString(R.string.sign_in));
            sign_up_bt.setText(resources.getString(R.string.sign_up));
            changeLanguage.setText(resources.getString(R.string.Language));
        }

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent refreash=new Intent(welcomeActivity.this,welcomeActivity.class);
                if (language.equals("en"))
                {
//                    Paper.book().write("language","ar");
                    PreferenceUtils.setDefaultLanguage("ar",welcomeActivity.this);
                    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
//                    updateView(Paper.book().read("language").toString());
                    updateView(PreferenceUtils.getDefaultLanguage(welcomeActivity.this));
                    LocaleHelper.setLocale(welcomeActivity.this,"ar");
                }
                else {
//                    Paper.book().write("language","en");
                    PreferenceUtils.setDefaultLanguage("en",welcomeActivity.this);
                    updateView(PreferenceUtils.getDefaultLanguage(welcomeActivity.this));
                    LocaleHelper.setLocale(welcomeActivity.this,"en");

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

        Log.d("language",language);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(PreferenceUtils.getId(welcomeActivity.this)!=null)
        {
            Intent intent=new Intent(welcomeActivity.this,MainActivity.class);
            intent.putExtra("userId",PreferenceUtils.getId(welcomeActivity.this));
            startActivity(intent);


        }
    }
}
