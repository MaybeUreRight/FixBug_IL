package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.FindPasswordActivity;
import com.xgkj.ilive.mvp.contract.ForgetPasswordContract;
import com.xgkj.ilive.mvp.model.ForgetPasswordModel;
import com.xgkj.ilive.mvp.model.SmsCodeModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

import static android.R.attr.phoneNumber;

/**
 * 作者：刘净辉
 * 日期: 2017/7/16 0016 12:00
 */

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {

    private ForgetPasswordContract.View mForgetPwdView;
    private Context mContext;

    public ForgetPasswordPresenter(ForgetPasswordContract.View view){
        mForgetPwdView = view;
        mContext = (Context) mForgetPwdView;
    }


    @Override
    public void confirmFinish() {
        String phoneNumber = mForgetPwdView.getNickName();
        String newPwd = mForgetPwdView.getNewPwd();
        String againPwd = mForgetPwdView.getaAgainPwd();
        String smsCode = mForgetPwdView.getSmsCode();

        if (TextUtils.isEmpty(phoneNumber) || "".equals(phoneNumber)){
            Toast.makeText(mContext,"昵称或手机号不能为空!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(newPwd) || "".equals(newPwd)){
            Toast.makeText(mContext, "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(againPwd) || "".equals(againPwd)){
            Toast.makeText(mContext, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(smsCode) || "".equals(smsCode)){
            Toast.makeText(mContext, "验证码不能为空", Toast.LENGTH_SHORT).show();            return;
        }

        Map<String,String> forgetMap = new HashMap<>();
        forgetMap.put("mobile",phoneNumber);
        forgetMap.put("password",newPwd);
        forgetMap.put("confirm_password",againPwd);
        forgetMap.put("captcha",smsCode);

        JSONObject jobj = GsonUtils.upJson(forgetMap);

        Subscription subscription = OkGo.post(NetUrl.FORGET_PWD)
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
                        ForgetPasswordModel passwordModel = GsonUtils.jsonToBean(s, ForgetPasswordModel.class);
                        ForgetPasswordModel.APIDATABean apidata = passwordModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            Toast.makeText(mContext,"密码重置成功!",Toast.LENGTH_SHORT).show();
                            FindPasswordActivity finrpwd = (FindPasswordActivity) mContext;
                            finrpwd.finish();
                        }else if (code == 404){
                            String msg = apidata.getMsg();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }

    @Override
    public void getSmsCode() {
        String nickName = mForgetPwdView.getNickName();
        if (TextUtils.isEmpty(nickName) || "".equals(phoneNumber)){
            Toast.makeText(mContext, "昵称或手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> smsMap = new HashMap<>();
        smsMap.put("mobile",nickName);
        smsMap.put("type","forget_captcha");
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
                    }
                });
    }
}
