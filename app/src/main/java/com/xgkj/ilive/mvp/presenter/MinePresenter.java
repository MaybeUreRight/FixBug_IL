package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.MineContract;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 10:52
 */

public class MinePresenter implements MineContract.Presenter {

    private MineContract.View mMineView;
    private Context mContext;

    public MinePresenter(MineContract.View view,Context context){
        mMineView = view;
        mContext = context;
    }

    @Override
    public <T> void startActivity(Class<T> cls) {
        Intent intent = new Intent(mContext, cls);
        mContext.startActivity(intent);
    }

    @Override
    public void getUserInfo() {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Subscription subscription = OkGo.post(NetUrl.GET_USER_INFO+access_token)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("getUserInfo",s);
                        MineModel mineModel = GsonUtils.jsonToBean(s, MineModel.class);
                        MineModel.APIDATABean apidata = mineModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            MineModel.APIDATABean.RetBean ret = apidata.getRet();
                            mMineView.getUserInfoFinished(ret);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext,throwable.toString(),Toast.LENGTH_SHORT).show();
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }

    @Override
    public void updateUserIcon(Intent data) {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");

        //进行图片处理
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        String photo_data = Base64.encodeToString(bytes, 0, bytes.length, Base64.DEFAULT);

        Map<String,String> iconMap = new HashMap<>();
        iconMap.put("pic",photo_data);

        JSONObject jobj = GsonUtils.upJson(iconMap);

        OkGo.post(NetUrl.UPLOAD_USERS_ICON+access_token)
                .upJson(jobj)
                .getCall(StringConvert.create(),RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }

    @Override
    public void updateLocalPicture(String photo) {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String> iconMap = new HashMap<>();
        iconMap.put("pic",photo);

        JSONObject jobj = GsonUtils.upJson(iconMap);
        OkGo.post(NetUrl.UPLOAD_USERS_ICON+access_token)
                .upJson(jobj)
                .getCall(StringConvert.create(),RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable.toString());
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public Bitmap getBitmap(String filePath, int destWidth, int destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        int samplesSize = 1;
        while (outHeight / samplesSize > destHeight || outWidth / samplesSize > destWidth) {
            samplesSize *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = samplesSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(filePath, options);
    }

    @Override
    public void getCompanyInfo( String company_id) {

        Map<String,String> company_info = new HashMap<>();
        company_info.put("id",company_id);
        JSONObject jobj = GsonUtils.upJson(company_info);


        Subscription subscription = OkGo.post(NetUrl.COMPANY_INFO+SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
                .upJson(jobj)
                .getCall(StringConvert.create(),RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
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
