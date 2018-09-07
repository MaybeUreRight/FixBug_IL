package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.xgkj.ilive.mvp.contract.MainContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 16:33
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;
    private Context mContext;

    public MainPresenter(MainContract.View view){
        mMainView = view;
        mContext=(Context) mMainView;
    }


}
