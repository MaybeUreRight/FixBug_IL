package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.activity.AnnounceInAdvanceActivity;
import com.xgkj.ilive.mvp.contract.AnnounceInAdvanceContract;
import com.xgkj.ilive.mvp.model.AnnounceInAdvanceModel;
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
 * 日期: 2017/8/3 0003 13:52
 */

public class AnnounceInAdvancePresenter implements AnnounceInAdvanceContract.Presenter {

    private AnnounceInAdvanceContract.View mAnnounceInAdvanceView;
    private Context mContext;

    public AnnounceInAdvancePresenter(AnnounceInAdvanceContract.View view){
        mAnnounceInAdvanceView = view;
        mContext = (Context) mAnnounceInAdvanceView;
    }


    @Override
    public void createAnnounceInAdvance(String photoData, String title, String activity_time, int type, String type_value) {
        if (TextUtils.isEmpty(photoData)){
            Toast.makeText(mContext, "上传封面不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject netJobj = new JSONObject();
        try {
            netJobj.put("name",title);
            netJobj.put("type",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject liveJobj = new JSONObject();
        try {
            liveJobj.put("live_type",2);
            liveJobj.put("pic",photoData);
            liveJobj.put("title",title);
            liveJobj.put("type",type);
            if (type == 1 || type == 2){
                liveJobj.put("type_value","");
            }else if (type == 3){
                liveJobj.put("type_value",type_value);
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

        Log.e("createAnnounce",jobj2+"");
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
                        AnnounceInAdvanceModel announceInAdvanceModel = GsonUtils.jsonToBean(s, AnnounceInAdvanceModel.class);
                        AnnounceInAdvanceModel.APIDATABean apidata = announceInAdvanceModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            Toast.makeText(mContext, "创建预告成功", Toast.LENGTH_SHORT).show();
                            AnnounceInAdvanceActivity announce= (AnnounceInAdvanceActivity) mContext;
                            announce.finish();
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
