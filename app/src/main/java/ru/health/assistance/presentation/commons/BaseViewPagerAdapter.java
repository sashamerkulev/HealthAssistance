package ru.health.assistance.presentation.commons;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sasha_merkulev on 11.03.2018.
 */

public abstract class BaseViewPagerAdapter extends PagerAdapter {

    private final List<View> pages;

    public BaseViewPagerAdapter(List<View> pages){
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = pages.get(position);
        container.addView(v, 0);
        return v;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void destroyItem(@NonNull View collection, int position, @NonNull Object view){
        ((ViewPager) collection).removeView((View) view);
    }}
