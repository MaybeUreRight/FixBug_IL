package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.model.BindingModel;
import com.xgkj.ilive.mvp.model.SmsCodeModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class BindingActivity extends BaseActivity {

    @BindView(R.id.login_back)
    ImageView login_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.get_phone_code)
    Button get_phone_code;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.settings_pwd)
    EditText settings_pwd;
    @BindView(R.id.btn_finished)
    Button btn_finished;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_binding;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("绑定手机号");
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    @OnClick({R.id.get_phone_code,R.id.login_back,R.id.btn_finished})
    public void onClick(View view){

        String phone_number = et_phone_number.getText().toString();
        String phone_code = et_code.getText().toString();
        String phone_pwd = settings_pwd.getText().toString();

        switch (view.getId()){
            case R.id.get_phone_code:
                if (TextUtils.isEmpty(phone_number)){
                    Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String,String> sendMap = new HashMap<>();
                sendMap.put("mobile",phone_number);
                sendMap.put("type","reg_captcha");

                JSONObject jobj = GsonUtils.upJson(sendMap);
                Subscription subscription = OkGo.post(NetUrl.GET_SMS_CODE)
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
                                SmsCodeModel smsCodeModel = GsonUtils.jsonToBean(s, SmsCodeModel.class);
                                SmsCodeModel.APIDATABean apidata = smsCodeModel.getAPIDATA();
                                String code = apidata.getCode();
                                if ("200".equals(code)){
                                    Toast.makeText(BindingActivity.this,"验证码发送成功!",Toast.LENGTH_SHORT).show();
                                }else if ("404".equals(code)){
                                    String msg = apidata.getMsg();
                                    Toast.makeText(BindingActivity.this,msg,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                MobclickAgent.reportError(BindingActivity.this,throwable.toString());
                            }
                        });
                break;
            case R.id.btn_finished:
                if (TextUtils.isEmpty(phone_number)){
                    Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone_code)){
                    Toast.makeText(this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone_pwd)){
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,String> bingPhoneMap = new HashMap<>();
                bingPhoneMap.put("captcha",phone_code);
                bingPhoneMap.put("mobile",phone_number);
                bingPhoneMap.put("password",phone_pwd);

                JSONObject jobj1 = GsonUtils.upJson(bingPhoneMap);
                OkGo.post(NetUrl.BING_PHONE_URL+ SharedPreferencesUtil.getAccessToken(BindingActivity.this,"access_token"))
                        .upJson(jobj1)
                        .getCall(StringConvert.create(),RxAdapter.<String>create())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {

                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                BindingModel bindingModel = GsonUtils.jsonToBean(s, BindingModel.class);
                                BindingModel.APIDATABean apidata = bindingModel.getAPIDATA();
                                int code = apidata.getCode();
                                if (code == 200){
                                    Toast.makeText(BindingActivity.this, "手机号绑定成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    String msg = apidata.getMsg();
                                    Toast.makeText(BindingActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                MobclickAgent.reportError(BindingActivity.this,throwable.toString());
                                Toast.makeText(BindingActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
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
