package com.xgkj.ilive.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.LoginContract;
import com.xgkj.ilive.mvp.model.ThirdLoginModel;
import com.xgkj.ilive.mvp.presenter.LoginPresenter;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.receiver.NetBroadcastReceiver;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.NetEvent;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity implements LoginContract.View, NetEvent {

    @BindView(R.id.login_phone_number)
    EditText login_phone_number;
    @BindView(R.id.login_phone_pwd)
    EditText login_phone_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.register_number)
    TextView register_number;
    @BindView(R.id.forget_pwd)
    TextView forget_pwd;
    @BindView(R.id.qq_login)
    Button qq_login;
    @BindView(R.id.wechat_login)
    Button wechat_login;
    @BindView(R.id.phone_login)
    Button phone_login;

    private LoginPresenter loginPresenter;

    /**
     * 6.0权限处理
     **/
    private boolean bPermission = false;
    private final int WRITE_PERMISSION_REQ_CODE = 100;
    private UMShareAPI mShareAPI;
    private boolean flag = false;
    private NetBroadcastReceiver netBroadcastReceiver;
    private int netMobile;


    private boolean checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(LoginActivity.this,
                        (String[]) permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_PERMISSION_REQ_CODE:
                for (int ret : grantResults) {
                    if (ret != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                }
                bPermission = true;
                break;
            default:
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(false);

        bPermission = checkPublishPermission();
        loginPresenter = new LoginPresenter(this);

        if (null == mShareAPI) {
            mShareAPI = UMShareAPI.get(LoginActivity.this);
        }
    }

    @OnClick({R.id.btn_login, R.id.register_number, R.id.forget_pwd, R.id.qq_login, R.id.wechat_login, R.id.phone_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (netMobile == 0 || netMobile == 1) {
                    loginPresenter.usersLogin();
                } else if (netMobile == -1) {
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_number:
                if (netMobile == 0 || netMobile == 1) {
                    loginPresenter.startActivity(RegisterActivity.class);
                } else if (netMobile == -1) {
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_pwd:
                loginPresenter.startActivity(FindPasswordActivity.class);
                break;
            case R.id.qq_login:
                if (netMobile == 0 || netMobile == 1) {
                    SHARE_MEDIA qq = SHARE_MEDIA.QQ;
                    boolean install = mShareAPI.isInstall(this, qq);
                    if (install) {
                        mShareAPI.doOauthVerify(LoginActivity.this, qq, qqUmAuthListener);
                    } else {
                        Toast.makeText(this, R.string.check_app_is_install, Toast.LENGTH_SHORT).show();
                    }
                } else if (netMobile == -1) {
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wechat_login:
                if (netMobile == 0 || netMobile == 1) {
                    SHARE_MEDIA weixin = SHARE_MEDIA.WEIXIN;
                    boolean wechat = mShareAPI.isInstall(this, weixin);
                    Toast.makeText(this, wechat + "", Toast.LENGTH_SHORT).show();
                    if (wechat) {
                        mShareAPI.doOauthVerify(LoginActivity.this, weixin, weixinUmAuthListener);
                        mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, weixinUmAuthListener);
                    } else {
                        Toast.makeText(this, R.string.check_app_is_install, Toast.LENGTH_SHORT).show();
                    }
                } else if (netMobile == -1) {
                    Toast.makeText(this, R.string.network_none_connection, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.phone_login:
                startActivity(new Intent(this, PhoneCodeLoginActivity.class));
                finish();
                break;
        }
    }

    /**
     * qq授权回调监听
     */
    private UMAuthListener qqUmAuthListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(LoginActivity.this, "授权开始", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    Toast.makeText(LoginActivity.this, "授权成功!", Toast.LENGTH_SHORT).show();
                    Map<String, String> qqMap = new HashMap<>();
                    Set<String> keySet = map.keySet();
                    Iterator<String> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        String value = map.get(key);
                        Log.e("onComplete", "key=" + key + "---------------------------value=" + value);
                        if ("uid".equals(key)) {
                            qqMap.put("uid", value);
                        }

                        if ("iconurl".equals(key)) {
                            qqMap.put("pic", value);
                        }

                        if ("name".equals(key)) {
                            qqMap.put("nickname", value);
                        }

                        if ("gender".equals(key)) {
                            qqMap.put("sex", value == "男" ? "1" : "2");
                        }

                        qqOrWeChatLogin(qqMap);
                    }
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {

                }
            });

        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            MobclickAgent.reportError(LoginActivity.this, throwable.toString());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    };


    private UMAuthListener weixinUmAuthListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(LoginActivity.this, "授权开始", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Set<String> keySet = map.keySet();
            Map<String, String> weixinMap = new HashMap<>();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = map.get(key);
                if ("uid".equals(key)) {
                    weixinMap.put("uid", value);
                }
                if ("iconurl".equals(key)) {
                    weixinMap.put("pic", value);
                }

                if ("name".equals(key)) {
                    weixinMap.put("nickname", value);
                }

                if ("gender".equals(key)) {
                    if (value.equals("男")) {
                        weixinMap.put("sex", "1");
                    } else if (value.equals("女")) {
                        weixinMap.put("sex", "2");
                    }
                }
            }

            qqOrWeChatLogin(weixinMap);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText(LoginActivity.this, "授权错误", Toast.LENGTH_SHORT).show();
            MobclickAgent.reportError(LoginActivity.this, throwable.toString());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    };

    private void qqOrWeChatLogin(Map<String, String> weixinMap) {
        JSONObject jobj = GsonUtils.upJson(weixinMap);
        Subscription subscription = OkGo.post(NetUrl.THIRD_LOGIN_REGISTER)
                .upJson(jobj)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        ThirdLoginModel thirdLoginModel = GsonUtils.jsonToBean(s, ThirdLoginModel.class);
                        ThirdLoginModel.APIDATABean apidata = thirdLoginModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200) {
                            String accesstoken = apidata.getAccesstoken();
                            SharedPreferencesUtil.saveAccessToken(LoginActivity.this, "access_token", accesstoken);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (code == 404) {
                            String msg = apidata.getMsg();
                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(LoginActivity.this, throwable.toString());
                        Toast.makeText(LoginActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getPhoneNumber() {
        return login_phone_number.getText().toString();
    }

    @Override
    public String getPhonePwd() {
        return login_phone_pwd.getText().toString();
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
    }

    @Override
    protected void onDestroy() {
        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
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
