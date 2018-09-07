package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期：2017/7/12 12:37
 */

public interface SplashContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {

        //延迟进行跳转的操作
        void delayJump();
        //开始跳转的方法
        void  startActivity();
        //关闭当前activity进行做一些销毁的操作
        void destory();

    }
}
