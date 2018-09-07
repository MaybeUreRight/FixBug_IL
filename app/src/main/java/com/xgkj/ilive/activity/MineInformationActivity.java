package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class MineInformationActivity extends BaseActivity {

    @BindView(R.id.login_back)
    ImageView mine_information_back;
    @BindView(R.id.title_content)
    TextView title_content;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_information;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("我的资讯");
        MobclickAgent.setCatchUncaughtExceptions(true);
        AppManager.getAppManager().addActivity(this);
        initTitleData();
    }

    /**
     * 初始化title数据
     */
    private void initTitleData() {

    }

    @OnClick(R.id.login_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
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
