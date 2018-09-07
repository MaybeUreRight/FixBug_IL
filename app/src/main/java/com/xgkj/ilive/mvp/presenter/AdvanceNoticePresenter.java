package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.AdvanceNoticeContract;
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
 * 日期: 2017/7/18 0018 15:10
 */

public class AdvanceNoticePresenter implements AdvanceNoticeContract.Presenter {

    private AdvanceNoticeContract.View mAdvanceNoticeView;
    private Context mContext;

    public AdvanceNoticePresenter(AdvanceNoticeContract.View view,Context context){
        mAdvanceNoticeView = view;
        mContext = context;
    }

    @Override
    public void getHotData(int get_video, int type, int records, int pnum) {
        Map<String,Integer> hotMap = new HashMap<>();
        hotMap.put("get_video",get_video);
        hotMap.put("type",type);
        hotMap.put("records",records);
        hotMap.put("pnum",pnum);

        JSONObject jobj = GsonUtils.upIntJson(hotMap);
        Log.e("getHotData",jobj +"");
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

                        LogUtils.e("预告"+s);
                        BestNewModel hotModel = GsonUtils.jsonToBean(s, BestNewModel.class);
                        BestNewModel.APIDATABean apidata = hotModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code== 200){
                            BestNewModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BestNewModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            mAdvanceNoticeView.getHotDataFinish(list);
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
    public void getLoadMoreHotData(int get_video, int type, int records, int pnum) {
        Map<String,Integer> hotMap = new HashMap<>();
        hotMap.put("get_video",get_video);
        hotMap.put("type",type);
        hotMap.put("records",records);
        hotMap.put("pnum",pnum);

        JSONObject jobj = GsonUtils.upIntJson(hotMap);
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
                        BestNewModel hotModel = GsonUtils.jsonToBean(s, BestNewModel.class);
                        BestNewModel.APIDATABean apidata = hotModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code== 200){
                            BestNewModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BestNewModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            mAdvanceNoticeView.getLoadMoreHotDataFinish(list);
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
