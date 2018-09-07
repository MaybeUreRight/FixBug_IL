package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.HelpActivity;
import com.xgkj.ilive.mvp.contract.HelpContract;
import com.xgkj.ilive.mvp.model.HelpModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/8/28 0028 10:23
 */

public class HelpPresenter implements HelpContract.Presenter {

    private HelpContract.View mHelpView;
    private Context mContext;

    public HelpPresenter(HelpContract.View view){
        mHelpView = view;
        mContext = (Context) mHelpView;
    }

    @Override
    public void commitSuggest(String content) {
        Map<String,String> contentMap = new HashMap<>();
        contentMap.put("content",content);
        JSONObject jobj = GsonUtils.upJson(contentMap);
        OkGo.post(NetUrl.COMPLAINTS_SUGGESTIONS+ SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
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
                        HelpModel helpModel = GsonUtils.jsonToBean(s, HelpModel.class);
                        HelpModel.APIDATABean apidata = helpModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            Toast.makeText(mContext, "投诉建议成功!", Toast.LENGTH_SHORT).show();
                            HelpActivity helpActivity= (HelpActivity) mContext;
                            helpActivity.finish();
                        }else {
                            Toast.makeText(mContext, apidata.getMsg(), Toast.LENGTH_SHORT).show();
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
}
