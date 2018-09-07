package com.xgkj.ilive.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.fragment.BrowseRecordsLiveFragment;
import com.xgkj.ilive.fragment.BrowseRecordsVideoFragment;
import com.xgkj.ilive.mvp.contract.BrowseRecordsContract;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class BrowseRecordsActivity extends BaseActivity implements BrowseRecordsContract.View{

    @BindView(R.id.login_back)
    ImageView browse_records_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.browse_records_live)
    TextView browse_records_live;
    @BindView(R.id.browse_records_video)
    TextView browse_records_video;
    @BindView(R.id.browse_records_live_underline)
    TextView browse_records_live_underline;
    @BindView(R.id.browse_records_video_underline)
    TextView browse_records_video_underline;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_browse_records;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("浏览记录");
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.browse_records_framelayout,new BrowseRecordsLiveFragment()).commit();
    }

    @OnClick({R.id.login_back,R.id.browse_records_live,R.id.browse_records_video})
    public void onClick(View view){
        FragmentManager manager = getSupportFragmentManager();
        switch (view.getId()){
            case R.id.browse_records_live:
                browse_records_live_underline.setVisibility(View.VISIBLE);
                browse_records_video_underline.setVisibility(View.INVISIBLE);
                browse_records_live.setTextColor(Color.parseColor("#00a0ff"));
                browse_records_video.setTextColor(Color.BLACK);
                manager.beginTransaction().replace(R.id.browse_records_framelayout,new BrowseRecordsLiveFragment()).commit();
                break;
            case R.id.browse_records_video:
                browse_records_live_underline.setVisibility(View.INVISIBLE);
                browse_records_video_underline.setVisibility(View.VISIBLE);
                browse_records_video.setTextColor(Color.parseColor("#00a0ff"));
                browse_records_live.setTextColor(Color.BLACK);
                manager.beginTransaction().replace(R.id.browse_records_framelayout,new BrowseRecordsVideoFragment()).commit();
                break;
            case R.id.login_back:
                onBackPressed();
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
