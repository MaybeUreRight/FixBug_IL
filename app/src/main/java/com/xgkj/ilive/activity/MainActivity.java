package com.xgkj.ilive.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.fragment.HomeFragment;
import com.xgkj.ilive.fragment.LiveFragment;
import com.xgkj.ilive.fragment.MineFragment;
import com.xgkj.ilive.fragment.PublishFragment;
import com.xgkj.ilive.mvp.contract.MainContract;
import com.xgkj.ilive.utils.AppManager;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainContract.View{

    @BindView(R.id.home_rb)
    AutoLinearLayout home_rb;
    @BindView(R.id.live_rb)
    AutoLinearLayout live_rb;
    @BindView(R.id.rb_edit)
    ImageView rb_edit;
    @BindView(R.id.publish_rb_btn)
    AutoLinearLayout publish_rb_btn;
    @BindView(R.id.mine_rb_btn)
    AutoLinearLayout mine_rb_btn;
    @BindView(R.id.home_image)
    ImageView home_image;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.live_image)
    ImageView live_image;
    @BindView(R.id.tv_live)
    TextView tv_live;
    @BindView(R.id.publish_image)
    ImageView publish_image;
    @BindView(R.id.tv_publish)
    TextView tv_publish;
    @BindView(R.id.mine_image)
    ImageView mine_image;
    @BindView(R.id.tv_mine)
    TextView tv_mine;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();

        home_image.setImageResource(R.drawable.home_pager_checked);
        tv_home.setTextColor(Color.parseColor("#00a0ff"));
        live_image.setImageResource(R.drawable.live_unchecked);
        tv_live.setTextColor(Color.BLACK);
        publish_image.setImageResource(R.drawable.publish_unchecked);
        tv_publish.setTextColor(Color.BLACK);
        mine_image.setImageResource(R.drawable.mine_uncheck);
        tv_mine.setTextColor(Color.BLACK);

        home_rb.setClickable(false);
        live_rb.setClickable(true);
        publish_rb_btn.setClickable(true);
        mine_rb_btn.setClickable(true);

    }

    @OnClick({R.id.home_rb,R.id.live_rb,R.id.rb_edit,R.id.publish_rb_btn,R.id.mine_rb_btn})
    public void onClick(View view){
        FragmentManager manager = getSupportFragmentManager();
        switch (view.getId()){
            case R.id.home_rb:
                home_image.setImageResource(R.drawable.home_pager_checked);
                tv_home.setTextColor(Color.parseColor("#00a0ff"));
                live_image.setImageResource(R.drawable.live_unchecked);
                tv_live.setTextColor(Color.BLACK);
                publish_image.setImageResource(R.drawable.publish_unchecked);
                tv_publish.setTextColor(Color.BLACK);
                mine_image.setImageResource(R.drawable.mine_uncheck);
                tv_mine.setTextColor(Color.BLACK);

                home_rb.setClickable(false);
                live_rb.setClickable(true);
                publish_rb_btn.setClickable(true);
                mine_rb_btn.setClickable(true);

                manager.beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();
                break;
            case R.id.live_rb:
                home_image.setImageResource(R.drawable.home_pager_unchecked);
                tv_home.setTextColor(Color.BLACK);
                live_image.setImageResource(R.drawable.live_checked);
                tv_live.setTextColor(Color.parseColor("#00a0ff"));
                publish_image.setImageResource(R.drawable.publish_unchecked);
                tv_publish.setTextColor(Color.BLACK);
                mine_image.setImageResource(R.drawable.mine_uncheck);
                tv_mine.setTextColor(Color.BLACK);

                home_rb.setClickable(true);
                live_rb.setClickable(false);
                publish_rb_btn.setClickable(true);
                mine_rb_btn.setClickable(true);

                manager.beginTransaction().replace(R.id.frame_layout,new LiveFragment()).commit();
                break;
            case R.id.rb_edit:
                startActivity(new Intent(this,EditorActivity.class));
                break;
            case R.id.publish_rb_btn:
                home_image.setImageResource(R.drawable.home_pager_unchecked);
                tv_home.setTextColor(Color.BLACK);
                live_image.setImageResource(R.drawable.live_unchecked);
                tv_live.setTextColor(Color.BLACK);
                publish_image.setImageResource(R.drawable.publish_checked);
                tv_publish.setTextColor(Color.parseColor("#00a0ff"));
                mine_image.setImageResource(R.drawable.mine_uncheck);
                tv_mine.setTextColor(Color.BLACK);

                home_rb.setClickable(true);
                live_rb.setClickable(true);
                publish_rb_btn.setClickable(false);
                mine_rb_btn.setClickable(true);


                manager.beginTransaction().replace(R.id.frame_layout,new PublishFragment()).commit();
                break;
            case R.id.mine_rb_btn:
                home_image.setImageResource(R.drawable.home_pager_unchecked);
                tv_home.setTextColor(Color.BLACK);
                live_image.setImageResource(R.drawable.live_unchecked);
                tv_live.setTextColor(Color.BLACK);
                publish_image.setImageResource(R.drawable.publish_unchecked);
                tv_publish.setTextColor(Color.BLACK);
                mine_image.setImageResource(R.drawable.mine_checked);
                tv_mine.setTextColor(Color.parseColor("#00a0ff"));

                home_rb.setClickable(true);
                live_rb.setClickable(true);
                publish_rb_btn.setClickable(true);
                mine_rb_btn.setClickable(false);

                manager.beginTransaction().replace(R.id.frame_layout,new MineFragment()).commit();
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
