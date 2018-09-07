package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.SerachContract;
import com.xgkj.ilive.mvp.model.NewsSerachModel;
import com.xgkj.ilive.mvp.model.SerachModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

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
 * 日期: 2017/7/15 0015 12:10
 */

public class SerachPresenter implements SerachContract.Presenter {

    private SerachContract.View mSerachView;
    private Context mContext;

    public SerachPresenter(SerachContract.View view){
        mSerachView = view;
        mContext = (Context) mSerachView;
    }

    @Override
    public void getHotSerach() {
        Subscription subscription = OkGo.post(NetUrl.HOT_SERACH+ SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        SerachModel serachModel = GsonUtils.jsonToBean(s, SerachModel.class);
                        SerachModel.APIDATABean apidata = serachModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            List<String> hot_search = apidata.getHot_search();
                            mSerachView.getHotSerachFinished(hot_search);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable);
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void getStartHotSerach(String keyword, final int tag) {

        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String>  keyWordMap = new HashMap<>();
        keyWordMap.put("keyword",keyword);
        JSONObject jobj = GsonUtils.upJson(keyWordMap);

        Subscription subscription = OkGo.post(NetUrl.USER_HOT_SERACH+access_token)
                .upJson(jobj)
                .getCall(StringConvert.create(),RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        NewsSerachModel newsSerachModel = GsonUtils.jsonToBean(s, NewsSerachModel.class);
                        NewsSerachModel.APIDATABean apidata = newsSerachModel.getAPIDATA();
                        NewsSerachModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = apidata.getCode();
                        if (code == 200){
                            List<NewsSerachModel.APIDATABean.RetBean.NewsListBean> news_list = ret.getNews_list();
                            List<NewsSerachModel.APIDATABean.RetBean.VideoListBean> video_list = ret.getVideo_list();
                            List<NewsSerachModel.APIDATABean.RetBean.LiveListBean> live_list = ret.getLive_list();

                            if (tag == 1) {
                                mSerachView.getStartHotSerachView(news_list);
                            }else if (tag == 2){
                                mSerachView.getStartVideoView(video_list);
                            }else if (tag == 3){
                                mSerachView.getStartLiveView(live_list);
                            }
                        }else if (code == 404){
                            String msg = apidata.getMsg();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable);
                        Toast.makeText(mContext,throwable.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
