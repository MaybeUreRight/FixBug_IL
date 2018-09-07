package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.AdvertModel;
import com.xgkj.ilive.mvp.model.HomeModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 17:14
 */

public interface HomeContract {
    interface Model {
    }

    interface View {
        //获取广告条
        void getAdvertFinish(List<AdvertModel.APIDATABean.RetBean.ListBean> advertList);
        //获取推荐的列表
        void getGroomListItem( HomeModel.APIDATABean apidata);
    }

    interface Presenter {
        //获取广告图片
        void getAdvertPic();
        //获取推荐信息
        void getHomeGroom();
    }
}
