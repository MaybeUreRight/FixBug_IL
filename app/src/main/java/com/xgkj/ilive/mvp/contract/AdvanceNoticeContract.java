package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.BestNewModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 15:10
 */

public interface AdvanceNoticeContract {
    interface Model {
    }

    interface View {
        void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret);
        void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret);
    }

    interface Presenter {
        void getHotData(int get_video,int type,int records,int pnum);
        void getLoadMoreHotData(int get_video,int type,int records,int pnum);
    }
}
