package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.PublishInformationModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 15:38
 */

public interface PublishInformationContract {
    interface Model {
    }

    interface View {
        void getInfomationListFinished( List<PublishInformationModel.APIDATABean.RetBean.ListBean> list);

        void getLoadMore( List<PublishInformationModel.APIDATABean.RetBean.ListBean> list);
    }

    interface Presenter {

        /**
         * 获取资讯列表
         * @param type
         */
        void getInformationList(int type,int records,int pnum);


        /**
         * 获取资讯列表
         * @param type
         */
        void getLoadMore(int type,int records,int pnum);
    }
}
