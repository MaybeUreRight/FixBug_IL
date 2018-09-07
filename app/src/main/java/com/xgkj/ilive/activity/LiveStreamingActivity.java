package com.xgkj.ilive.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.netease.LSMediaCapture.lsLogUtil;
import com.netease.LSMediaCapture.lsMediaCapture;
import com.netease.LSMediaCapture.lsMessageHandler;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.vcloud.video.render.NeteaseView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.LiveStreamingAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.LiveStreamingContract;
import com.xgkj.ilive.mvp.model.JoinModel;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.model.PublishParam;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.mvp.presenter.LiveStreamingPresenter;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class LiveStreamingActivity extends BaseActivity implements LiveStreamingContract.View, lsMessageHandler {

    /**
     * 显示当前的网络速度
     */
    private static final int SHOW_SPEED = 1;

    /**
     * 参加的人数
     */
    private static final int SHOW_JOIN_PEOPLE = 2;
    @BindView(R.id.videoview)
    NeteaseView videoview;
    @BindView(R.id.start_live)
    Button start_live;
    @BindView(R.id.wifi_signal_status)
    ImageView wifi_signal_status;
    @BindView(R.id.signal_status)
    TextView signal_status;
    @BindView(R.id.live_time)
    Chronometer live_time;
    @BindView(R.id.live_join_people)
    TextView live_join_people;
    @BindView(R.id.start_microphone_btn)
    Button start_microphone_btn;
    @BindView(R.id.change_live_camera)
    Button change_live_camera;
    @BindView(R.id.stop_live)
    TextView stop_live;
    @BindView(R.id.chatroom_recyclerview)
    RecyclerView chatroom_recyclerview;
    @BindView(R.id.et_chatroom_content)
    EditText et_chatroom_content;
    @BindView(R.id.start_shared)
    Button start_shared;
    @BindView(R.id.start_close_chat)
    Button start_close_chat;
    @BindView(R.id.chat_room_linear)
    AutoLinearLayout chat_room_linear;
    @BindView(R.id.live_root_layout)
    AutoRelativeLayout live_root_layout;

    private lsMediaCapture mLSMediaCapture;
    private lsMediaCapture.LiveStreamingPara mLiveStreamingPara;
    private boolean m_liveStreamingOn = false;

    private PublishParam publishParam;
    private long clickTime = 0L;

    private boolean m_tryToStopLivestreaming = false;

    private LiveStreamingPresenter liveStreamingPresenter;
    private String cid;
    private String pushUrl;
    private String roomId;
    private String rtmpPullUrl;
    private LinkedList<ChatRoomMessage> items;

    private static final int MESSAGE_CAPACITY = 500;
    private long mLastAudioProcessErrorAlertTime = 0;
    private static final int UPDATE_CHAT_DATA = 3;

    //handler消息处理
    private Handler liveStreamHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case SHOW_SPEED:
                    WifiManager wifi_service = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                    WifiInfo wifiInfo = wifi_service.getConnectionInfo();
                    int linkSpeed = wifiInfo.getLinkSpeed();
//                    //1.得到网络速度
//                    String netSpeed = DateUtil.getNetSpeed(LiveStreamingActivity.this);
                    int rssi = wifiInfo.getRssi();
                    Log.e("rssi", rssi + "");
                    if (rssi < 0 && rssi >= -50) {
                        wifi_signal_status.setImageResource(R.drawable.wifi_strong);
                    } else if (rssi < -50 && rssi >= -150) {
                        wifi_signal_status.setImageResource(R.drawable.wifi_weak);
                    } else if (rssi >= -150) {
                        wifi_signal_status.setImageResource(R.drawable.wifi_no_signal);
                    }
                    //显示网速
                    signal_status.setText(linkSpeed + "kb/s");

                    //2.每两秒更新一次
                    liveStreamHandler.removeMessages(SHOW_SPEED);
                    liveStreamHandler.sendEmptyMessageDelayed(SHOW_SPEED, 1000);
                    break;
                case SHOW_JOIN_PEOPLE:
                    liveStreamingPresenter.joinPeople(cid);

                    liveStreamHandler.removeMessages(SHOW_JOIN_PEOPLE);
                    liveStreamHandler.sendEmptyMessageDelayed(SHOW_JOIN_PEOPLE, 1000);
                    break;

                //进行更新评论消息
                case UPDATE_CHAT_DATA:
                    liveStreamingPresenter.queryChatMessage(id);

                    liveStreamHandler.removeMessages(UPDATE_CHAT_DATA);
                    liveStreamHandler.sendEmptyMessageDelayed(UPDATE_CHAT_DATA, 1000);
                    break;
            }
        }
    };
    private String uid;
    private String pictures;
    private String name;
    private long mLastVideoProcessErrorAlertTime = 0;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private Intent intent;
    private String id;
    private LiveStreamingAdapter liveStreamingAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_streaming;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        try {
            AppManager.getAppManager().addActivity(this);
            MobclickAgent.setCatchUncaughtExceptions(true);

            //应用运行时，保持屏幕高亮，不锁屏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.screenBrightness = 0.7f;
            getWindow().setAttributes(params);

            liveStreamingPresenter = new LiveStreamingPresenter(this);


            //开始更新网络速度
            liveStreamHandler.sendEmptyMessage(SHOW_SPEED);


            //设置计时的格式
            int hour = (int) ((SystemClock.elapsedRealtime() - live_time.getBase()) / 1000 / 60);
            live_time.setFormat("0" + String.valueOf(hour) + ":%s");
            items = new LinkedList<>();
            //获取用户信息
            getUserInfo();

            publishParam = new PublishParam();

            //对其他播放进行处理
            pauseOtherAppSoucePlay();
        } catch (Exception e) {
            Log.e("livestreamingactivity", e.toString());
        }

    }

    /**
     * 对其他音频的处理
     */
    private void pauseOtherAppSoucePlay() {
        if (requestTheAudioFocus() == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            intent = getIntent();
            cid = intent.getStringExtra("cid");
            id = intent.getStringExtra("id");
            pushUrl = intent.getStringExtra("pushUrl");
            name = intent.getStringExtra("name");

            rtmpPullUrl = intent.getStringExtra("rtmpPullUrl");

            m_liveStreamingOn = false;
            m_tryToStopLivestreaming = false;

            liveStreamingAdapter = new LiveStreamingAdapter();
            chatroom_recyclerview.setLayoutManager(new LinearLayoutManager(LiveStreamingActivity.this, LinearLayoutManager.VERTICAL, false));

            //1、创建直播实例
            lsMediaCapture.LsMediaCapturePara lsMediaCapturePara = new lsMediaCapture.LsMediaCapturePara();
            lsMediaCapturePara.setContext(this); //设置SDK上下文（建议使用ApplicationContext）
            lsMediaCapturePara.setMessageHandler(this); //设置SDK消息回调
            lsMediaCapturePara.setLogLevel(lsLogUtil.LogLevel.INFO); //日志级别
            lsMediaCapturePara.setUploadLog(publishParam.uploadLog);//是否上传SDK日志
            mLSMediaCapture = new lsMediaCapture(lsMediaCapturePara);

            if (publishParam.useFilter) { //demo中默认设置为干净滤镜
                mLSMediaCapture.setBeautyLevel(5); //磨皮强度为5,共5档，0为关闭
                mLSMediaCapture.setFilterStrength(0.5f); //滤镜强度
                mLSMediaCapture.setFilterType(publishParam.filterType);
            }

            //2、设置直播参数
            mLiveStreamingPara = new lsMediaCapture.LiveStreamingPara();
            mLiveStreamingPara.setStreamType(publishParam.streamType); // 推流类型 AV、AUDIO、VIDEO
            mLiveStreamingPara.setFormatType(publishParam.formatType); // 推流格式 RTMP、MP4、RTMP_AND_MP4
            mLiveStreamingPara.setRecordPath(rtmpPullUrl);//formatType 为 MP4 或 RTMP_AND_MP4 时有效
            mLiveStreamingPara.setQosOn(publishParam.qosEnable);

            if (publishParam.streamType != lsMediaCapture.StreamType.AUDIO) {
                boolean mScale_16x9 = publishParam.isScale_16x9; //是否强制16:9
                lsMediaCapture.VideoQuality videoQuality = publishParam.videoQuality; //视频模板（SUPER_HIGH 1280*720、SUPER 960*540、HIGH 640*480、MEDIUM 480*360、LOW 352*288）
                mLSMediaCapture.startVideoPreview(videoview, publishParam.frontCamera, publishParam.useFilter, videoQuality, mScale_16x9);
            }

            //点击发送按键进行发送内容
            et_chatroom_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_SEND) {
                        String send_content = textView.getText().toString();

                        if (TextUtils.isEmpty(send_content)) {
                            Toast.makeText(LiveStreamingActivity.this, "发送内容不能为空", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        liveStreamingPresenter.sendMessageContent(id, send_content);
                        liveStreamHandler.sendEmptyMessage(UPDATE_CHAT_DATA);
                        et_chatroom_content.setText("");
                    }
                    return false;
                }
            });


            //来电监听
            incomingTelegram();
            //焦点获取成功，播放操作
            Log.e("pauseOtherAppSoucePlay", "焦点获取成功，播放操作");
        } else {
            //提示用户关闭其他音频再播放，不然用户以为是bug呢...
            Toast.makeText(this, "请检查是否有其它音频，将其它进行关闭!", Toast.LENGTH_SHORT).show();
            Log.e("pauseOtherAppSoucePlay", "提示用户关闭其他音频再播放，不然用户以为是bug呢");
        }
    }

    /**
     * 来电状态监听
     */
    private void incomingTelegram() {
        TelephonyManager mTelePhonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mTelePhonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                //电话挂断的处理
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e("CALL_STATE_IDLE", "*****************************************");
                    mLSMediaCapture.resumeVideoLiveStream();
                    break;
                //电话接听
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.e("CALL_STATE_IDLE", "##########################################");
                    mLSMediaCapture.pauseVideoLiveStream();
                    break;
                //响铃电话来电号码
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.e("TelephonyManager.CALL_STATE_RINGING", "响铃电话来电号码:" + incomingNumber);
                    break;
            }
        }
    };

    //zxzhong 请求音频焦点 设置监听
    private int requestTheAudioFocus() {
        if (Build.VERSION.SDK_INT < 8) {//Android 2.2开始(API8)才有音频焦点机制
            return 0;
        }
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
        if (mAudioFocusChangeListener == null) {
            mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {//监听器
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                            //播放操作
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            //暂停操作
                            break;
                        default:
                            break;
                    }
                }
            };
        }
        //下面两个常量参数试过很多 都无效，最终反编译了其他app才搞定，汗~
        int requestFocusResult = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        return requestFocusResult;
    }

    //zxzhong 暂停、播放完成或退到后台释放音频焦点
    private void releaseTheAudioFocus(AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener) {
        if (mAudioManager != null && mAudioFocusChangeListener != null) {
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }


    @Override
    public NeteaseView getNEVideoViewWidget() {
        if (videoview == null) {
            videoview = (NeteaseView) findViewById(R.id.nevideo_view);
        }
        return videoview;
    }

    @Override
    public void getJoinPeopleFinish(JoinModel.APIDATABean.RetBean ret) {
        JoinModel.APIDATABean.RetBean.ListBean list = ret.getList();
        String live_online_count = list.getLive_online_count();
        if ("".equals(live_online_count)) {
            live_join_people.setText("参与0人");
        } else {
            live_join_people.setText("参与" + live_online_count + "人");
        }
    }

    @Override
    public void queryChatMessageFinished(List<QueryChatMessageModel.APIDATABean.RetBean> ret) {
        if (ret != null && ret.size() > 0) {
            liveStreamingAdapter.setData(ret);
            chatroom_recyclerview.setAdapter(liveStreamingAdapter);
        }
    }

    private boolean flag = true;

    @OnClick({R.id.start_live, R.id.start_microphone_btn, R.id.change_live_camera, R.id.stop_live, R.id.start_close_chat, R.id.start_shared})
    public void onDownClick(View view) {
        switch (view.getId()) {
            //开始推流按钮
            case R.id.start_live:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        boolean liveStream = mLSMediaCapture.initLiveStream(mLiveStreamingPara, pushUrl);
                        if (liveStream) {
                            m_liveStreamingOn = true;
                            mLSMediaCapture.startLiveStreaming();
                        }
                    }
                }.start();
                SystemClock.sleep(1000);
                start_live.setVisibility(View.GONE);
                // 设置开始时间
                live_time.setBase(SystemClock.elapsedRealtime());
                // 开始计时
                live_time.start();
                liveStreamHandler.sendEmptyMessage(SHOW_JOIN_PEOPLE);

                //查询评论记录
                liveStreamingPresenter.queryChatMessage(cid);
                liveStreamHandler.sendEmptyMessage(UPDATE_CHAT_DATA);
                break;
            //开启或关闭麦克风
            case R.id.start_microphone_btn:

                if (flag) {
                    mLSMediaCapture.pauseAudioLiveStream();
                    start_microphone_btn.setBackgroundResource(R.drawable.close_microphone);
                    flag = false;
                } else {
                    mLSMediaCapture.resumeAudioLiveStream();
                    flag = true;
                    start_microphone_btn.setBackgroundResource(R.drawable.start_microphone);
                }

                break;
            //切换前后摄像头
            case R.id.change_live_camera:
                if (mLSMediaCapture != null) {
                    mLSMediaCapture.switchCamera();
                }
                break;
            //停止直播
            case R.id.stop_live:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view1 = View.inflate(this, R.layout.stop_live_dialog, null);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setView(view1);
                TextView live_stop = (TextView) view1.findViewById(R.id.live_stop);
                TextView live_continue = (TextView) view1.findViewById(R.id.live_continue);
                //进行停止直播
                live_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stopAV();
                        alertDialog.dismiss();
                        LiveStreamingActivity.this.finish();
                    }
                });
                //继续直播
                live_continue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LiveStreamingActivity.this, "进行继续直播", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
            //开启或禁止聊天
            case R.id.start_close_chat:
                int visibility = chat_room_linear.getVisibility();
                if (visibility == View.VISIBLE) {
                    start_close_chat.setBackgroundResource(R.drawable.end_chat_room);
                    chat_room_linear.setVisibility(View.GONE);
                } else {
                    start_close_chat.setBackgroundResource(R.drawable.start_chat_room);
                    chat_room_linear.setVisibility(View.VISIBLE);
                }
                break;

            //分享功能
            case R.id.start_shared:
                //分享面板设置
                ShareBoardConfig shareBoardConfig = new ShareBoardConfig();
                //设置分享面板的位置
                shareBoardConfig.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
                shareBoardConfig.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
                shareBoardConfig.setCancelButtonText("取消");
                ShareAction shareAction = new ShareAction(this);
                String photoData = SharedPreferencesUtil.getPhotoData(this, "photoData");
                if (photoData == null || "".equals(photoData)){
                    Toast.makeText(this, "分享封面不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                liveStreamingPresenter.sharedLive(id, "1", shareAction, shareBoardConfig, name, photoData);
                break;

        }
    }



    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            String upperCase = platform.name().toString().toUpperCase();
            //分享成功的提示语
            if (upperCase.equals("QQ")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.qq_shared_successed, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.weixin_shared_successed, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN_CIRCLE")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.weixin_circle_successed, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("SINA")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.sina_shared_successed, Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            String upperCase = platform.name().toString().toUpperCase();
            //分享失败的提示语
            if (upperCase.equals("QQ")) {
                LogUtils.e(R.string.qq_shared_error + "========" + t.toString());
                Toast.makeText(LiveStreamingActivity.this, R.string.qq_shared_error, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN")) {
                LogUtils.e(R.string.weixin_shared_error + "========" + t.toString());
                Toast.makeText(LiveStreamingActivity.this, R.string.weixin_shared_error, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN_CIRCLE")) {
                LogUtils.e(R.string.weixin_circle_shared_error + "========" + t.toString());
                Toast.makeText(LiveStreamingActivity.this, R.string.weixin_circle_shared_error, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("SINA")) {
                LogUtils.e(R.string.sina_shared_error + "========" + t.toString());
                Toast.makeText(LiveStreamingActivity.this, R.string.sina_shared_error, Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            String upperCase = platform.name().toString().toUpperCase();
            //取消分享的提示语
            if (upperCase.equals("QQ")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.qq_shared_cancel, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.weixin_shared_cancel, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN_CIRCLE")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.weixin_circle_cancel, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("SINA")) {
                Toast.makeText(LiveStreamingActivity.this, R.string.sina_shared_cancel, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void getUserInfo() {
        String access_token = SharedPreferencesUtil.getAccessToken(this, "access_token");

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
                            pictures = pic;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(LiveStreamingActivity.this, throwable.toString());
                    }
                });
    }

    @Override
    protected void onPause() {
        releaseTheAudioFocus(mAudioFocusChangeListener);
        MobclickAgent.onPause(this);
        if (mLSMediaCapture != null) {
            if (!m_tryToStopLivestreaming && m_liveStreamingOn) {
                if (mLiveStreamingPara.getStreamType() != lsMediaCapture.StreamType.AUDIO) {
                    //推最后一帧图像
                    mLSMediaCapture.backgroundVideoEncode();
                } else {
                    //推静音帧
                    mLSMediaCapture.backgroundAudioEncode();
                }
            }
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (mLSMediaCapture != null && m_liveStreamingOn) {
            if (mLiveStreamingPara.getStreamType() != lsMediaCapture.StreamType.AUDIO) {
                //关闭推流固定图像，正常推流
                mLSMediaCapture.resumeVideoEncode();
            } else {
                //关闭推流静音帧
                mLSMediaCapture.resumeAudioEncode();
            }
        }
    }


    @Override
    protected void onDestroy() {

        releaseTheAudioFocus(mAudioFocusChangeListener);
        //停止直播调用相关API接口
        if (mLSMediaCapture != null && m_liveStreamingOn) {
            //停止直播，释放资源
            stopAV();
            //反初始化推流实例，当它与stopLiveStreaming连续调用时，参数为false
            mLSMediaCapture.uninitLsMediaCapture(false);
            mLSMediaCapture = null;
        }

        if (m_liveStreamingOn) {
            m_liveStreamingOn = false;
        }
        liveStreamHandler.removeCallbacksAndMessages(null);
        live_time.stop();
        super.onDestroy();
    }

    private void stopAV() {
        if (mLSMediaCapture != null) {
            mLSMediaCapture.stopLiveStreaming();
        }
    }

    @Override
    public void handleMessage(int i, Object o) {
        switch (i) {
            case MSG_INIT_LIVESTREAMING_OUTFILE_ERROR://初始化直播出错
            case MSG_INIT_LIVESTREAMING_VIDEO_ERROR:
            case MSG_INIT_LIVESTREAMING_AUDIO_ERROR:
                Toast.makeText(this, "初始化直播出错", Toast.LENGTH_SHORT).show();
                break;

            case MSG_START_LIVESTREAMING_ERROR://开始直播出错
                Toast.makeText(this, "开始直播出错：" + o, Toast.LENGTH_SHORT).show();
                break;

            case MSG_STOP_LIVESTREAMING_ERROR://停止直播出错
                if (m_liveStreamingOn) {
                    Toast.makeText(this, "停止直播出错", Toast.LENGTH_SHORT).show();
                }
                break;
            case MSG_AUDIO_PROCESS_ERROR://音频处理出错
                if (m_liveStreamingOn && System.currentTimeMillis() - mLastAudioProcessErrorAlertTime >= 10000) {
                    Toast.makeText(this, "音频处理出错", Toast.LENGTH_SHORT).show();
                    mLastAudioProcessErrorAlertTime = System.currentTimeMillis();
                }
                break;
            case MSG_VIDEO_PROCESS_ERROR://视频处理出错
                if (m_liveStreamingOn && System.currentTimeMillis() - mLastVideoProcessErrorAlertTime >= 10000) {
                    Toast.makeText(this, "视频处理出错", Toast.LENGTH_SHORT).show();
                    mLastVideoProcessErrorAlertTime = System.currentTimeMillis();
                }

                break;
            case MSG_START_PREVIEW_ERROR://视频预览出错，可能是获取不到camera的使用权限
                Toast.makeText(this, "无法打开相机，可能没有相关的权限或者自定义分辨率不支持", Toast.LENGTH_SHORT).show();
                break;
            case MSG_AUDIO_RECORD_ERROR://音频采集出错，获取不到麦克风的使用权限
                Toast.makeText(this, "无法开启；录音，可能没有相关的权限", Toast.LENGTH_SHORT).show();
                break;
            case MSG_RTMP_URL_ERROR://断网消息
                mLSMediaCapture.restartLiveStream();
                Toast.makeText(this, "网络错误,正在重新连接...", Toast.LENGTH_SHORT).show();
                break;

            case MSG_URL_NOT_AUTH://直播URL非法，URL格式不符合视频云要求
                Toast.makeText(this, "直播地址不合法", Toast.LENGTH_SHORT).show();
                break;
            case MSG_SEND_STATICS_LOG_ERROR://发送统计信息出错
                break;

            case MSG_SEND_HEARTBEAT_LOG_ERROR://发送心跳信息出错
                break;

            case MSG_AUDIO_SAMPLE_RATE_NOT_SUPPORT_ERROR://音频采集参数不支持
                Toast.makeText(this, "音频采集参数不支持", Toast.LENGTH_SHORT).show();
                break;

            case MSG_AUDIO_PARAMETER_NOT_SUPPORT_BY_HARDWARE_ERROR://音频参数不支持

                Toast.makeText(this, "音频参数不支持", Toast.LENGTH_SHORT).show();
                break;

            case MSG_NEW_AUDIORECORD_INSTANCE_ERROR://音频实例初始化出错

                Toast.makeText(this, "音频实例初始化出错", Toast.LENGTH_SHORT).show();
                break;

            case MSG_AUDIO_START_RECORDING_ERROR://音频采集出错
                Toast.makeText(this, "音频采集出错", Toast.LENGTH_SHORT).show();
                break;

            case MSG_QOS_TO_STOP_LIVESTREAMING://网络QoS极差，视频码率档次降到最低

                Toast.makeText(this, "网络QoS极差，视频码率档次降到最低", Toast.LENGTH_SHORT).show();
                break;

            case MSG_HW_VIDEO_PACKET_ERROR://视频硬件编码出错反馈消息
                Toast.makeText(this, "视频硬件编码出错反馈消息", Toast.LENGTH_SHORT).show();
                break;

            case MSG_WATERMARK_INIT_ERROR://视频水印操作初始化出错
                Toast.makeText(this, "视频水印操作初始化出错", Toast.LENGTH_SHORT).show();
                break;

            case MSG_WATERMARK_PIC_OUT_OF_VIDEO_ERROR://视频水印图像超出原始视频出错
                Toast.makeText(this, "视频水印图像超出原始视频出错", Toast.LENGTH_SHORT).show();
                break;

            case MSG_WATERMARK_PARA_ERROR://视频水印参数设置出错
                Toast.makeText(this, "视频水印参数设置出错", Toast.LENGTH_SHORT).show();
                break;

            case MSG_CAMERA_PREVIEW_SIZE_NOT_SUPPORT_ERROR://camera采集分辨率不支持
                Toast.makeText(this, "相机采集分辨率不支持", Toast.LENGTH_SHORT).show();
                break;

            case MSG_CAMERA_NOT_SUPPORT_FLASH:
                Toast.makeText(this, "不支持闪光灯", Toast.LENGTH_SHORT).show();
                break;
            case MSG_START_PREVIEW_FINISHED://camera采集预览完成
                //Toast.makeText(this, "相机采集预览完成", Toast.LENGTH_SHORT).show();
                break;

            case MSG_START_LIVESTREAMING_FINISHED://开始直播完成
                m_liveStreamingOn = true;
                Toast.makeText(this, "直播开始", Toast.LENGTH_SHORT).show();
                break;

            case MSG_STOP_LIVESTREAMING_FINISHED://停止直播完成
                m_liveStreamingOn = false;
                Toast.makeText(this, "停止直播已完成", Toast.LENGTH_SHORT).show();
                break;

            case MSG_STOP_VIDEO_CAPTURE_FINISHED:
                break;

            case MSG_STOP_AUDIO_CAPTURE_FINISHED:
                break;

            case MSG_SWITCH_CAMERA_FINISHED://切换摄像头完成
                int cameraId = (Integer) o;//切换之后的camera id
                if (cameraId == 1) {
                    Toast.makeText(this, "前置摄像头切换成功!", Toast.LENGTH_SHORT).show();
                } else if (cameraId == 0) {
                    Toast.makeText(this, "后置摄像头切换成功!", Toast.LENGTH_SHORT).show();
                }
                break;


            case MSG_BAD_NETWORK_DETECT://如果连续一段时间（10s）实际推流数据为0，会反馈这个错误消息
//                mLSMediaCapture.restartLiveStream();
                Toast.makeText(this, "网络连接异常", Toast.LENGTH_SHORT).show();
                break;

            case MSG_URL_FORMAT_NOT_RIGHT://推流url格式不正确
                Toast.makeText(this, "推流地址的格式不正确", Toast.LENGTH_SHORT).show();
                break;

            case MSG_URL_IS_EMPTY://推流url为空
                Toast.makeText(this, "推流地址为空!", Toast.LENGTH_SHORT).show();
                break;

            case MSG_SPEED_CALC_SUCCESS:
            case MSG_SPEED_CALC_FAIL:
                break;
            default:
                break;

        }
    }


}
