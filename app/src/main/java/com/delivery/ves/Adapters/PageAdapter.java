package com.delivery.ves.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.delivery.ves.Fragments.KidsFragment;
import com.delivery.ves.Fragments.MenFragment;
import com.delivery.ves.Fragments.WomenFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;
    public PageAdapter(@NonNull FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.numberOfTabs=NumberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new MenFragment();
            case 1:
                return new WomenFragment();
            case 2:
                return new KidsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
