package com.example.whcar.Home.Adapter;
import android.content.Context;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mList;
    private ArrayList<String> mListString;
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment>  mList, ArrayList<String> mListString) {
        super(fm);
        this.context = context;
        this.mList = mList;
        this.mListString = mListString;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListString.get(position);
    }
}
