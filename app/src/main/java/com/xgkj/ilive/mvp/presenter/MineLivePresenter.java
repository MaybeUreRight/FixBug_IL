package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.xgkj.ilive.mvp.contract.MineLiveContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:40
 */

public class MineLivePresenter implements MineLiveContract.Presenter {

    private MineLiveContract.View mMineLiveView;
    private Context mContext;

    public MineLivePresenter(MineLiveContract.View view){
        mMineLiveView = view;
        mContext = (Context) mMineLiveView;
    }
}
