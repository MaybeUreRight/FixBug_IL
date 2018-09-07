package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.HotVideoContract;
import com.xgkj.ilive.mvp.model.PublishVideoModel;
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
 * 日期: 2017/8/17 0017 10:37
 */

public class HotVideoPresenter implements HotVideoContract.Presenter {

    private HotVideoContract.View mHotVideoView;
    private Context mContext;

    public HotVideoPresenter(HotVideoContract.View view){
        mHotVideoView = view;
        mContext = (Context) mHotVideoView;
    }

    @Override
    public void getHotVideoList(int pnum) {
        Map<String,Integer> videoMap = new HashMap<>();
        videoMap.put("type",1);
        videoMap.put("records",10);
        videoMap.put("pnum",pnum);

        JSONObject jobj = GsonUtils.upIntJson(videoMap);

        Subscription subscription = OkGo.post(NetUrl.VIDEO_LIST_URL)
                .upJson(jobj)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mHotVideoView.getProgressWidget().setVisibility(View.VISIBLE);
                        mHotVideoView.getRecyclerviewWidget().setVisibility(View.GONE);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        PublishVideoModel publishVideoModel = GsonUtils.jsonToBean(s, PublishVideoModel.class);
                        PublishVideoModel.APIDATABean apidata = publishVideoModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            mHotVideoView.getProgressWidget().setVisibility(View.GONE);
                            mHotVideoView.getRecyclerviewWidget().setVisibility(View.VISIBLE);
                            PublishVideoModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<PublishVideoModel.APIDATABean.RetBean.ListBean> list =ret.getList();
                            mHotVideoView.getHotVideoListFininsh(list);
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
    public void getLoadHotVideoList(int pnum) {
        Map<String,Integer> videoMap = new HashMap<>();
        videoMap.put("type",1);
        videoMap.put("records",10);
        videoMap.put("pnum",pnum);

        JSONObject jobj = GsonUtils.upIntJson(videoMap);

        Subscription subscription = OkGo.post(NetUrl.VIDEO_LIST_URL)
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
                        PublishVideoModel publishVideoModel = GsonUtils.jsonToBean(s, PublishVideoModel.class);
                        PublishVideoModel.APIDATABean apidata = publishVideoModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            PublishVideoModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<PublishVideoModel.APIDATABean.RetBean.ListBean> list =ret.getList();
                            mHotVideoView.getLoadHotVideoListFininsh(list);
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
