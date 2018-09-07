package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.xgkj.ilive.mvp.contract.MineAttentionContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:11
 */

public class MineAttentionPresenter implements MineAttentionContract.Presenter {

    private MineAttentionContract.View mMineAttentionView;
    private Context mContext;

    public MineAttentionPresenter(MineAttentionContract.View view){
        mMineAttentionView = view;
        mContext = (Context) mMineAttentionView;
    }
}
