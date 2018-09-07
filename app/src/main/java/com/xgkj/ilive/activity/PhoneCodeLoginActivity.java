package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.PhoneCodeLoginContract;
import com.xgkj.ilive.mvp.presenter.PhoneCodeLoginPresenter;
import com.xgkj.ilive.utils.AppManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/8/3 0003 18:50
 */

public class PhoneCodeLoginActivity extends BaseActivity implements PhoneCodeLoginContract.View{

    @BindView(R.id.login_back)
    ImageView login_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.send_code)
    Button send_code;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.btn_login)
    Button btn_login;
    private PhoneCodeLoginPresenter phoneCodeLoginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("手机登录");
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        phoneCodeLoginPresenter = new PhoneCodeLoginPresenter(this);
    }

    @OnClick({R.id.login_back,R.id.send_code,R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_back:
                onBackPressed();
                break;
            case R.id.send_code:
                phoneCodeLoginPresenter.getPhoneCode();
                break;
            case R.id.btn_login:
                phoneCodeLoginPresenter.getCodeLogin();
                break;
        }
    }

    @Override
    public EditText getPhoneWidget() {
        return et_phone_number;
    }

    @Override
    public EditText getPhoneCodeWidget() {
        return et_code;
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
