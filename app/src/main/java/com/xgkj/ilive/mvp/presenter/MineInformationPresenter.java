package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.xgkj.ilive.mvp.contract.MineInformationContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:58
 */

public class MineInformationPresenter implements MineInformationContract.Presenter {

    private MineInformationContract.View mMineInformationView;
    private Context mContext;

    public MineInformationPresenter(MineInformationContract.View view){
        mMineInformationView = view;
        mContext = (Context) mMineInformationView;
    }


}
