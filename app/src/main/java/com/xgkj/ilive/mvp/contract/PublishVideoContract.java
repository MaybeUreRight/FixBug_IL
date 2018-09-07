package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.PublishVideoModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 16:06
 */

public interface PublishVideoContract {
    interface Model {
    }

    interface View {
        void getVideoListFininsh(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list);

        void getVideoLoadMore(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list);
    }

    interface Presenter {

        /**
         * 获取点播的列表
         * @param type
         */
        void getVideoList(int type,int records,int pnum);

        /**
         * 获取点播加载更多
         * @param type
         * @param records
         * @param pnum
         */
        void  getVideoLoadMore(int type,int records,int pnum);


    }
}
