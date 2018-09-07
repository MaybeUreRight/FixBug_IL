package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.PublishInformationModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/17 0017 11:42
 */

public interface HotInformationContract {
    interface Model {
    }

    interface View {
        void getInfomationListFinished( List<PublishInformationModel.APIDATABean.RetBean.ListBean> list);
        void getLoadInfomationList(List<PublishInformationModel.APIDATABean.RetBean.ListBean> list);
    }

    interface Presenter {
        void getInfoData(int pnum);

        /**
         * 获取加载更多的数据
         * @param pnum
         */
        void getLoadInfoData(int pnum);
    }
}
