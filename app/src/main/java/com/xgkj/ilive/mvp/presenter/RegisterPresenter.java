package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.RegisterActivity;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.RegisterContract;
import com.xgkj.ilive.mvp.model.RegisterModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 15:06
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mRegisterView;
    private RegisterActivity mContext;

    public RegisterPresenter(RegisterContract.View view) {
        mRegisterView = view;
        mContext = (RegisterActivity) mRegisterView;
    }

    @Override
    public void startRegister() {
        String nickName = mRegisterView.getNickName();
        String phoneNumber = mRegisterView.getPhoneNumber();
        String password = mRegisterView.getPassword();
        String smsCode = mRegisterView.getSmsCode();

        if (TextUtils.isEmpty(nickName) || "".equals(nickName)) {
            Toast.makeText(mContext, "昵称不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(phoneNumber) || "".equals(phoneNumber)) {
            Toast.makeText(mContext, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password) || "".equals(password)) {
            Toast.makeText(mContext, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(smsCode) || "".equals(smsCode)) {
            Toast.makeText(mContext, "验证码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        //注册的集合
        Map<String, String> registerMap = new HashMap<>();
        registerMap.put("mobile", phoneNumber);
        registerMap.put("password", password);
        registerMap.put("nickname", nickName);
        registerMap.put("captcha", smsCode);

        JSONObject jobj = GsonUtils.upJson(registerMap);

        Subscription subscription = OkGo.post(NetUrl.REGISTER_USER_CODE)
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
                        Log.e("startRegister", s);
                        RegisterModel registerModel = GsonUtils.jsonToBean(s, RegisterModel.class);
                        RegisterModel.APIDATABean apidata = registerModel.getAPIDATA();
                        RegisterModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (200 == code) {
                            Toast.makeText(mContext, "注册成功!", Toast.LENGTH_SHORT).show();
                            RegisterActivity registerActivity = (RegisterActivity) mContext;
                            registerActivity.finish();
                        } else if (404 == code) {
                            String msg = ret.getMsg();
                            Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext, throwable.toString());
                    }
                });

    }

    @Override
    public void getSmsCode() {
        String phoneNumber = mRegisterView.getPhoneNumber();
        if (TextUtils.isEmpty(phoneNumber) || "".equals(phoneNumber)) {
            Toast.makeText(mContext, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> smsMap = new HashMap<>();
        smsMap.put("mobile", phoneNumber);
        smsMap.put("type", "reg_captcha");
        JSONObject jobj = GsonUtils.upJson(smsMap);


        Subscription subscribe = OkGo.post(NetUrl.GET_SMS_CODE)
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
                        mRegisterView.sendSuccessSms(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext, throwable.toString());
                    }
                });
    }


}
