package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.LoginActivity;
import com.xgkj.ilive.activity.MainActivity;
import com.xgkj.ilive.mvp.contract.LoginContract;
import com.xgkj.ilive.mvp.model.LoginModel;
import com.xgkj.ilive.mvp.model.MineModel;
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
 * 日期: 2017/7/15 0015 14:15
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private Context mContext;
    private boolean flag = false;

    public LoginPresenter(LoginContract.View view) {
        mLoginView = view;
        mContext = (Context) mLoginView;
    }

    @Override
    public <T> void startActivity(Class<T> cls) {
        mContext.startActivity(new Intent(mContext, cls));
    }

    @Override
    public void usersLogin() {
        final String phoneNumber = mLoginView.getPhoneNumber();
        Log.e("phoneNumber",phoneNumber);
        String phonePwd = mLoginView.getPhonePwd();


        if (TextUtils.isEmpty(phoneNumber) || "".equals(phoneNumber)) {
            Toast.makeText(mContext, "手机号不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(phonePwd) || "".equals(phonePwd)) {
            Toast.makeText(mContext, "密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }


        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("mobile", phoneNumber);
        loginMap.put("password", phonePwd);

        JSONObject jobj = GsonUtils.upJson(loginMap);

        Subscription subscription = OkGo.post(NetUrl.USER_LOGIN)
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

                        Log.e("accesstoken", s);
                        LoginModel loginModel = GsonUtils.jsonToBean(s, LoginModel.class);
                        LoginModel.APIDATABean apidata = loginModel.getAPIDATA();
                        LoginModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200) {
                            //Toast.makeText(mContext,ret.toString(), Toast.LENGTH_SHORT).show();
                            String accesstoken = ret.getAccesstoken();
                            Log.e("accesstoken", accesstoken);
                            SharedPreferencesUtil.saveAccessToken(mContext, "access_token", accesstoken);
                            getUserInfo(accesstoken);
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
                        MobclickAgent.reportError(mContext, throwable.toString());
                    }
                });
    }

    public void getUserInfo(final String accesstoken) {

        Subscription subscription = OkGo.post(NetUrl.GET_USER_INFO + accesstoken)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("qqOrWeChatLogin", "________________________---------------" + s);
                        // Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                        MineModel mineModel = GsonUtils.jsonToBean(s, MineModel.class);
                        MineModel.APIDATABean apidata = mineModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200) {
                            MineModel.APIDATABean.RetBean ret = apidata.getRet();
                            //获取昵称
                            String nickname = ret.getNickname();
                            //获取头像
                            String pic = ret.getPic();

                            String accid = ret.getAccid();
                            String accid_token = ret.getAccid_token();
                            if (TextUtils.isEmpty(accid) || TextUtils.isEmpty(accid_token)) {
                                //进行注册云信id
                                registerYunxinId(nickname, pic, accesstoken);
                            }
                            // Log.e("accid","accid="+accid +"accid_token="+accid_token);
                            Map<String, String> loginMap = new HashMap<String, String>();

                            loginMap.put("accid", accid);
                            loginMap.put("accid_token", accid_token);
                            SharedPreferencesUtil.saveLoginStatus(mContext, loginMap);

                            LoginInfo info = new LoginInfo(accid, accid_token); // config...
                            RequestCallback<LoginInfo> callback =
                                    new RequestCallback<LoginInfo>() {
                                        @Override
                                        public void onSuccess(LoginInfo param) {
                                            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailed(int code) {
                                            Toast.makeText(mContext, "登录失败: " + code, Toast.LENGTH_LONG).show();
                                        }

                                        @Override
                                        public void onException(Throwable exception) {
                                            MobclickAgent.reportError(mContext, exception.toString());
                                        }
                                    };
                            NIMClient.getService(AuthService.class).login(info)
                                    .setCallback(callback);//进行登录,实现登录的回调
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext, throwable.toString());
                    }
                });
    }


    /**
     * 注册云信id
     */
    public void registerYunxinId(String nickname, String pic, String access_token) {

        Map<String, String> registerYunxinMap = new HashMap<>();

        registerYunxinMap.put("name", nickname);
        registerYunxinMap.put("icon", pic);

        JSONObject jobj = GsonUtils.upJson(registerYunxinMap);
        Subscription subscription = OkGo.post(NetUrl.REGISTER_YUNXIN_ID + access_token)
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
                        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                        Log.e("registerYunxinId", "call: " + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
