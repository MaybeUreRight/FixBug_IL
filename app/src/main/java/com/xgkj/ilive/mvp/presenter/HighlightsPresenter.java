package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.HighlightsContract;
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
 * 日期: 2017/8/17 0017 14:16
 */

public class HighlightsPresenter implements HighlightsContract.Presenter {

    private HighlightsContract.View mHighlightsView;
    private Context mContext;

    public HighlightsPresenter(HighlightsContract.View view){
        mHighlightsView = view;
        mContext = (Context) mHighlightsView;
    }

    @Override
    public void getHighlightsData(int pnum) {
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
                            mHighlightsView.getHotDataFinish(list);
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
    public void getLoadMoreHighlightsData(int pnum) {
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
                            mHighlightsView.getLoadMoreHotDataFinish(list);
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
