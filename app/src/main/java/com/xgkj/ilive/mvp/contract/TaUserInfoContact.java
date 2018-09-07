package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.TaUserModel;
import com.xgkj.ilive.mvp.model.bean.TaUserInfoBean;

/**
 * Created by admin on 2017/11/2.
 */

public interface TaUserInfoContact {
    interface Model {

    }

    interface View {
        /**
         * 获取用户信息
         * @param
         */
        void getUserInfoFinished(TaUserModel.APIDATABean ret, String type);
    }

    interface Presenter {

        /**
         * 获取用户信息
         */
        void getUserInfo(int to_uid,String type);
    }
}
