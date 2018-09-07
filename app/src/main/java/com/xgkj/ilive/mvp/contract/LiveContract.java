package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期: 2017/8/6 0006 09:55
 */

public interface LiveContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
        void createLive(String photoData,String activityTitle,int isLook,String activityIntroduced,String lookWayValue);
    }
}
