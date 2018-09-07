package com.xgkj.ilive.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netease.neliveplayer.NELivePlayer;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.LiveStreamingAdapter;
import com.xgkj.ilive.adapter.SpaceItemDecoration;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.fragment.ChatFragment;
import com.xgkj.ilive.fragment.DetailsFragment;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.contract.PublishVideoDetailsContract;
import com.xgkj.ilive.mvp.model.AdvanceLiveModel;
import com.xgkj.ilive.mvp.model.PublishVideoDetailsModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.mvp.presenter.PublishVideoDetailsPresenter;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.player.media.NEVideoView;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.DateUtil;
import com.xgkj.ilive.utils.TimeUtils;
import com.xgkj.ilive.view.CircleImageView;
import com.xgkj.ilive.view.UserInfoDialog;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.netease.neliveplayer.NELivePlayer.NELP_BUFFERING_END;
import static com.netease.neliveplayer.NELivePlayer.NELP_BUFFERING_START;

public class PublishVideoDetailsActivity extends BaseActivity implements PublishVideoDetailsContract.View, NELivePlayer.OnPreparedListener, NELivePlayer.OnSeekCompleteListener, SeekBar.OnSeekBarChangeListener, NELivePlayer.OnCompletionListener, NELivePlayer.OnErrorListener, NELivePlayer.OnInfoListener {

    @BindView(R.id.nevideo_view)
    NEVideoView nevideo_view;
    @BindView(R.id.video_play_status)
    TextView video_play_status;
    @BindView(R.id.video_playing_time)
    TextView video_playing_time;
    @BindView(R.id.user_icon_img)
    CircleImageView user_icon_img;
    @BindView(R.id.video_play_and_pause)
    ImageView video_play_and_pause;
    @BindView(R.id.video_seekbar)
    SeekBar video_seekbar;
    @BindView(R.id.video_play_time)
    TextView video_play_time;
    @BindView(R.id.scan_big_small)
    ImageButton scan_big_small;
    @BindView(R.id.user_icon)
    CircleImageView user_icon;
    @BindView(R.id.video_nickname)
    TextView video_nickname;
    @BindView(R.id.look_number)
    TextView look_number;
    @BindView(R.id.follow_number)
    TextView follow_number;
    @BindView(R.id.share_rb)
    AutoLinearLayout share_rb;
    @BindView(R.id.tv_shared)
    TextView tv_shared;
    @BindView(R.id.chat_title)
    TextView chat_title;
    @BindView(R.id.details_title)
    TextView details_title;
    @BindView(R.id.video_details)
    LinearLayout video_details;
    @BindView(R.id.chat_underline)
    TextView chat_underline;
    @BindView(R.id.details_underline)
    TextView details_underline;
    @BindView(R.id.video_controller_is_show)
    LinearLayout video_controller_is_show;
    @BindView(R.id.loading_buffer_linear)
    LinearLayout loading_buffer_linear;
    @BindView(R.id.buffer_progressbar)
    ProgressBar buffer_progressbar;
    @BindView(R.id.current_internet_speed)
    TextView current_internet_speed;
    @BindView(R.id.show_hide_person)
    LinearLayout show_hide_person;
    @BindView(R.id.video_details_relative)
    RelativeLayout video_details_relative;
    @BindView(R.id.play_details_image)
    ImageView play_details_image;
    @BindView(R.id.chat_panel_show)
    LinearLayout chat_panel_show;
    @BindView(R.id.chat_recyclerview)
    RecyclerView chat_recyclerview;
    @BindView(R.id.et_chat_content)
    EditText et_chat_content;
    @BindView(R.id.image_back)
    ImageView image_back;
    @BindView(R.id.relative_play)
    AutoRelativeLayout relative_play;


    private PublishVideoDetailsPresenter publishVideoDetailsPresenter;
    private boolean mBackPressed;
    private TimeUtils timeUtils;
    private static final int REQUEST_CODE = 0x001;
    //播放的进度
    private static final int PROGRESS = 1;
    //分享的标识
    private static final int SHARED_WHAT = 2;
    private PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list;
    /**
     * 隐藏控制面板
     */
    private static final int HIDE_MEDIACONTROLLER = 3;

