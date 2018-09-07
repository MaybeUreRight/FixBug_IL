package com.xgkj.ilive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xgkj.ilive.fragment.MyLiveFragment;
import com.xgkj.ilive.fragment.MyVideoFragment;

public class VideoPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fm;

    public VideoPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment =  new MyVideoFragment();
                break;
            case 1:
                fragment = new MyLiveFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
    }

  /*  boolean canScrollVertically(int position, int direction) {
        boolean fragment = false;
        switch (position) {
            case 0:
                fragment = ((TopicMainFragment) getItem(position)).canScrollVertically(direction);
                break;
            case 1:
                fragment = ((TopicHotFragment) getItem(position)).canScrollVertically(direction);
                break;
        }
        return fragment;
    }*/
}
