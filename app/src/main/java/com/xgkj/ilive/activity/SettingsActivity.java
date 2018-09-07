package com.xgkj.ilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.SettingsContract;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.presenter.SettingsPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity implements SettingsContract.View{

    @BindView(R.id.login_back)
    ImageView mine_video_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.personal_profile)
    LinearLayout personal_profile; //个人资料
    @BindView(R.id.user_icon)
    CircleImageView user_icon;
    @BindView(R.id.update_password)
    LinearLayout update_password; //更改密码
    @BindView(R.id.wipe_cache_partition)
    LinearLayout wipe_cache_partition; //清除缓存
    @BindView(R.id.help_feedback)
    LinearLayout help_feedback; //帮助反馈
    @BindView(R.id.exit_the_account)
    Button exit_the_account;  //退出当前账号
    @BindView(R.id.about_many)
    LinearLayout about_many;
    private SettingsPresenter settingsPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("设置");
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        settingsPresenter = new SettingsPresenter(this);
        settingsPresenter.getUserInfo();
    }

    @OnClick({R.id.login_back,R.id.personal_profile,R.id.update_password,R.id.wipe_cache_partition,R.id.help_feedback,R.id.exit_the_account,R.id.about_many})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_back:
                onBackPressed();
                break;
            case R.id.personal_profile:
                settingsPresenter.startActivity(PersonalProfileActivity.class);
                break;
            case R.id.update_password:
                settingsPresenter.startActivity(FindPasswordActivity.class);
                break;
            //绑定手机
            case R.id.wipe_cache_partition:
                startActivity(new Intent(this,BindingActivity.class));
                break;
            case R.id.help_feedback:
                startActivity(new Intent(this,HelpActivity.class));
                break;
            case R.id.exit_the_account:
                SharedPreferencesUtil.clearAccessToke(this);
                startActivity(new Intent(this,LoginActivity.class));
                AppManager.getAppManager().finishAllActivity();
                finish();
                break;
            case R.id.about_many:
                startActivity(new Intent(this,AboutPeopleActivity.class));
                break;
        }
    }

    @Override
    public void getUserInfoFinished(MineModel.APIDATABean.RetBean ret) {
        Glide.with(this)
                .asBitmap()
                .load(ret.getPic())
                .apply(App.requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop())
                .into(user_icon);
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
