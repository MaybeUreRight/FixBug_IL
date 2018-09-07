package com.xgkj.ilive.mvp.presenter;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xgkj.ilive.activity.GuidePagerActivity;
import com.xgkj.ilive.activity.SplashActivity;
import com.xgkj.ilive.mvp.contract.SplashContract;

/**
 * 作者：刘净辉
 * 日期：2017/7/12 12:37
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View splashView;
    private Context mContext;

    private Handler splashHandler = new Handler(){};

    public SplashPresenter(SplashContract.View view){
        splashView = view;
        mContext=(Context) splashView;
    }

    @Override
    public void delayJump() {
        //延迟三秒进行跳转
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        },3000);
    }

    @Override
    public void startActivity() {
        Intent intent = new Intent(mContext, GuidePagerActivity.class);
        mContext.startActivity(intent);
        SplashActivity splashActivity = (SplashActivity) this.mContext;
        splashActivity.finish();
    }

    @Override
    public void destory() {
        //移除所发送的消息
        splashHandler.removeCallbacksAndMessages(null);
    }
}
