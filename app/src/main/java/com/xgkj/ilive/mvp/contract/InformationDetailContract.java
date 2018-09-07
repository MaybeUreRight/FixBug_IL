package com.xgkj.ilive.mvp.contract;

import android.webkit.WebView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/19 0019 18:28
 */

public interface InformationDetailContract {
    interface Model {
    }

    interface View {
        //获取webview控件
        WebView getWebViewWidgit();
    }

    interface Presenter {
        void getLoadWebPager(String id);
    }
}
