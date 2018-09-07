package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.BestNewContract;
import com.xgkj.ilive.mvp.model.BestNewModel;
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
 * 日期: 2017/7/18 0018 13:45
 */

public class BestNewPresenter implements BestNewContract.Presenter {

    private BestNewContract.View mBestNewView;
    private Context mContext;

    public BestNewPresenter(BestNewContract.View view,Context context){
        mBestNewView = view;
        mContext = context;
    }


    @Override
    public void getBestNewData() {
        Map<String,Integer> hotMap = new HashMap<>();
        hotMap.put("get_video",0);
        hotMap.put("type",1);
        hotMap.put("records",10);
        hotMap.put("pnum",1);

        JSONObject jobj =  GsonUtils.upIntJson(hotMap);
        Subscription subscription = OkGo.post(NetUrl.GET_LIVE_LIST)
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
                        BestNewModel bestNewModel = GsonUtils.jsonToBean(s, BestNewModel.class);
                        BestNewModel.APIDATABean apidata = bestNewModel.getAPIDATA();

                        int code = apidata.getCode();
                        if (code == 200) {
                            BestNewModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BestNewModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            mBestNewView.getHotDataFinish(list);
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
    public void getLoadMoreBestNewData(int pnum) {
        Map<String,Integer> hotMap = new HashMap<>();
        hotMap.put("get_video",0);
        hotMap.put("type",1);
        hotMap.put("records",10);
        hotMap.put("pnum",pnum);

        JSONObject jobj =  GsonUtils.upIntJson(hotMap);
        Subscription subscription = OkGo.post(NetUrl.GET_LIVE_LIST)
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
                        BestNewModel bestNewModel = GsonUtils.jsonToBean(s, BestNewModel.class);
                        BestNewModel.APIDATABean apidata = bestNewModel.getAPIDATA();

                        int code = apidata.getCode();
                        if (code == 200) {
                            BestNewModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BestNewModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            mBestNewView.getLoadMoreHotDataFinish(list);
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
