package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.LiveActivity;
import com.xgkj.ilive.activity.LiveStreamingActivity;
import com.xgkj.ilive.mvp.contract.LiveContract;
import com.xgkj.ilive.mvp.model.LiveModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/8/6 0006 09:55
 */

public class LivePresenter implements LiveContract.Presenter {

    private LiveContract.View mLiveView;
    private Context mContext;

    public LivePresenter(LiveContract.View view){
        mLiveView = view;
        mContext = (Context) mLiveView;
    }

    @Override
    public void createLive(final String photoData, final String activityTitle, int isLook, final String activityIntroduced, String lookWayValue) {
        if (TextUtils.isEmpty(photoData)){
            Toast.makeText(mContext, "上传封面不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(activityTitle) || "未设置".equals(activityTitle)){
            Toast.makeText(mContext, "活动标题不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("未设置".equals(lookWayValue) || TextUtils.isEmpty(lookWayValue)){
            Toast.makeText(mContext, "谁可以看不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(activityIntroduced) || "未设置".equals(activityIntroduced)){
            Toast.makeText(mContext, "活动介绍不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject netJobj = new JSONObject();
        try {
            netJobj.put("name",activityTitle);
            netJobj.put("type",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject liveJobj = new JSONObject();
        try {
            liveJobj.put("live_type",1);
            liveJobj.put("pic",photoData);
            liveJobj.put("title",activityTitle);
            liveJobj.put("type",isLook);
            if (isLook == 1 || isLook == 2){
                liveJobj.put("type_value","");
            }else if (isLook == 3){
                liveJobj.put("type_value",lookWayValue);
            }

            liveJobj.put("introduce","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jobj2 = new JSONObject();
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("net_easy",netJobj);
            jobj.put("ilive",liveJobj);
            jobj2.put("APIDATA",jobj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Log.e("access_token",access_token);
//        Log.e("createLive",jobj2+"");
        Subscription subscription = OkGo.post(NetUrl.CREATE_LIVE_CHANNELS+ SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
                .upJson(jobj2)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("createLive","******************************"+s);
                        LiveModel liveModel = GsonUtils.jsonToBean(s, LiveModel.class);
                        LiveModel.APIDATABean apidata = liveModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            LiveModel.APIDATABean.RetBean ret = apidata.getRet();
                            String id = ret.getId();
                            String cid = ret.getCid();
                            String pushUrl = ret.getPushUrl();
                            String rtmpPullUrl = ret.getRtmpPullUrl();
                            String name = ret.getName();
                            Intent intent = new Intent(mContext, LiveStreamingActivity.class);
                            intent.putExtra("cid",cid);
                            intent.putExtra("id",id);
                            intent.putExtra("pushUrl",pushUrl);
                            intent.putExtra("rtmpPullUrl",rtmpPullUrl);
                            intent.putExtra("name",name);
                            SharedPreferencesUtil.savePhotoData(mContext,"photoData",photoData);
                            mContext.startActivity(intent);
                            LiveActivity liveActivity= (LiveActivity) mContext;
                            liveActivity.finish();
                        }else {
                            String msg = apidata.getMsg();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
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
