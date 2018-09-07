package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.IndustryContract;
import com.xgkj.ilive.mvp.model.IndustryModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/8/10 0010 10:53
 */

public class IndustryPresenter implements IndustryContract.Presenter {

    private IndustryContract.View mIndustryView;
    private Context mContext;

    public IndustryPresenter(IndustryContract.View view){
        mIndustryView = view;
        mContext = (Context) mIndustryView;
    }
    @Override
    public void getIndustryList() {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Subscription subscription = OkGo.post(NetUrl.INDUSTRY_URL+access_token)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        IndustryModel industryModel = GsonUtils.jsonToBean(s, IndustryModel.class);
                        IndustryModel.APIDATABean apidata = industryModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            List<IndustryModel.APIDATABean.ListBean> list = apidata.getList();
                            mIndustryView.getIndustryFinish(list);
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
