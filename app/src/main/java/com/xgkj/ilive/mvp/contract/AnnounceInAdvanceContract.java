package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期: 2017/8/3 0003 13:52
 */

public interface AnnounceInAdvanceContract {
    interface Model {
    }

    interface View {

    }

    interface Presenter {
        /**
         * 创建预告
         * @param photoData base64 加密后的密码
         * @param title  标题
         * @param activity_time  时间
         * @param type   观看类型
         * @param type_value  加密后传入值，否则传入空
         */
        void createAnnounceInAdvance(String photoData,String title,String activity_time,int type,String type_value);
    }
}