    /**
     * 显示网络速度
     */
    private static final int SHOW_SPEED = 4;
    /**
     * 是否显示控制面板
     */
    private boolean isshowMediaController = false;

    private long precurrentPosition;

    private Handler videoDetailsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case PROGRESS:
                    //总时间
                    long duration = nevideo_view.getDuration();
                    //播放的时间
                    long playableDuration = nevideo_view.getCurrentPosition();
                    video_seekbar.setMax((int) duration);
                    video_seekbar.setProgress((int) playableDuration);
                    //设置播放的时间
                    video_playing_time.setText(timeUtils.stringForTime((int) playableDuration) + "/" + timeUtils.stringForTime((int) duration));
                    video_play_time.setText(timeUtils.stringForTime((int) playableDuration) + "/" + timeUtils.stringForTime((int) duration));


                    //监听卡
                    if (!isUseSystem) {
                        if (nevideo_view.isPlaying()) {
                            int buffer = (int) (playableDuration - precurrentPosition);
                            if (buffer < 500) {
                                //视频卡了
                                loading_buffer_linear.setVisibility(View.VISIBLE);
                            } else {
                                //视频不卡了
                                loading_buffer_linear.setVisibility(View.GONE);
                            }
                        } else {
                            loading_buffer_linear.setVisibility(View.GONE);
                        }
                    }
                    precurrentPosition = playableDuration;

                    //3.每秒更新一次
                    videoDetailsHandler.removeMessages(PROGRESS);
                    videoDetailsHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
                    break;
                case SHARED_WHAT:
                    list = (PublishVideoDetailsModel.APIDATABean.RetBean.ListBean) msg.obj;
                    break;
                //隐藏或显示控制面板
                case HIDE_MEDIACONTROLLER:
                    hideMediaController();
                    break;
                case SHOW_SPEED:
                    //1.得到网络速度
                    String netSpeed = DateUtil.getNetSpeed(PublishVideoDetailsActivity.this);
                    //显示网速
                    current_internet_speed.setText("缓冲中..." + netSpeed);

