package com.delivery.ves.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.delivery.ves.Activities.MainActivity;
import com.delivery.ves.Activities.welcomeActivity;
import com.delivery.ves.Adapters.PageAdapter;
import com.delivery.ves.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabLayout tabLayout;
    TabItem men;
    TabItem women;
    TabItem kids;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=view.findViewById(R.id.view_pager);
        tabLayout=view.findViewById(R.id.tabLayout);
        men=view.findViewById(R.id.Men_tab_item);
        women=view.findViewById(R.id.Women_tab_item);
        kids=view.findViewById(R.id.Kids_tab_item);
        pageAdapter=new PageAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Paper.init(getContext());
        if (welcomeActivity.language.equals("ar"))
        {
//            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//            tabLayout.setTextDirection(View.TEXT_DIRECTION_LTR);
            viewPager.setRotationY(180);
        }
        return view;
    }
}
