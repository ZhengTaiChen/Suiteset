package com.zxg.mzitu.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.zxg.mzitu.R;
import com.zxg.suiteset.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tabHost)
  public  MaterialTabHost tabHost;
    @BindView(R.id.pager)
    ViewPager pager;
    private List<String> list = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        ButterKnife.bind(this);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(tabHost.newTab()
                    .setText(adapter.getPageTitle(i))
                    .setTabListener(new MaterialTabListener() {
                        @Override
                        public void onTabSelected(MaterialTab tab) {
                            pager.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabReselected(MaterialTab tab) {

                        }

                        @Override
                        public void onTabUnselected(MaterialTab tab) {

                        }
                    })
            );
        }

    }

    @Override
    protected void onInitData() {


    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            return new ContentFragment();
        }

        @Override
        public int getCount() {
            return 6;
        }


        @Override
        public int getItemPosition(Object object) {
            if (list != null && list.size() == 0) {
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
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
