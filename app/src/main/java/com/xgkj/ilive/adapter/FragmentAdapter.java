package com.xgkj.ilive.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xgkj.ilive.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2018/9/7/007
 * @description: $description$
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragmentList = new ArrayList<BaseFragment>();

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public BaseFragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();

    }
}
