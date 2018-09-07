package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.SplashContract;
import com.xgkj.ilive.mvp.presenter.SplashPresenter;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashContract.View{


    @BindView(R.id.splash)
    ImageView splash;

    private SplashPresenter splashPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        splashPresenter = new SplashPresenter(this);
        Glide.with(this).load(R.drawable.activity_splash)
                .apply(App.requestOptions.placeholder(R.drawable.activity_splash).error(R.drawable.activity_splash))
                .into(splash);
        splashPresenter.delayJump();
    }

    @Override
    protected void onDestroy() {
        splashPresenter.destory();
        super.onDestroy();
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
