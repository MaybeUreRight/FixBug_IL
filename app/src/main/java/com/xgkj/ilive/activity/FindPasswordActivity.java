package com.xgkj.ilive.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.ForgetPasswordContract;
import com.xgkj.ilive.mvp.presenter.ForgetPasswordPresenter;
import com.xgkj.ilive.receiver.NetBroadcastReceiver;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.NetEvent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 10:48
 */

public class FindPasswordActivity extends BaseActivity implements ForgetPasswordContract.View, NetEvent {

    @BindView(R.id.et_nickname)
    EditText et_nickname;
    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.et_phone_password)
    EditText et_phone_password;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.get_sms_code)
    TextView get_sms_code;
    @BindView(R.id.get_phone_code)
    Button get_phone_code;

    private ForgetPasswordPresenter forgetPasswordPresenter;
    private NetBroadcastReceiver netBroadcastReceiver;
    private int netMobile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver,intentFilter);
            netBroadcastReceiver.setNetEvent(this);
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        forgetPasswordPresenter = new ForgetPasswordPresenter(this);
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    @OnClick({R.id.get_sms_code,R.id.get_phone_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.get_sms_code:
                if (netMobile == 0 || netMobile == 1) {
                    forgetPasswordPresenter.getSmsCode();
                }else if (netMobile == -1){
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.get_phone_code:
                if (netMobile == 0 || netMobile == 1) {
                    forgetPasswordPresenter.confirmFinish();
                }else if (netMobile == -1){
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public String getNickName() {
        return et_nickname.getText().toString();
    }

    @Override
    public String getNewPwd() {
        return et_phone_number.getText().toString();
    }

    @Override
    public String getaAgainPwd() {
        return et_phone_password.getText().toString();
    }

    @Override
    public String getSmsCode() {
        return et_code.getText().toString();
    }


    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
    }

    @Override
    protected void onDestroy() {
        if (netBroadcastReceiver != null){
            unregisterReceiver(netBroadcastReceiver);
        }
        super.onDestroy();
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
