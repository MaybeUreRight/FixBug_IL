package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.graphics.Color;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xgkj.ilive.mvp.contract.InformationDetailContract;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

/**
 * 作者：刘净辉
 * 日期: 2017/7/19 0019 18:28
 */

public class InformationDetailPresenter implements InformationDetailContract.Presenter {

    private InformationDetailContract.View mInfoView;
    private Context mContext;

    public InformationDetailPresenter(InformationDetailContract.View view){
        mInfoView = view;
        mContext = (Context) mInfoView;
    }

    @Override
    public void getLoadWebPager(String id) {
        WebView webViewWidgit = mInfoView.getWebViewWidgit();
        webViewWidgit.loadUrl(NetUrl.BASE_PUBLISH_INFO+id+"&access_token="+ SharedPreferencesUtil.getAccessToken(mContext,"access_token"));
        webViewWidgit.setBackgroundColor(Color.TRANSPARENT);
        webViewWidgit.setWebViewClient(new WebViewClient());
        WebSettings settings = webViewWidgit.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    }
}
