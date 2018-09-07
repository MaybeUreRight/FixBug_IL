package com.xgkj.ilive.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class UserAgreementActivity extends BaseActivity {

    @BindView(R.id.info_back)
    TextView info_back;
    @BindView(R.id.info_close)
    TextView info_close;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_agreement;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.loadUrl(NetUrl.USER_AGREE);
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.setWebViewClient(new WebViewClient());
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    }

    @OnClick({R.id.info_back,R.id.info_close})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.info_back:
                onBackPressed();
                break;
            case R.id.info_close:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
