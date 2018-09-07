package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.PublishVideoContract;
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
 * 日期: 2017/7/18 0018 16:06
 */

public class PublishVideoPresenter implements PublishVideoContract.Presenter {

    private PublishVideoContract.View mPulishVideoView;
    private Context mContext;
    private  List<PublishVideoModel.APIDATABean.RetBean.ListBean> publishList;

    public PublishVideoPresenter(PublishVideoContract.View view,Context context){
        mPulishVideoView = view;
        mContext = context;
    }

    @Override
    public void getVideoList(int type,int records,int pnum) {
        Map<String,Integer> videoMap = new HashMap<>();
        videoMap.put("type",type);
        videoMap.put("records",records);
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

                        LogUtils.e("点播"+s);
                        PublishVideoModel publishVideoModel = GsonUtils.jsonToBean(s, PublishVideoModel.class);
                        PublishVideoModel.APIDATABean apidata = publishVideoModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            PublishVideoModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<PublishVideoModel.APIDATABean.RetBean.ListBean> list =ret.getList();
//                            publishList.addAll(list);
//                            list.clear();
                            mPulishVideoView.getVideoListFininsh(list);
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
    public void getVideoLoadMore(int type, int records, int pnum) {
        Map<String,Integer> videoMap = new HashMap<>();
        videoMap.put("type",type);
        videoMap.put("records",records);
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
                            mPulishVideoView.getVideoLoadMore(list);
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
