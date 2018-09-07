package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.LoginActivity;
import com.xgkj.ilive.activity.MainActivity;
import com.xgkj.ilive.mvp.contract.PhoneCodeLoginContract;
import com.xgkj.ilive.mvp.model.LoginModel;
import com.xgkj.ilive.mvp.model.SmsCodeModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/8/3 0003 19:02
 */

public class PhoneCodeLoginPresenter implements PhoneCodeLoginContract.Presenter {

    private PhoneCodeLoginContract.View mPhoneCodeView;
    private Context mContext;

    public PhoneCodeLoginPresenter(PhoneCodeLoginContract.View view){
        mPhoneCodeView = view;
        mContext = (Context) mPhoneCodeView;
    }


    /**
     * 获取手机验证码
     */
    @Override
    public void getPhoneCode() {
        EditText phoneWidget = mPhoneCodeView.getPhoneWidget();
        String phone = phoneWidget.getText().toString();
        if (phone == null){
            Toast.makeText(mContext, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> loginMap = new HashMap<>();
        loginMap.put("mobile",phone);
        loginMap.put("type","login_captcha");

        JSONObject jobj = GsonUtils.upJson(loginMap);
        Subscription  subscription = OkGo.post(NetUrl.GET_SMS_CODE)
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
                            Toast.makeText(mContext,"验证码发送成功!",Toast.LENGTH_SHORT).show();
                        }else if ("404".equals(code)){
                            String msg = apidata.getMsg();
                            Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable.toString());
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void getCodeLogin() {
        EditText phoneCodeWidget = mPhoneCodeView.getPhoneCodeWidget();
        EditText phoneWidget = mPhoneCodeView.getPhoneWidget();
        String phone = phoneWidget.getText().toString();
        String phoneCode = phoneCodeWidget.getText().toString();
        if (phone == null){
            Toast.makeText(mContext, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phoneCode == null){
            Toast.makeText(mContext, "验证码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> codeLogin = new HashMap<>();
        codeLogin.put("mobile",phone);
        codeLogin.put("captcha",phoneCode);
        JSONObject jobj = GsonUtils.upJson(codeLogin);

        Subscription subscription = OkGo.post(NetUrl.USER_LOGIN)
                .upJson(jobj)
                .getCall(StringConvert.create(),RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LoginModel loginModel = GsonUtils.jsonToBean(s, LoginModel.class);
                        LoginModel.APIDATABean apidata = loginModel.getAPIDATA();
                        LoginModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200) {
                            //Toast.makeText(mContext,ret.toString(), Toast.LENGTH_SHORT).show();
                            String accesstoken = ret.getAccesstoken();
                            Log.e("accesstoken", accesstoken);
                            SharedPreferencesUtil.saveAccessToken(mContext, "access_token", accesstoken);
                            Intent intent = new Intent(mContext, MainActivity.class);
                            mContext.startActivity(intent);
                            LoginActivity loginActivity = (LoginActivity) mContext;
                            loginActivity.finish();
                        } else if (code == 404) {
                            Toast.makeText(mContext, ret.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }
}
