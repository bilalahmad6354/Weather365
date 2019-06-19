package com.example.weather365;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DailyWeatherFragmentAdapter extends FragmentStatePagerAdapter {


    //Adapter related outlets
    String[] citiesList = {"Lahore", "Islamabad", "Karachi", "Multan"};
    String countryCode = "pk";

    public DailyWeatherFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        DailyWeatherFragment fragment = new DailyWeatherFragment();

        Bundle bundle = new Bundle();
        bundle.putString("cityName", citiesList[position]);
        bundle.putString("countryCode", countryCode);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return citiesList.length;
    }

}
