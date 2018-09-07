package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.xgkj.ilive.mvp.contract.LiveStreamingContract;
import com.xgkj.ilive.mvp.model.CommentModel;
import com.xgkj.ilive.mvp.model.HelpModel;
import com.xgkj.ilive.mvp.model.JoinModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
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
 * 日期: 2017/8/6 0006 15:45
 */

public class LiveStreamingPresenter implements LiveStreamingContract.Presenter {

    private LiveStreamingContract.View mLiveStreamView;
    private Context mContext;


    public LiveStreamingPresenter(LiveStreamingContract.View view){
        mLiveStreamView = view;
        mContext = (Context) mLiveStreamView;
    }


    @Override
    public void joinPeople(String id) {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String> joinMap = new HashMap<>();
        joinMap.put("cid",id);

        JSONObject jobj = GsonUtils.upJson(joinMap);

        Subscription subscription = OkGo.post(NetUrl.GET_LIVE_DETAILS+access_token)
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
                        JoinModel joinModel = GsonUtils.jsonToBean(s, JoinModel.class);
                        JoinModel.APIDATABean apidata = joinModel.getAPIDATA();
                        JoinModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200){
                            mLiveStreamView.getJoinPeopleFinish(ret);
                        }else {
                            String msg = ret.getMsg();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void sendMessageContent(String cid, String content) {
        //发送消息
        Map<String,String> sendMap = new HashMap<>();
        sendMap.put("vid",cid);
        sendMap.put("content",content);

        JSONObject jobj = GsonUtils.upJson(sendMap);
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Log.e("JSONObject",jobj+"\naccess_token===="+access_token);
        Subscription subscription = OkGo.post(NetUrl.PUBLISH_CHAT_BROWSE+access_token)
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
                        CommentModel commentModel = GsonUtils.jsonToBean(s, CommentModel.class);
                        CommentModel.APIDATABean apidata = commentModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 201){
                            Toast.makeText(mContext, "发送消息失败!", Toast.LENGTH_SHORT).show();
                        }else if (code == 200){
                            Toast.makeText(mContext, "发送消息成功!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    @Override
    public void queryChatMessage(String cid) {
        Map<String,String> queryChatMap = new HashMap<>();
        queryChatMap.put("vid",cid);
        JSONObject jobj = GsonUtils.upJson(queryChatMap);

        Subscription subscription = OkGo.post(NetUrl.QUERY_CHAT_CONTENT)
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
                        QueryChatMessageModel queryChatMessageModel = GsonUtils.jsonToBean(s, QueryChatMessageModel.class);
                        QueryChatMessageModel.APIDATABean apidata = queryChatMessageModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            List<QueryChatMessageModel.APIDATABean.RetBean> ret = apidata.getRet();
                            mLiveStreamView.queryChatMessageFinished(ret);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public void sharedLive(final String uid, final String type, final ShareAction shareAction, final ShareBoardConfig shareBoardConfig, final String title, final String pic) {
        Map<String,String> transmitMap = new HashMap<>();
        transmitMap.put("cid",uid);
        transmitMap.put("type",type);

        JSONObject jobj = GsonUtils.upJson(transmitMap);
        Subscription subscription = OkGo.post(NetUrl.TRANSMIT)
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
                        HelpModel helpModel = GsonUtils.jsonToBean(s, HelpModel.class);
                        HelpModel.APIDATABean apidata = helpModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            UMVideo umvideo = new UMVideo("http://api.devtool6.com/izhibo/index.html?cid=" + uid + "&type=" + type);
                            umvideo.setTitle(title);
                            UMImage umImage = new UMImage(mContext,pic);
                            umvideo.setThumb(umImage);

                            shareAction.withMedia(umvideo).open(shareBoardConfig);
                        }else if (code == 404){
                            String msg = apidata.getMsg();
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void getLiveDetails(String cid) {
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String> liveDetailsMap = new HashMap<>();
        liveDetailsMap.put("cid",cid);
        JSONObject jobj = GsonUtils.upJson(liveDetailsMap);

        Subscription subscription = OkGo.post(NetUrl.GET_LIVE_DETAILS+access_token)
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
                        Log.e("getLiveDetails",s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable);
                    }
                });


    }
}
