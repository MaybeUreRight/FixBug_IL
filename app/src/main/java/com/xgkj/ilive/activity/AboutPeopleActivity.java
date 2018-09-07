package com.xgkj.ilive.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutPeopleActivity extends BaseActivity {

    private static final int REQUEST_CALL_PHONE_CODE = 1;
    @BindView(R.id.login_back)
    ImageView login_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.app_version_name)
    TextView app_version_name;
    @BindView(R.id.custom_service)
    TextView custom_service;
    @BindView(R.id.content_cooperate)
    TextView content_cooperate;
    @BindView(R.id.custom_service_email)
    TextView custom_service_email;
    @BindView(R.id.user_agree)
    TextView user_agree;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_people;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("关于我们");
        AppManager.getAppManager().addActivity(this);
        PackageManager packageManager = getPackageManager();
        MobclickAgent.setCatchUncaughtExceptions(true);
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            app_version_name.setText("I智播 " + Float.parseFloat(packageInfo.versionCode + ""));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            MobclickAgent.reportError(this,e.toString());
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

    @OnClick({R.id.login_back, R.id.custom_service, R.id.content_cooperate,R.id.user_agree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                onBackPressed();
                break;
            case R.id.custom_service:
                String s = custom_service.getText().toString();
                playPhone(s);
                break;
            case R.id.content_cooperate:
                String s1 = content_cooperate.getText().toString();
                playPhone(s1);
                break;
            case R.id.user_agree:
                startActivity(new Intent(this,UserAgreementActivity.class));
                break;
//            case R.id.custom_service_email:
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                startActivity(intent);
//                break;
        }
    }

    private void playPhone(String phone) {
        if (Build.VERSION.SDK_INT >= 23) {
            //判断有没有拨打电话权限
            if (PermissionChecker.checkSelfPermission(AboutPeopleActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //请求拨打电话权限
                ActivityCompat.requestPermissions(AboutPeopleActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_CODE);
            }else {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CALL_PHONE_CODE:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + custom_service.getText().toString()));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
                }
                startActivity(intent);
                break;
        }
    }
}
