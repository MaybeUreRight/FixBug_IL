package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.xgkj.ilive.mvp.contract.TaUserInfoContact;
import com.xgkj.ilive.mvp.contract.TaUserInfoContact.Presenter;
import com.xgkj.ilive.mvp.model.JoinModel;
import com.xgkj.ilive.mvp.model.TaUserModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by admin on 2017/11/2.
 */

public class TaUserInfoPresenter implements Presenter {

    private TaUserInfoContact.View mPersionalProfileView;
    private Context mContext;

    public TaUserInfoPresenter(TaUserInfoContact.View view){
        mPersionalProfileView = view;
        mContext = (Context) mPersionalProfileView;
    }

    @Override
    public void getUserInfo(int to_uid, final String type) {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        JSONObject root = new JSONObject();
        JSONObject jobj = new JSONObject();
        try {
            root.put("to_uid",to_uid);
            root.put("type",type);
            jobj.put("APIDATA",root);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Subscription subscription = OkGo.post(NetUrl.OTHERVIEW+access_token)
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
                        Log.d("summer",s);
                        TaUserModel taUserModel = GsonUtils.jsonToBean(s, TaUserModel.class);
                        TaUserModel.APIDATABean apidata = taUserModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            if (null != apidata.getList() && apidata.getList().size()>0){
                                mPersionalProfileView.getUserInfoFinished(apidata,type);
                            }
                        }else {
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("joinPeople",throwable.toString());
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
