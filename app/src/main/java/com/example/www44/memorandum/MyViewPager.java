package com.example.www44.memorandum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 页面适配器
 * 适配viewpage的
 *
 */
public class MyViewPager extends FragmentPagerAdapter {
    private List<Fragment> listfragment;

    private FragmentManager fragmetnmanager;  //创建FragmentManager


//<br>　　　　　　//定义构造带两个参数

    public MyViewPager(FragmentManager fm, List<Fragment> list) {

        super(fm);

        this.fragmetnmanager = fm;

        this.listfragment = list;

    }


    @Override
    public Fragment getItem(int arg0) {

        // TODO Auto-generated method stub

        return listfragment.get(arg0); //返回第几个fragment

    }


    @Override

    public int getCount() {

        // TODO Auto-generated method stub

        return listfragment.size(); //总共有多少个fragment

    }


}
