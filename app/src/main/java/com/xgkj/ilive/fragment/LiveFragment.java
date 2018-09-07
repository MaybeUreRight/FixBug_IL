package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 16:51
 */

public class LiveFragment extends BaseFragment{

    @BindView(R.id.hot_live)
    TextView hot_live; //热门
    @BindView(R.id.new_best_live)
    TextView new_best_live; //最新
    @BindView(R.id.advance_notice)
    TextView advance_notice; //预告

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        new_best_live.setTextColor(getResources().getColor(R.color.publish_font_color));
        hot_live.setTextColor(getResources().getColor(android.R.color.white));
        advance_notice.setTextColor(getResources().getColor(android.R.color.white));

        manager.beginTransaction().replace(R.id.framelayout_live,new BestNewFragment()).commit();
    }

    @OnClick({R.id.hot_live,R.id.new_best_live,R.id.advance_notice})
    public void onClick(View view){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        switch (view.getId()){
            case R.id.hot_live:
                hot_live.setTextColor(getResources().getColor(R.color.publish_font_color));
                new_best_live.setTextColor(getResources().getColor(android.R.color.white));
                advance_notice.setTextColor(getResources().getColor(android.R.color.white));

                manager.beginTransaction().replace(R.id.framelayout_live,new HotFragment()).commit();
                break;
            case R.id.new_best_live:
                new_best_live.setTextColor(getResources().getColor(R.color.publish_font_color));
                hot_live.setTextColor(getResources().getColor(android.R.color.white));
                advance_notice.setTextColor(getResources().getColor(android.R.color.white));

                manager.beginTransaction().replace(R.id.framelayout_live,new BestNewFragment()).commit();
                break;
            case R.id.advance_notice:
                advance_notice.setTextColor(getResources().getColor(R.color.publish_font_color));
                new_best_live.setTextColor(getResources().getColor(android.R.color.white));
                hot_live.setTextColor(getResources().getColor(android.R.color.white));

                manager.beginTransaction().replace(R.id.framelayout_live,new AdvanceNoticeFragment()).commit();
                break;
        }
    }
}
