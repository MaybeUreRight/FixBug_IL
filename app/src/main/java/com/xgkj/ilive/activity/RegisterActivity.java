package com.xgkj.ilive.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.RegisterContract;
import com.xgkj.ilive.mvp.model.SmsCodeModel;
import com.xgkj.ilive.mvp.presenter.RegisterPresenter;
import com.xgkj.ilive.receiver.NetBroadcastReceiver;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.NetEvent;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 17:25
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View, NetEvent {

    @BindView(R.id.register_back)
    ImageView register_back;
    @BindView(R.id.register_finish)
    TextView register_finish;
    @BindView(R.id.et_nickname)
    EditText et_nickname;
    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.et_phone_password)
    EditText et_phone_password;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.identtifying_code)
    Button identtifying_code;

    private RegisterPresenter registerPresenter;
    private NetBroadcastReceiver netBroadcastReceiver;
    private int netMobile;

    private Timer timer;
    private TimerTask timerTask;
    private int countDown = 60;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (netBroadcastReceiver == null) {
            netBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(netBroadcastReceiver, intentFilter);

            netBroadcastReceiver.setNetEvent(this);
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        registerPresenter = new RegisterPresenter(this);
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);


        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (countDown > 0) {
                            countDown--;
                            String str = "获取验证码(" + countDown + "秒)";
                            identtifying_code.setText(str);
                        } else {
                            String str = "获取验证码";
                            identtifying_code.setText(str);
                            identtifying_code.setClickable(true);
                        }
                    }
                });
            }
        };
    }

    @OnClick({R.id.register_back, R.id.register_finish, R.id.identtifying_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_back:
                onBackPressed();
                break;
            case R.id.register_finish:
                if (netMobile == 0 || netMobile == 1) {
                    registerPresenter.startRegister();
                } else if (netMobile == -1) {
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.identtifying_code:
                if (netMobile == 0 || netMobile == 1) {
                    registerPresenter.getSmsCode();
                    timer.schedule(timerTask, 0, 1000);
                    identtifying_code.setClickable(false);
                } else if (netMobile == -1) {
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public String getNickName() {
        return et_nickname.getText().toString().trim();
    }

    @Override
    public String getPhoneNumber() {
        return et_phone_number.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_phone_password.getText().toString().trim();
    }

    @Override
    public String getSmsCode() {
        return et_code.getText().toString().trim();
    }

    @Override
    public void sendSuccessSms(String json) {
        timer.cancel();
        SmsCodeModel smsCodeModel = GsonUtils.jsonToBean(json, SmsCodeModel.class);
        SmsCodeModel.APIDATABean apidata = smsCodeModel.getAPIDATA();
        String code = apidata.getCode();
        if ("200".equals(code)) {
            Toast.makeText(this, "验证码发送成功!", Toast.LENGTH_SHORT).show();
        } else if ("404".equals(code)) {
            String msg = apidata.getMsg();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
        String str = "获取验证码";
        identtifying_code.setText(str);
        identtifying_code.setClickable(true);
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
    }


    @Override
    protected void onDestroy() {
        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
            if (timer != null) {
                timer.cancel();
            }
        }
        super.onDestroy();
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
