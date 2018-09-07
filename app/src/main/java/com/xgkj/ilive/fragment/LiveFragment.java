package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.FragmentAdapter;
import com.xgkj.ilive.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 16:51
 */

public class LiveFragment extends BaseFragment {

    @BindView(R.id.hot_live)
    TextView hot_live; //热门
    @BindView(R.id.new_best_live)
    TextView new_best_live; //最新
    @BindView(R.id.advance_notice)
    TextView advance_notice; //预告
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private int currentIndex;

    @Override
    protected int getLayoutResId() {
//        return R.layout.fragment_live;
        return R.layout.fragment_live_new;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MobclickAgent.setCatchUncaughtExceptions(true);

        ArrayList<BaseFragment> mFragmentList = new ArrayList<>();
        HotFragment hotFragment = new HotFragment();
        BestNewFragment bestNewFragment = new BestNewFragment();
        AdvanceNoticeFragment advanceNoticeFragment = new AdvanceNoticeFragment();
        mFragmentList.add(hotFragment);
        mFragmentList.add(bestNewFragment);
        mFragmentList.add(advanceNoticeFragment);

        FragmentPagerAdapter mFragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    @OnClick({R.id.hot_live, R.id.new_best_live, R.id.advance_notice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hot_live:
                if (currentIndex == 0) {
                    return;
                }
                currentIndex = 0;
                viewPager.setCurrentItem(currentIndex);
                break;
            case R.id.new_best_live:
                if (currentIndex == 1) {
                    return;
                }
                currentIndex = 1;
                viewPager.setCurrentItem(currentIndex);
                break;
            case R.id.advance_notice:
                if (currentIndex == 2) {
                    return;
                }
                currentIndex = 2;
                viewPager.setCurrentItem(currentIndex);
                break;
            default:
                break;
        }
    }

    private void changeFragment(int currentIndex) {
        new_best_live.setTextColor(getResources().getColor(R.color.white));
        hot_live.setTextColor(getResources().getColor(android.R.color.white));
        advance_notice.setTextColor(getResources().getColor(android.R.color.white));

        switch (currentIndex) {
            case 0:
                hot_live.setTextColor(getResources().getColor(R.color.publish_font_color));
                break;
            case 1:
                new_best_live.setTextColor(getResources().getColor(R.color.publish_font_color));
                break;
            case 2:
                advance_notice.setTextColor(getResources().getColor(R.color.publish_font_color));
                break;
            default:
                break;
        }


    }
}
