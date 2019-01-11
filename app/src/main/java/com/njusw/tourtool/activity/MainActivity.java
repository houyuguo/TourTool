package com.njusw.tourtool.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.Window;

import com.njusw.tourtool.R;
import com.njusw.tourtool.adapter.ViewPagerAdapter;

import com.njusw.tourtool.ui.DateFragment;

import com.njusw.tourtool.ui.InterfaceTestFragment;
import com.njusw.tourtool.ui.MainFragment;
import com.njusw.tourtool.ui.MeFragment;
import com.njusw.tourtool.ui.MessageFragment;
import com.njusw.tourtool.ui.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomNavigationView)
    public BottomNavigationView btmNavi;
    @BindView(R.id.main_vp)
    public ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private MenuItem menuItem;

    @Override
    protected void initUI() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView()工作在baseActivity中完成，必须在ButterKnife.bind()之前完成

        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //btmNavi = findViewById(R.id.bottomNavigationView);
        //viewPager = findViewById(R.id.main_vp);
        btmNavi.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        List<Fragment> list = new ArrayList<>();
        list.add(new MainFragment());
        list.add(new DateFragment());
        list.add(new MessageFragment());
        list.add(new InterfaceTestFragment());
        viewPagerAdapter.setList(list);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menu) {
            menuItem = menu;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_exploration:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_message:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_person:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private  ViewPager.OnPageChangeListener mOnPageChangeListener =
            new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    if (menuItem != null) {
                        menuItem.setChecked(false);
                    } else {
                        btmNavi.getMenu().getItem(0).setChecked(false);
                    }
                    menuItem = btmNavi.getMenu().getItem(i);
                    menuItem.setChecked(true);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            };

}
