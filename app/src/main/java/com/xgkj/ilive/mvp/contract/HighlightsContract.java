package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.BestNewModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/17 0017 14:16
 */

public interface HighlightsContract {
    interface Model {
    }

    interface View {
        void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list);

        void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list);
    }

    interface Presenter {
        void getHighlightsData(int pnum);

        /**
         * 加载更多数据
         * @param pnum
         */
        void getLoadMoreHighlightsData(int pnum);
    }
}
