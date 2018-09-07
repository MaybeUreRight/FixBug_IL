package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.MineModel;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 16:17
 */

public interface SettingsContract {
    interface Model {
    }

    interface View {
        //获取用户信息完成
        void getUserInfoFinished(MineModel.APIDATABean.RetBean ret);

    }

    interface Presenter {
        //开始跳转的方法
        <T> void startActivity(Class<T> cls);
        //获取用户信息
        void getUserInfo();
    }
}
