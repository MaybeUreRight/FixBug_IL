package com.xgkj.ilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.utils.AppManager;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class EditorActivity extends BaseActivity {


    @BindView(R.id.live_rb)
    AutoLinearLayout live_rb;  //直播
    @BindView(R.id.announce_in_advance)
    AutoLinearLayout announce_in_advance;  //预告
    @BindView(R.id.publish_cancel)
    ImageView publish_cancel;  //取消
    @BindView(R.id.btn_advance)
    ImageView btn_advance;
    @BindView(R.id.btn_live)
    ImageView btn_live;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    @OnClick({R.id.live_rb,R.id.announce_in_advance,R.id.publish_cancel,R.id.btn_advance,R.id.btn_live})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_live:
            case R.id.live_rb:
                startActivity(new Intent(this,LiveActivity.class));
                break;
            case R.id.btn_advance:
            case R.id.announce_in_advance:
                startActivity(new Intent(this,AnnounceInAdvanceActivity.class));
                break;
            case R.id.publish_cancel:
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
        MobclickAgent.onPause(this);
    }
}
