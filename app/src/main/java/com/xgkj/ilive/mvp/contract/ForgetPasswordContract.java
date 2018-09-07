package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/16 0016 12:00
 */

public interface ForgetPasswordContract {
    interface Model {
    }

    interface View {
        //获取昵称
        String getNickName();
        //获取新的密码
        String getNewPwd();
        //获取又一次输入的密码
        String getaAgainPwd();
        //获取输入的短信验证码
        String getSmsCode();

    }

    interface Presenter {
        //确认完成
        void  confirmFinish();
        //获取手机验证码
        void getSmsCode();
    }
}
