package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.MineVideoContract;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class MineVideoActivity extends BaseActivity implements MineVideoContract.View{

    @BindView(R.id.login_back)
    ImageView mine_video_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.mine_video_recyclerview)
    RecyclerView mine_video_recyclerview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("我的视频");
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    @OnClick(R.id.login_back)
    public void onClick(View view){
        switch (view.getId()){
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
