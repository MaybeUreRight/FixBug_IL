package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.xgkj.ilive.mvp.contract.MineVideoContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:50
 */

public class MineVideoPresenter implements MineVideoContract.Presenter {

    private MineVideoContract.View mMineVideoView;
    private Context mContext;

    public MineVideoPresenter(MineVideoContract.View view){
        mMineVideoView = view;
        mContext = (Context) mMineVideoView;
    }

}
