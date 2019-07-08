package com.example.www44.memorandum;

import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;

/**
 * 这是监听页面滑动变换的接口
 */
public class MyPageChangeListener implements ViewPager.OnPageChangeListener {
    private FragmentTabHost tabHost;

    public MyPageChangeListener(FragmentTabHost tabHost) {
        this.tabHost = tabHost;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //界面被选择时调用
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}