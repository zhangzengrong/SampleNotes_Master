package com.example.www44.memorandum;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.www44.memorandum.Fragment1;
import com.example.www44.memorandum.Fragment2;
import com.example.www44.memorandum.Fragment3;
import com.example.www44.memorandum.MyPageChangeListener;
import com.example.www44.memorandum.MyViewPager;
import com.example.www44.memorandum.R;
import com.example.www44.memorandum.Tab;

import java.util.ArrayList;
import java.util.List;


/**
 * 主页
 * 四个底部
 * 使用tabhost
 * 这里
 * Activity  +Fragment
 */
public class MainActivity extends FragmentActivity{
    private FragmentTabHost tabHost;
    private View view = null;
    private LayoutInflater Inflater;
    private List<Tab> list = new ArrayList<>(3);
    private ViewPager viewPager;
    private List<Fragment> FragmentList = new ArrayList<>(3);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Inflater = LayoutInflater.from(this);
        //初始化tab的方法
        initTab();
    }
    //todo 初始化资源 Tabhost
    private void initTab() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //TODO 绑定 Viewpager  ji加分项
        tabHost.setup(this,getSupportFragmentManager(),R.id.view_pager);
        //TODO 添加资源 初始化三个页面 加进Tabhost
        Tab tabmain = new Tab(R.string.home, R.drawable.ic_write_normal, Fragment1.class);
        Tab tabcontact = new Tab(R.string.daojishi, R.drawable.ic_check_normal, Fragment2.class);
        Tab tabmine = new Tab(R.string.tubiao, R.drawable.ic_setting_normal, Fragment3.class);
        list.add(tabmain);
        list.add(tabcontact);
        list.add(tabmine);
   //加入到Tabhost
        for (Tab tab : list) {
            TabHost.TabSpec spec= tabHost.newTabSpec(getString(tab.getTitle()))//
                    .setIndicator(getItemView(tab));
            tabHost.addTab(spec,tab.getFragment(),null);
        }
        //清除分界线
        tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //初始化viewpage 加分的
        initFragment();
    }

    /**
     * 初始化ViewPager 加分
     */
    private void initFragment() {
        //Todo            添加四个fragment
        FragmentList.add(new Fragment1());
        FragmentList.add(new Fragment2());
        FragmentList.add(new Fragment3());
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //设置适配器
        viewPager.setAdapter(new MyViewPager(getSupportFragmentManager(),FragmentList));
        //设置界面滑动监听事件
        viewPager.addOnPageChangeListener(new MyPageChangeListener(tabHost));
        //设置viewpage缓存
        viewPager.setOffscreenPageLimit(3);
        //设置TabHost的点击切换监听事件
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position  =tabHost.getCurrentTab();
                viewPager.setCurrentItem(position);
            }
        });
    }
    //TODO 给indicator配置View
    private View getItemView(Tab tab) {
        view = Inflater.inflate(R.layout.itemview, null);
        ImageView img = (ImageView) view.findViewById(R.id.iv);
        TextView textview = (TextView) view.findViewById(R.id.tv);
        img.setImageResource(tab.getImg());
        textview.setText(tab.getTitle());
        return view;
    }
}