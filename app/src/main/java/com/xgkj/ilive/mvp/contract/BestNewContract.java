package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.BestNewModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 13:45
 */

public interface BestNewContract {
    interface Model {
    }

    interface View {
        void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list);

        void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret);
    }

    interface Presenter {
        void getBestNewData();

        void getLoadMoreBestNewData(int pnum);
    }
}
