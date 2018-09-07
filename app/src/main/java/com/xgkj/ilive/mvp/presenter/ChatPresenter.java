package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.mvp.contract.ChatContract;
import com.xgkj.ilive.mvp.model.ChatModel;
import com.xgkj.ilive.mvp.model.CommentModel;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.ChatRoomMemberCache;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/7/27 0027 10:38
 */

public class ChatPresenter implements ChatContract.Presenter {

    private ChatContract.View mChatView;
    private Context mContext;
    private List<ChatRoomMember> items = new ArrayList<>();

    private static final int LIMIT = 10;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    private String pic;

    public ChatPresenter(ChatContract.View view, Context context) {
        mChatView = view;
        mContext = context;
    }


    @Override
    public void enterChatRoom(final String room_id) {
        EnterChatRoomData data = new EnterChatRoomData(room_id);

        NIMClient.getService(ChatRoomService.class).enterChatRoom(data)
                .setCallback(new RequestCallback<EnterChatRoomResultData>() {
                    @Override
                    public void onSuccess(EnterChatRoomResultData param) {
                        getChatRoomMember(room_id);
                        Toast.makeText(mContext, "进入聊天室成功", Toast.LENGTH_SHORT).show();
                        ChatRoomMember member = param.getMember();
                        String nick = member.getNick();
                        Log.e("nick", nick);
                        member.setMemberType(MemberType.GUEST);
                        ChatRoomMemberCache.getInstance().registerObservers(true);
                        String account = member.getAccount();
                        String accid = SharedPreferencesUtil.getLoginStatus(mContext, "accid");
                        if (accid.equals(account)) {
                            member.setAvatar(getPic());
                        }
                    }

                    @Override
                    public void onFailed(int code) {
                        Toast.makeText(mContext, "进入聊天室失败" + code, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onException(Throwable exception) {
                        Toast.makeText(mContext, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void pullMessageHistory(String roomId) {
        //历史消息的集合
        Map<String, String> historyMap = new HashMap<>();
        historyMap.put("roomid", roomId);

        JSONObject jobj = GsonUtils.upJson(historyMap);

        Subscription subscription = OkGo.post(NetUrl.CHAT_ROOM_HISTORY)
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
                        ChatModel chatModel = GsonUtils.jsonToBean(s, ChatModel.class);
                        ChatModel.APIDATABean apidata = chatModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200) {
                            ChatModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<ChatModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public void getChatRoomMember(final String roomId) {

        NIMClient.getService(ChatRoomService.class)
                .fetchRoomMembers(roomId, MemberQueryType.GUEST, new ChatRoomMember().getUpdateTime(), LIMIT)
                .setCallback(new RequestCallback<List<ChatRoomMember>>() {
                    @Override
                    public void onSuccess(List<ChatRoomMember> chatRoomMembers) {
                        Log.e("ChatRoomMemberCache", chatRoomMembers.size() + "");
                        ChatRoomMemberCache instance = ChatRoomMemberCache.getInstance();
                        instance.saveMembers(chatRoomMembers);
                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                });
    }

    @Override
    public void getUserInfo() {

        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");

        Subscription subscription = OkGo.post(NetUrl.GET_USER_INFO + access_token)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        MineModel mineModel = GsonUtils.jsonToBean(s, MineModel.class);
                        MineModel.APIDATABean apidata = mineModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200) {
                            MineModel.APIDATABean.RetBean ret = apidata.getRet();
                            String pic = ret.getPic();
                            setPic(pic);
                            mChatView.getUserInfoFinished(ret);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    /**
     * 发送消息内容
     *
     * @param cid
     */
    @Override
    public void sendMessageContent(final String cid, String content) {

        //发送消息
        Map<String, String> sendMap = new HashMap<>();
        sendMap.put("vid", cid);
        sendMap.put("content", content);

        JSONObject jobj = GsonUtils.upJson(sendMap);
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Log.e("JSONObject", jobj + "\naccess_token====" + access_token);
        Subscription subscription = OkGo.post(NetUrl.PUBLISH_CHAT_BROWSE + access_token)
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
                        CommentModel commentModel = GsonUtils.jsonToBean(s, CommentModel.class);
                        CommentModel.APIDATABean apidata = commentModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 201) {
                            Toast.makeText(mContext, "发送消息失败!", Toast.LENGTH_SHORT).show();
                        } else if (code == 200) {
                            Toast.makeText(mContext, "发送消息成功!", Toast.LENGTH_SHORT).show();
                        }
//                        Log.e("sendMessageContent",s);
//                       // Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext, throwable.toString());
                    }
                });

    }

    /**
     * 查询聊天消息
     *
     * @param cid 传入视频的id
     */
    @Override
    public void queryChatMessage(String cid) {

        Map<String, String> queryChatMap = new HashMap<>();
        queryChatMap.put("vid", cid);
        JSONObject jobj = GsonUtils.upJson(queryChatMap);

        Subscription subscription = OkGo.post(NetUrl.QUERY_CHAT_CONTENT)
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
                        QueryChatMessageModel queryChatMessageModel = GsonUtils.jsonToBean(s, QueryChatMessageModel.class);
                        QueryChatMessageModel.APIDATABean apidata = queryChatMessageModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200) {
                            List<QueryChatMessageModel.APIDATABean.RetBean> ret = apidata.getRet();
                            mChatView.queryChatMessageFinished(ret);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(mContext, throwable.toString());
                    }
                });
    }

}
