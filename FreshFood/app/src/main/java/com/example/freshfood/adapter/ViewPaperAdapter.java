package com.example.freshfood.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.freshfood.bottomnavigation.FragmentBill;
import com.example.freshfood.bottomnavigation.FragmentHome;
import com.example.freshfood.bottomnavigation.FragmentProfile;

import org.jetbrains.annotations.NotNull;

public class ViewPaperAdapter extends FragmentStatePagerAdapter {

    public ViewPaperAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentBill();
            case 2:
                return new FragmentProfile();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
