package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.HotInformationContract;
import com.xgkj.ilive.mvp.model.PublishInformationModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/8/17 0017 11:42
 */

public class HotInformationPresenter implements HotInformationContract.Presenter {
    private HotInformationContract.View mHotInfoView;
    private Context mContext;

    public HotInformationPresenter(HotInformationContract.View view){
        mHotInfoView = view;
        mContext = (Context) mHotInfoView;
    }


    @Override
    public void getInfoData(int pnum) {
        Map<String,Integer> infoMap = new HashMap<>();
        infoMap.put("type",1);
        infoMap.put("records",10);
        infoMap.put("pnum",pnum);

        JSONObject jobj = GsonUtils.upIntJson(infoMap);

        Subscription subscription = OkGo.post(NetUrl.INFORMATION_URL)
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
                        PublishInformationModel publishInformationModel = GsonUtils.jsonToBean(s, PublishInformationModel.class);
                        PublishInformationModel.APIDATABean apidata = publishInformationModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            PublishInformationModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<PublishInformationModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            mHotInfoView.getInfomationListFinished(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }

    @Override
    public void getLoadInfoData(int pnum) {
        Map<String,Integer> infoMap = new HashMap<>();
        infoMap.put("type",1);
        infoMap.put("records",10);
        infoMap.put("pnum",pnum);

        JSONObject jobj = GsonUtils.upIntJson(infoMap);

        Subscription subscription = OkGo.post(NetUrl.INFORMATION_URL)
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
                        PublishInformationModel publishInformationModel = GsonUtils.jsonToBean(s, PublishInformationModel.class);
                        PublishInformationModel.APIDATABean apidata = publishInformationModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            PublishInformationModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<PublishInformationModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            mHotInfoView.getLoadInfomationList(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }
}