                    //2.每两秒更新一次
                    videoDetailsHandler.removeMessages(SHOW_SPEED);
                    videoDetailsHandler.sendEmptyMessageDelayed(SHOW_SPEED, 2000);
                    break;
                case QUERY_CHAT_MESSAGE:
                    SystemClock.sleep(500);
                    publishVideoDetailsPresenter.queryChatMessage(cid);
                    break;
            }
        }
    };
    private GestureDetector videoSimpleOnGestureListener;
    private String cid;
    private boolean isUseSystem = true;
    private Intent intent;
    private String cid_url;
    private String title;
    private String pic;
    private String video_pic;
    public int is_follow;
    private String uid;

    /**
     * 查询聊天记录
     */
    private static final int QUERY_CHAT_MESSAGE = 5;

    private String type;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;
    private LiveStreamingAdapter liveStreamingAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_video_details;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = 0.7f;
        getWindow().setAttributes(params);

        try {
            //6.0后权限的处理
            if (Build.VERSION.SDK_INT >= 23) {
                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
                ActivityCompat.requestPermissions(this, mPermissionList, REQUEST_CODE);
            }
            //处理播放视频时候暂停其他应用的播放
            pauseOtherAppSoucePlay();

            videoSimpleOnGestureListener = new GestureDetector(this, new VideoSimpleOnGestureListener());

            //开始更新网络速度
            videoDetailsHandler.sendEmptyMessage(SHOW_SPEED);

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            LogUtils.e(e.toString());
        }
        liveStreamingAdapter = new LiveStreamingAdapter();
        chat_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        chat_recyclerview.addItemDecoration(new SpaceItemDecoration(5));
        videoDetailsHandler.sendEmptyMessage(QUERY_CHAT_MESSAGE);

        //点击发送按键进行发送内容
        et_chat_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    String send_content = textView.getText().toString();
                    //发送消息内容
                    publishVideoDetailsPresenter.sendMessageContent(cid, send_content);
                    et_chat_content.setText("");
                    videoDetailsHandler.sendEmptyMessage(QUERY_CHAT_MESSAGE);
                }
                return false;
            }
        });
    }

    /**
     * 对其他声音的处理
     */
    private void pauseOtherAppSoucePlay() {
        if (requestTheAudioFocus() == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            intent = getIntent();
            is_follow = intent.getIntExtra("is_follow",0);
            cid = intent.getStringExtra("cid");
            uid = intent.getStringExtra("publish_id");
            timeUtils = new TimeUtils();
            publishVideoDetailsPresenter = new PublishVideoDetailsPresenter(this);
            publishVideoDetailsPresenter.initNEVideoView();
            type = intent.getStringExtra("live_type");
            String stringExtra = intent.getStringExtra("type");
            String created = intent.getStringExtra("created");
            String username = intent.getStringExtra("username");
            video_play_status.setText(stringExtra);
            if ("".equals(cid) || null == cid || cid.length() == 3) {
                cid_url = intent.getStringExtra("cid_url");
                title = intent.getStringExtra("title");
                video_pic = intent.getStringExtra("video_pic");
                String pull_url = intent.getStringExtra("pull_url");
                if (!"".equals(cid_url) && this.type.equals("1")) {

                    video_details_relative.setVisibility(View.VISIBLE);
                    play_details_image.setVisibility(View.GONE);

                    nevideo_view.setVideoPath(NetUrl.BASE_PUBLISH_VIDEO + cid_url);
                } else if ("".equals(cid_url) && this.type.equals("1")) {
                    video_details_relative.setVisibility(View.VISIBLE);
                    play_details_image.setVisibility(View.GONE);

                    nevideo_view.setVideoPath(pull_url);
                } else if ("".equals(cid_url) && type.equals("2")) {
                    video_details_relative.setVisibility(View.GONE);
                    play_details_image.setVisibility(View.VISIBLE);
                    publishVideoDetailsPresenter.getAdvanceLiveDetails(cid);
                    Glide.with(this).load(video_pic).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(play_details_image);
                } else if (!"".equals(cid_url) && type.equals("2")) {
                    video_details_relative.setVisibility(View.GONE);
                    play_details_image.setVisibility(View.VISIBLE);
                    publishVideoDetailsPresenter.getAdvanceLiveDetails(cid);
                    Glide.with(this).load(video_pic).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(play_details_image);
                }

                details_title.setTextColor(getResources().getColor(R.color.main_rb_btn_checked_color));
                chat_title.setTextColor(Color.BLACK);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.video_details_framelayout, DetailsFragment.newInstance(title, created, username)).commit();
                getVideoData();

            } else {
                publishVideoDetailsPresenter.getVideoDetails(cid);
            }


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
                    nevideo_view.start();
                    Log.e("CALL_STATE_IDLE", "*****************************************");
                    break;
                //电话接听
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    nevideo_view.pause();
                    Log.e("CALL_STATE_IDLE", "##########################################");
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

    /**
     * 获取视频数据
     */
    private void getVideoData() {
        nevideo_view.setOnPreparedListener(this);
        nevideo_view.start();
        nevideo_view.setOnCompletionListener(this);
        String view = intent.getStringExtra("view");
        String like = intent.getStringExtra("like");
        String send = intent.getStringExtra("send");
        pic = intent.getStringExtra("pic");
        String username = intent.getStringExtra("username");

        if (isUseSystem) {
            //监听视频播放卡-系统的api
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                nevideo_view.setOnInfoListener(this);
            }
        }
        if ("".equals(username)) {
            video_nickname.setText("昵称");
        } else {
            video_nickname.setText(username);
        }
        look_number.setText(view + "次");
        follow_number.setText(like + "次");
        tv_shared.setText(send + "次");
        Glide.with(this).load(pic).into(user_icon_img);
        Glide.with(this).load(pic).asBitmap().placeholder(R.drawable.mine_circle_icon).error(R.drawable.mine_circle_icon).into(user_icon);
        video_play_and_pause.setImageResource(R.drawable.play_video);
        video_seekbar.setOnSeekBarChangeListener(this);
        //播放出错了的监听
        nevideo_view.setOnErrorListener(this);
        Message message = Message.obtain();
        message.what = SHARED_WHAT;
        message.obj = list;
        videoDetailsHandler.sendMessage(message);
    }

    @Override
    public void getVideoDetailsFinished(final PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list) {
        String cid = list.getCid();
        //设置视频播放的地址
        nevideo_view.setVideoPath(NetUrl.BASE_PUBLISH_VIDEO + list.getCid_url());

        video_play_status.setText("回放");
        is_follow = list.getIs_follow();
        uid = list.getPublish_id();
        Log.e("startClickLike", is_follow + "=============");
        Glide.with(this).load(list.getUser_pic()).into(user_icon_img);
        Glide.with(this).load(list.getUser_pic()).into(user_icon);

        //设置监听
        nevideo_view.setOnPreparedListener(this);
        nevideo_view.setOnCompletionListener(this);

        if (isUseSystem) {
            //监听视频播放卡-系统的api
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                nevideo_view.setOnInfoListener(this);
            }
        }
        video_play_and_pause.setImageResource(R.drawable.play_video);
        video_seekbar.setOnSeekBarChangeListener(this);
        //播放出错了的监听
        nevideo_view.setOnErrorListener(this);

        video_nickname.setText(list.getUsername());
        look_number.setText(list.getView() + "次");
        follow_number.setText(list.getLike() + "次");
        tv_shared.setText(list.getSend() + "次");

        chat_underline.setVisibility(View.VISIBLE);
        details_underline.setVisibility(View.INVISIBLE);

        details_title.setTextColor(getResources().getColor(R.color.main_rb_btn_checked_color));
        chat_title.setTextColor(Color.BLACK);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.video_details_framelayout, DetailsFragment.newInstance(list.getTitle(), list.getCreated(), list.getUsername())).commit();

        Message message = Message.obtain();
        message.what = SHARED_WHAT;
        message.obj = list;
        videoDetailsHandler.sendMessage(message);
    }

    @Override
    public void getLikeDetailsFinished(PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list) {
        String like = list.getLike();
        Drawable drawableLeft = getResources().getDrawable(
                R.drawable.follow_checked);
        follow_number.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,null,null,null);
        follow_number.setCompoundDrawablePadding(64);
        follow_number.setText(like+"次");
    }

    @Override
    public void getLikeAnvanceLiveFinished(AdvanceLiveModel.APIDATABean.RetBean.ListBean list) {
        String like = list.getYglike();
        Drawable drawableLeft = getResources().getDrawable(
                R.drawable.follow_checked);
        follow_number.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,null,null,null);
        follow_number.setCompoundDrawablePadding(64);
        follow_number.setText(like+"次");
    }

    @Override
    public void getAdvanceLiveDetailsFinished(AdvanceLiveModel.APIDATABean.RetBean.ListBean list) {
        look_number.setText(list.getView() + "次");
        follow_number.setText(list.getYglike() + "次");
        tv_shared.setText(list.getYgsend() + "次");
    }


    @OnClick({R.id.follow_number, R.id.tv_shared, R.id.video_play_and_pause, R.id.chat_title, R.id.share_rb, R.id.details_title,
            R.id.scan_big_small, R.id.image_back,R.id.user_icon_img,R.id.user_icon})
    public void onDownClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        switch (view.getId()) {
            case R.id.image_back:
                onBackPressed();
                break;
            case R.id.video_play_and_pause:
                if (nevideo_view.isPlaying()) {
                    nevideo_view.pause();
                    video_play_and_pause.setImageResource(R.drawable.play_pause);
                } else {
                    nevideo_view.start();
                    video_play_and_pause.setImageResource(R.drawable.play_video);
                }
                break;

            case R.id.tv_shared:
            case R.id.share_rb:

                //ShareBoardConfig初始化配置
                ShareBoardConfig shareBoardConfig = new ShareBoardConfig();
                //设置分享面板的位置
                shareBoardConfig.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);
                shareBoardConfig.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
                shareBoardConfig.setCancelButtonText("取消分享");
                ShareAction shareAction = new ShareAction(this);

                if (list != null) {
                    String logo = list.getVideo_pic();
                    String title = list.getTitle();
                    publishVideoDetailsPresenter.startTransmit(cid, "2", title, logo, "点播", shareBoardConfig, shareAction);
                } else {
                    String status_content = video_play_status.getText().toString();

                    String title = null;
                    String pic = null;

                    if (this.intent != null) {
                        title = this.intent.getStringExtra("title");
                        pic = this.intent.getStringExtra("video_pic");
                    }

                    if (status_content.equals("直播") || "回放".equals(status_content)) {
                        publishVideoDetailsPresenter.startTransmit(cid, "1", title, pic, status_content, shareBoardConfig, shareAction);
                    } else if ("预告".equals(status_content)) {
                        publishVideoDetailsPresenter.startTransmit(cid, "1", title, pic, status_content, shareBoardConfig, shareAction);
                    }

                }
                //设置分享回调监听
                shareAction.setCallback(shareListener);
                break;

            case R.id.chat_title:

                chat_underline.setVisibility(View.INVISIBLE);
                details_underline.setVisibility(View.VISIBLE);

                chat_title.setTextColor(getResources().getColor(R.color.main_rb_btn_checked_color));
                details_title.setTextColor(Color.BLACK);

                Intent intent = getIntent();
                String room_id = intent.getStringExtra("room_id");
                String cid = intent.getStringExtra("cid");

                manager.beginTransaction().replace(R.id.video_details_framelayout, ChatFragment.newInstance(room_id, cid)).commit();
                break;
            case R.id.details_title:
                chat_underline.setVisibility(View.VISIBLE);
                details_underline.setVisibility(View.INVISIBLE);

                details_title.setTextColor(getResources().getColor(R.color.main_rb_btn_checked_color));
                chat_title.setTextColor(Color.BLACK);
                String title = null;
                String time = null;
                String username = null;
                if (list != null) {
                    title = list.getTitle();
                    time = list.getCreated();
                    username = list.getUsername();
                } else {
                    if (this.intent != null) {
                        title = this.intent.getStringExtra("title");
                        username = this.intent.getStringExtra("username");
                        time = this.intent.getStringExtra("created");
                    }
                }
                manager.beginTransaction().replace(R.id.video_details_framelayout, DetailsFragment.newInstance(title, time, username)).commit();
                break;
            //横竖屏切换
            case R.id.scan_big_small:
                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    show_hide_person.setVisibility(View.VISIBLE);
                    chat_panel_show.setVisibility(View.GONE);
                    //获取屏幕宽高
                    WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    int width = wm.getDefaultDisplay().getWidth();
                    int height = wm.getDefaultDisplay().getHeight();

                    AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.MATCH_PARENT, height / 3);
                    relative_play.setLayoutParams(params);
                } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    show_hide_person.setVisibility(View.GONE);
                    chat_panel_show.setVisibility(View.VISIBLE);
                    AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT);
                    relative_play.setLayoutParams(params);
                }
                break;
            case R.id.follow_number:
                String type1 = this.intent.getStringExtra("type");
                String type = null;
                if ("预告".equals(type1)){
                    type = "3";
                }else {
                    type = this.intent.getStringExtra("live_type");
                }

                Log.e("follow_number",type+"**********************************");
                publishVideoDetailsPresenter.startClickLike(this.cid, type);

                break;
            case R.id.user_icon:
            case R.id.user_icon_img:
                UserInfoDialog dialog = new UserInfoDialog(this,uid,pic, is_follow, new UserInfoDialog.DialogCallBackListener() {
                    @Override
                    public void callBack(int msg) {
                        is_follow = msg;
                    }
                });
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
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
            Log.e("platform", platform + "onStart");
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
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.qq_shared_successed, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN")) {
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.weixin_shared_successed, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN_CIRCLE")) {
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.weixin_circle_successed, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("SINA")) {
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.sina_shared_successed, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.qq_shared_error, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN")) {
                LogUtils.e(R.string.weixin_shared_error + "========" + t.toString());
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.weixin_shared_error, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN_CIRCLE")) {
                LogUtils.e(R.string.weixin_circle_shared_error + "========" + t.toString());
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.weixin_circle_shared_error, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("SINA")) {
                LogUtils.e(R.string.sina_shared_error + "========" + t.toString());
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.sina_shared_error, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.qq_shared_cancel, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN")) {
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.weixin_shared_cancel, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("WEIXIN_CIRCLE")) {
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.weixin_circle_cancel, Toast.LENGTH_SHORT).show();
            } else if (upperCase.equals("SINA")) {
                Toast.makeText(PublishVideoDetailsActivity.this, R.string.sina_shared_cancel, Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    public NEVideoView getNEVideoView() {
        if (nevideo_view == null) {
            nevideo_view = (NEVideoView) findViewById(R.id.nevideo_view);
        }
        return nevideo_view;
    }

    @Override
    public void queryChatMessageFinished(List<QueryChatMessageModel.APIDATABean.RetBean> ret) {
        liveStreamingAdapter.setData(ret);
        chat_recyclerview.setAdapter(liveStreamingAdapter);
        chat_recyclerview.smoothScrollToPosition(ret.size() - 1);
        liveStreamingAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onPrepared(NELivePlayer neLivePlayer) {
//        wakeLock.acquire();
        nevideo_view.start();
        videoDetailsHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
    }


    @Override
    public void onBackPressed() {
        mBackPressed = true;
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (nevideo_view.isPlaying()) {
            nevideo_view.pause();
            video_play_and_pause.setImageResource(R.drawable.play_pause);
            //进行释放焦点
            releaseTheAudioFocus(mAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        nevideo_view.stopBackgroundPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (requestTheAudioFocus() == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            requestTheAudioFocus();
        }

        if (!nevideo_view.isPlaying()) {
            nevideo_view.start();
            video_play_and_pause.setImageResource(R.drawable.play_video);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (nevideo_view.isPaused()) {
            nevideo_view.start();
            Log.e("onResume", "================================");
            video_play_and_pause.setImageResource(R.drawable.play_video);
        }
    }

    @Override
    public void onSeekComplete(NELivePlayer neLivePlayer) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            nevideo_view.seekTo(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCompletion(NELivePlayer neLivePlayer) {
        video_play_and_pause.setImageResource(R.drawable.play_pause);
    }


    @Override
    protected void onDestroy() {
        videoDetailsHandler.removeCallbacksAndMessages(null);
        //进行释放焦点
        releaseTheAudioFocus(mAudioFocusChangeListener);
//        wakeLock.release();
        super.onDestroy();
    }


    @Override
    public boolean onError(NELivePlayer neLivePlayer, int i, int i1) {

        publishVideoDetailsPresenter.initNEVideoView();
        if (cid != null && cid.length() > 3) {
            Toast.makeText(this, "播放失败,正在重新加载，请稍等...", Toast.LENGTH_SHORT).show();
            publishVideoDetailsPresenter.getVideoDetails(cid);
        } else if (cid == null || "".equals(cid)) {
            if (!"".equals(cid_url) && type.equals("1")) {
                nevideo_view.setVideoPath(NetUrl.BASE_PUBLISH_VIDEO + cid_url);
            } else if ("".equals(cid_url) && type.equals("1")) {
                Toast.makeText(this, "拉流结束!", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public boolean onInfo(NELivePlayer neLivePlayer, int i, int i1) {
        switch (i) {
            case NELP_BUFFERING_START:
                nevideo_view.pause();
                video_play_and_pause.setImageResource(R.drawable.play_pause);
                loading_buffer_linear.setVisibility(View.VISIBLE);
                break;
            case NELP_BUFFERING_END:
                nevideo_view.start();
                video_play_and_pause.setImageResource(R.drawable.play_video);
                loading_buffer_linear.setVisibility(View.GONE);
                break;
        }

        return true;
    }

    //手势按键监听
    class VideoSimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (isshowMediaController) {
                //隐藏
                hideMediaController();
                //把隐藏消息移除
                videoDetailsHandler.removeMessages(HIDE_MEDIACONTROLLER);

            } else {
                //显示
                showMediaController();
                //发消息隐藏
                videoDetailsHandler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 4000);
            }

            return super.onSingleTapConfirmed(e);
        }
    }

    /**
     * 隐藏控制面板
     */
    private void hideMediaController() {
        video_controller_is_show.setVisibility(View.GONE);
        isshowMediaController = false;
    }

    /**
     * 显示控制面板
     */
    private void showMediaController() {
        video_controller_is_show.setVisibility(View.VISIBLE);
        isshowMediaController = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //3.把事件传递给手势识别器
        videoSimpleOnGestureListener.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下
                videoDetailsHandler.removeMessages(HIDE_MEDIACONTROLLER);
                break;
            case MotionEvent.ACTION_UP://手指离开
                videoDetailsHandler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 4000);
                break;
        }
        return super.onTouchEvent(event);
    }
}
