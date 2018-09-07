package com.xgkj.ilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.InformationDetailContract;
import com.xgkj.ilive.mvp.presenter.InformationDetailPresenter;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class InfotmationDetailActivity extends BaseActivity implements InformationDetailContract.View{

    private InformationDetailPresenter informationDetailPresenter;
    @BindView(R.id.info_detail_webview)
    WebView info_detail_webview;
    @BindView(R.id.info_back)
    TextView info_back;
    @BindView(R.id.info_close)
    TextView info_close;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_infotmation_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        informationDetailPresenter = new InformationDetailPresenter(this);
        informationDetailPresenter.getLoadWebPager(id);
    }

    @Override
    public WebView getWebViewWidgit() {
        //判断webview是否为空
        if (info_detail_webview == null){
            return info_detail_webview = (WebView) findViewById(R.id.info_detail_webview);
        }
        return info_detail_webview;
    }

    @OnClick({R.id.info_back,R.id.info_close})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.info_back:
                onBackPressed();
                break;
            case R.id.info_close:
                InfotmationDetailActivity.this.finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
