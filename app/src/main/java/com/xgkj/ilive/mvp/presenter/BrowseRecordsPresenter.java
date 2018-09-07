package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.xgkj.ilive.mvp.contract.BrowseRecordsContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:21
 */

public class BrowseRecordsPresenter implements BrowseRecordsContract.Presenter {

    private BrowseRecordsContract.View mBrowsRecordsView;
    private Context mContext;

    public BrowseRecordsPresenter(BrowseRecordsContract.View view){
        mBrowsRecordsView = view;
        mContext = (Context) mBrowsRecordsView;
    }
}
