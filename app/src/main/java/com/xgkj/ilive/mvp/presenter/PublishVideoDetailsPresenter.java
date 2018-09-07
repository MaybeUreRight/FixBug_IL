package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.netease.neliveplayer.NELivePlayer;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.PublishVideoDetailsContract;
import com.xgkj.ilive.mvp.model.AdvanceLiveModel;
import com.xgkj.ilive.mvp.model.CommentModel;
import com.xgkj.ilive.mvp.model.HelpModel;
import com.xgkj.ilive.mvp.model.PublishVideoDetailsModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.player.media.NEVideoView;
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
 * 日期: 2017/7/20 0020 11:17
 */

public class PublishVideoDetailsPresenter implements PublishVideoDetailsContract.Presenter {

    private PublishVideoDetailsContract.View mPulishViewoDetailsView;
    private Context mContext;

    public PublishVideoDetailsPresenter(PublishVideoDetailsContract.View view){
        mPulishViewoDetailsView = (PublishVideoDetailsContract.View) view;
        mContext = (Context) mPulishViewoDetailsView;
    }

    @Override
    public void startTransmit(final String cid, String type, final String title, final String pic, final String status_content, final ShareBoardConfig shareBoardConfig, final ShareAction shareAction ) {
        Map<String,String> transmitMap = new HashMap<>();
        transmitMap.put("cid",cid);
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
                            UMVideo umvideo = null;
                            if ("点播".equals(status_content)){
                                umvideo = new UMVideo("http://api.devtool6.com/izhibo/index.html?cid=" + cid + "&type=" + 2);
                            }else if ("直播".equals(status_content) || "回放".equals(status_content)){
                                umvideo = new UMVideo("http://api.devtool6.com/izhibo/index.html?cid=" + cid + "&type=" + 1);
                            }else if ("预告".equals(status_content)){
                                umvideo = new UMVideo("http://api.devtool6.com/izhibo/index.html?cid=" + cid + "&type=" + 3);
                            }

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
//                        Log.e("sendMessageContent",s);
//                       // Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
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
                        LogUtils.e(s);
                        QueryChatMessageModel queryChatMessageModel = GsonUtils.jsonToBean(s, QueryChatMessageModel.class);
                        QueryChatMessageModel.APIDATABean apidata = queryChatMessageModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            List<QueryChatMessageModel.APIDATABean.RetBean> ret = apidata.getRet();
                            mPulishViewoDetailsView.queryChatMessageFinished(ret);
                        }
                        Log.e("queryChatMessage",s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable.toString());
                    }
                });
    }

    @Override
    public void getVideoDetails(String cid) {
        Map<String,Integer> videoDetails = new HashMap<>();
        videoDetails.put("cid",Integer.parseInt(cid));

        JSONObject jobj = GsonUtils.upIntJson(videoDetails);

        Subscription subscription = OkGo.post(NetUrl.VIDEO_LIST_DETAILS_URL)
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
                        Log.e("getVideoDetails",s);
                        PublishVideoDetailsModel publishVideoDetailsModel = GsonUtils.jsonToBean(s, PublishVideoDetailsModel.class);
                        PublishVideoDetailsModel.APIDATABean apidata = publishVideoDetailsModel.getAPIDATA();
                        PublishVideoDetailsModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200){
                            PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list = ret.getList();
                            mPulishViewoDetailsView.getVideoDetailsFinished(list);
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
    public void initNEVideoView() {
        NEVideoView neVideoView = mPulishViewoDetailsView.getNEVideoView();
        //点播抗振动
        neVideoView.setBufferStrategy(NELivePlayer.NELPANTIJITTER);
        neVideoView.setHardwareDecoder(true);
        neVideoView.setEnableBackgroundPlay(true);
        neVideoView.setMediaType("videoondemand");
    }

    @Override
    public void startClickLike(final String cid, final String type) {
        Map<String,String> clickMap = new HashMap<>();
        clickMap.put("cid",cid);
        clickMap.put("type",type);

        JSONObject jobj = GsonUtils.upJson(clickMap);

        Log.e("startClickLike",jobj+"");
        Subscription subscription = OkGo.post(NetUrl.LIKE_URL+SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
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
                        Log.e("startClickLike",s);
                        HelpModel helpModel = GsonUtils.jsonToBean(s, HelpModel.class);
                        HelpModel.APIDATABean apidata = helpModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            getLikeAfterCount(cid,type);
                            Toast.makeText(mContext, "点赞成功!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public void getAdvanceLiveDetails(String cid) {
        Map<String, Integer> videoDetails = new HashMap<>();
        videoDetails.put("cid", Integer.parseInt(cid));

        JSONObject jobj = GsonUtils.upIntJson(videoDetails);

        Subscription subscri = OkGo.post(NetUrl.GET_LIVE_DETAILS+SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
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
                        AdvanceLiveModel advanceLiveModel = GsonUtils.jsonToBean(s, AdvanceLiveModel.class);
                        AdvanceLiveModel.APIDATABean apidata = advanceLiveModel.getAPIDATA();
                        AdvanceLiveModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200){
                            AdvanceLiveModel.APIDATABean.RetBean.ListBean list = ret.getList();
                            mPulishViewoDetailsView.getAdvanceLiveDetailsFinished(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext,throwable);
                    }
                });
    }

    private   void getLikeAfterCount( String cid,String type){

        Map<String, Integer> videoDetails = new HashMap<>();
        videoDetails.put("cid", Integer.parseInt(cid));

        JSONObject jobj = GsonUtils.upIntJson(videoDetails);

        if ("3".equals(type)){
            Subscription subscri = OkGo.post(NetUrl.GET_LIVE_DETAILS+SharedPreferencesUtil.getAccessToken(mContext,"access_token"))
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
                            AdvanceLiveModel advanceLiveModel = GsonUtils.jsonToBean(s, AdvanceLiveModel.class);
                            AdvanceLiveModel.APIDATABean apidata = advanceLiveModel.getAPIDATA();
                            AdvanceLiveModel.APIDATABean.RetBean ret = apidata.getRet();
                            int code = ret.getCode();
                            if (code == 200){
                                AdvanceLiveModel.APIDATABean.RetBean.ListBean list = ret.getList();
                                mPulishViewoDetailsView.getLikeAnvanceLiveFinished(list);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            MobclickAgent.reportError(mContext,throwable);
                        }
                    });
        }else {
            Subscription subscription = OkGo.post(NetUrl.VIDEO_LIST_DETAILS_URL)
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
                            Log.e("getVideoDetails", s);
                            PublishVideoDetailsModel publishVideoDetailsModel = GsonUtils.jsonToBean(s, PublishVideoDetailsModel.class);
                            PublishVideoDetailsModel.APIDATABean apidata = publishVideoDetailsModel.getAPIDATA();
                            PublishVideoDetailsModel.APIDATABean.RetBean ret = apidata.getRet();
                            int code = ret.getCode();
                            if (code == 200) {
                                PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list = ret.getList();
                                mPulishViewoDetailsView.getLikeDetailsFinished(list);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Toast.makeText(mContext, throwable.toString(), Toast.LENGTH_SHORT).show();
                            MobclickAgent.reportError(mContext, throwable.toString());
                        }
                    });
        }
    }
}
