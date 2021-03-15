package com.delivery.ves.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.delivery.ves.Fragments.BasketFragment;
import com.delivery.ves.Fragments.HomeFragment;
import com.delivery.ves.Fragments.MyOrdersFragment;
import com.delivery.ves.R;
import com.delivery.ves.utils.LocaleHelper;
import com.delivery.ves.utils.PreferenceUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    public static BottomNavigationView bottomNavigationView;
    String id;
    Resources resources;
    public static boolean mNotificationsCount;
    public static Menu menu;
    public static MenuItem item;
    public static BadgeDrawable badge;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);


//        Paper.init(this);
//        language=Paper.book().read("language");
//
//        if (language==null){
//            Paper.book().write("language","en");
//        }
//        updateView((String) Paper.book().read("language"));
//        LocaleHelper.setLocale(MainActivity.this,language.toLowerCase());
             menu = bottomNavigationView.getMenu();
            MenuItem item = menu.findItem(R.id.basket);

        Intent intent = getIntent();
        id = intent.getStringExtra("userId");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new HomeFragment()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(mNotificationsCount==true)
                {
                    badge.setVisible(false);
                }
                switch (item.getItemId()) {
                    case R.id.home:

                        fragment = new HomeFragment();

                        break;
                    case R.id.basket:

                        fragment = new BasketFragment();

                        break;
                    case R.id.order:

                        fragment = new MyOrdersFragment();
                        break;
                    default:
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, fragment).commit();
                }
                return true;
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.about:
                Intent about = new Intent(MainActivity.this, aboutActivity.class);
                startActivity(about);
                break;
            case R.id.logout:
                Toast.makeText(MainActivity.this, "Logout Selected", Toast.LENGTH_SHORT).show();
                PreferenceUtils.deleteId(MainActivity.this);
                Intent intent = new Intent(this, welcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.process:
                Intent process = new Intent(MainActivity.this, processActivity.class);
                startActivity(process);
                break;

            default:
                break;
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomNavigationView.getMenu().clear();
        navigationView.getMenu().clear();
        bottomNavigationView.inflateMenu(R.menu.bottom_nav_bar);
        navigationView.inflateMenu(R.menu.navigation_drawer);
    }
}

