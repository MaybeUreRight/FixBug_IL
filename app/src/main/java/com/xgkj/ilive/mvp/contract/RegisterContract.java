package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 15:06
 */

public interface RegisterContract {
    interface Model {
    }

    interface View {
        //获取昵称
        String getNickName();
        //获取手机号
        String getPhoneNumber();
        //获取密码
        String getPassword();
        //获取验证码
        String getSmsCode();
        //发送成功的验证码
        void sendSuccessSms(String json);
    }

    interface Presenter {
        //开始注册
        void startRegister();
        //获取验证码
        void getSmsCode();

    }
}
