package com.xgkj.ilive.mvp.presenter;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.HomeContract;
import com.xgkj.ilive.mvp.model.AdvertModel;
import com.xgkj.ilive.mvp.model.HomeModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 17:14
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;
    private Context mContext;
    private final String access_token;

    public HomePresenter(HomeContract.View view,Context context){
        mHomeView = view;
        mContext = context;
        access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
    }


    @Override
    public void getAdvertPic() {
        Subscription subscription = OkGo.post(NetUrl.GET_ADVERT_PIC)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                   public void call(String s) {
                        AdvertModel advertModel = GsonUtils.jsonToBean(s, AdvertModel.class);
                        AdvertModel.APIDATABean apidata = advertModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            AdvertModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<AdvertModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list!=null && list.size()>0) {
                                    mHomeView.getAdvertFinish(list);

                            }
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
    public void getHomeGroom() {
        Subscription subscription = OkGo.post(NetUrl.GET_HOME_GROOM)
                .getCall(StringConvert.create(),RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //Toast.makeText(mContext,s, Toast.LENGTH_SHORT).show();
                        //LogUtils.e(s);
                        HomeModel homeModel = GsonUtils.jsonToBean(s, HomeModel.class);
                        HomeModel.APIDATABean apidata = homeModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            if (apidata != null) {
                                    mHomeView.getGroomListItem(apidata);
                            }
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
