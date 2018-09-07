package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 14:15
 */

public interface LoginContract {
    interface Model {
    }

    interface View {
        //获取手机号
        String getPhoneNumber();
        //获取手机密码
        String getPhonePwd();
    }

    interface Presenter {
        //开始跳转的方法
        <T> void startActivity(Class<T> cls);
        //用户登录
        void usersLogin();

    }
}
