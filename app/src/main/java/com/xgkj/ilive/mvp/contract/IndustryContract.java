package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.IndustryModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/10 0010 10:53
 */

public interface IndustryContract {
    interface Model {
    }

    interface View {
        void getIndustryFinish(List<IndustryModel.APIDATABean.ListBean> list);
    }

    interface Presenter {
        void getIndustryList();
    }
}
