package com.xgkj.ilive.mvp.contract;

import android.widget.EditText;

/**
 * 作者：刘净辉
 * 日期: 2017/8/3 0003 19:02
 */

public interface PhoneCodeLoginContract {
    interface Model {
    }

    interface View {
        EditText getPhoneWidget();
        EditText getPhoneCodeWidget();
    }

    interface Presenter {

        void getPhoneCode();

        void getCodeLogin();
    }
}
