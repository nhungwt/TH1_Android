package com.example.viewpager_tuto;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.viewpager_tuto.model.FragmentFood;
import com.example.viewpager_tuto.model.FragmentMovie;
import com.example.viewpager_tuto.model.FragmentTravel;

public class FragmentCustomAdapter extends FragmentPagerAdapter {
    private int pageNumber;


    public FragmentCustomAdapter(@NonNull FragmentManager fm, int pageNumber) {
        super(fm, pageNumber);
        this.pageNumber = pageNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
// trả về fragment tùy vào position
        switch (position){
            case 0:
                FragmentFood food = new FragmentFood();
                return food;
            case 1:
                FragmentMovie movie = new FragmentMovie();
                return movie;
            case 2:
                FragmentTravel travel = new FragmentTravel();
                return travel;
        }
        return new FragmentFood();
    }

    @Override
    public int getCount() {
        return this.pageNumber; // trả về số lượng fragment
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0: return "FOOD";
            case 1: return "MOVIE";
            case 2: return "TRAVEL";
        }
        return null;
    }
}
