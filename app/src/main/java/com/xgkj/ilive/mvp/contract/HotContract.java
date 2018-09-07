package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.BestNewModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 19:09
 */

public interface HotContract {
    interface Model {
    }

    interface View {
        void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret);

        /**
         * 获取加载更多
         * @param ret
         */
        void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret);
    }

    interface Presenter {
        void getHotData(int pnum);

        /**
         * @param pnum
         */
        void getLoadMoreHotData(int pnum);

    }
}
