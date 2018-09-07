package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.GuidePagerAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.GuideContract;
import com.xgkj.ilive.mvp.presenter.GuidePagerPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GuidePagerActivity extends BaseActivity implements GuideContract.View, ViewPager.OnPageChangeListener {

    @BindView(R.id.guide_pager)
    ViewPager guide_pager;
    @BindView(R.id.guide_pager_btn)
    Button guide_pager_btn;

    private GuidePagerPresenter guidePagerPresenter;
    private List<ImageView> imageList;
    private static final String IS_FIRST ="is_first";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide_pager;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        guidePagerPresenter = new GuidePagerPresenter(this);
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);

        if (SharedPreferencesUtil.getPagerStatus(this,IS_FIRST)){
            //初始化数据
            guidePagerPresenter.initData();
            guide_pager.setOnPageChangeListener(this);
        }else {
            guidePagerPresenter.startActivity();
        }

    }

    @Override
    public void initDataFinish(List<ImageView> imageViewList) {
        imageList = imageViewList;
        guide_pager.setAdapter(new GuidePagerAdapter(imageViewList));
    }

    @OnClick(R.id.guide_pager_btn)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.guide_pager_btn:
                guidePagerPresenter.startActivity();
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (imageList != null){
            if (imageList.size()-1 == position){
                guide_pager_btn.setVisibility(View.VISIBLE);
            }else {
                guide_pager_btn.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
