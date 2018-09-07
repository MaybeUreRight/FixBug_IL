package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.SettingsContract;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 16:17
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View mSettingsView;
    private Context mContext;

    public SettingsPresenter(SettingsContract.View view){
        mSettingsView = view;
        mContext = (Context) mSettingsView;
    }

    @Override
    public <T> void startActivity(Class<T> cls) {
        mContext.startActivity(new Intent(mContext,cls));
    }


    @Override
    public void getUserInfo() {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Subscription subscription = OkGo.post(NetUrl.GET_USER_INFO+access_token)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        MineModel mineModel = GsonUtils.jsonToBean(s, MineModel.class);
                        MineModel.APIDATABean apidata = mineModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            MineModel.APIDATABean.RetBean ret = apidata.getRet();
                            mSettingsView.getUserInfoFinished(ret);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext,throwable.toString(),Toast.LENGTH_SHORT).show();
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }
}
