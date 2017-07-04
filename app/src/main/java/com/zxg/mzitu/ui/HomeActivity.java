package com.zxg.mzitu.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.zxg.mzitu.R;
import com.zxg.suiteset.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private ViewPagerAdapter adapter;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void onInitView() {
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.setAdapter(adapter = new ViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.setSelectedItemId(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onInitData() {

    }
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            if (num == 0) {
                return new HomeFragment();
            }
            return new ContentFragment();
        }

        @Override
        public int getCount() {
            return 6;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "首页";
                case 1:
                    return "性感妹子";
                case 2:
                    return "日本妹子";
                case 3:
                    return "台湾妹子";
                case 4:
                    return "清纯妹子";
                case 5:
                    return "妹子自拍";
                default:
                    return null;
            }
        }
    }

}
