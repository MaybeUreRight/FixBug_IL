package com.xgkj.ilive.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.PublishVideoDetailsActivity;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.FollowModel;
import com.xgkj.ilive.mvp.model.UserInfoModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by admin on 2017/8/1.
 */

public class UserInfoDialog extends Dialog implements View.OnClickListener{

    private Context mContext;
    private String uid;
    private int isFollow;
    private String pic;

    private DialogCallBackListener listener;

    CircleImageView iv_head;
    TextView tv_follow;
    TextView tv_fans;
    TextView tv_live_num;
    Button btn_follow;
    ImageView iv_close;

    public UserInfoDialog(Context context, String uid,String pic,int isFollow,DialogCallBackListener listener) {
        this(context, R.style.live_option_dialog,uid,pic,isFollow,listener);
        this.mContext = context;
        this.uid = uid;
        this.isFollow = isFollow;
        this.listener = listener;
        this.pic = pic;
    }

    public UserInfoDialog(@NonNull Context context, int style, String uid,String pic,int isFollow, DialogCallBackListener listener) {
        super(context, style);
        this.mContext = context;
        this.uid = uid;
        this.isFollow = isFollow;
        this.listener = listener;
        this.pic = pic;
        setContentView(R.layout.user_info_dialog);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView(){
        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_follow = (TextView) findViewById(R.id.tv_follow);
        tv_fans = (TextView) findViewById(R.id.tv_fans);
        tv_live_num = (TextView) findViewById(R.id.tv_live_num);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        btn_follow = (Button) findViewById(R.id.btn_follow);
        btn_follow.setOnClickListener(this);
        if (1 == isFollow){
            btn_follow.setText("已关注");
        }else {
            btn_follow.setText("关注");
        }
        Glide.with(mContext).asBitmap().load(pic)
                .apply(App.requestOptions.placeholder(R.drawable.mine_circle_icon).error(R.drawable.mine_circle_icon))
                .into(iv_head);
        getUerInfo(uid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.btn_follow:
                if (1 == isFollow){
                    unFollowUser();
                }else {
                    followUser();
                }
                break;
        }
    }

    /**
     * 关注主播
     * */
    private void followUser(){
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String> contentMap = new HashMap<>();
        contentMap.put("to_uid",uid);
        JSONObject jobj = GsonUtils.upJson(contentMap);
        Subscription subscription = OkGo.post(NetUrl.FOLLOW+access_token)
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
                        FollowModel followModel = GsonUtils.jsonToBean(s, FollowModel.class);
                        FollowModel.APIDATABean apidata = followModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            btn_follow.setText("已关注");
                            listener.callBack(1);
                            dismiss();
                        }else {
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("joinPeople",throwable.toString());
                    }
                });
    }

    /**
     * 取消关注
     * */
    private void unFollowUser(){
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String> contentMap = new HashMap<>();
        contentMap.put("to_uid",uid);
        JSONObject jobj = GsonUtils.upJson(contentMap);
        Subscription subscription = OkGo.post(NetUrl.UNFOLLOW+access_token)
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
                        FollowModel followModel = GsonUtils.jsonToBean(s, FollowModel.class);
                        FollowModel.APIDATABean apidata = followModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            btn_follow.setText("关注");
                            listener.callBack(0);
                            dismiss();
                        }else {
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("joinPeople",throwable.toString());
                    }
                });
    }

    /**
     * 获取用户信息
     * */
    private void getUerInfo(String uid){
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        Map<String,String> contentMap = new HashMap<>();
        contentMap.put("uid",uid);
        JSONObject jobj = GsonUtils.upJson(contentMap);
        Subscription subscription = OkGo.post(NetUrl.LOOK_LIVE_PEOPLE+access_token)
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
                        UserInfoModel taUserModel = GsonUtils.jsonToBean(s, UserInfoModel.class);
                        UserInfoModel.APIDATABean apidata = taUserModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            UserInfoModel.RetBean bean = apidata.getRet();
                            tv_fans.setText(String.valueOf(bean.getFans_count()));
                            tv_follow.setText(String.valueOf(bean.getFollow_count()));
                            tv_live_num.setText(String.valueOf(bean.getVideo_count()));
                        }else {
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("joinPeople",throwable.toString());
                    }
                });
    }

    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(int msg);//具体方法
    }

}
