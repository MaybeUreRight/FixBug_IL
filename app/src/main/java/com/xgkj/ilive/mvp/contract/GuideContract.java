package com.xgkj.ilive.mvp.contract;

import android.widget.ImageView;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 10:45
 */

public interface GuideContract {
    interface Model {
    }

    interface View {
        //初始化完成的数据
        void initDataFinish(List<ImageView> imageViewList);
    }

    interface Presenter {

        void initData();

        ImageView setImageViewData(int resId);

        void startActivity();
    }
}
