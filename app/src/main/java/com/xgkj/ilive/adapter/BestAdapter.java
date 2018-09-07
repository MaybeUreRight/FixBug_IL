package com.xgkj.ilive.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMVideo;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.PublishVideoDetailsActivity;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.model.BestNewModel;
import com.xgkj.ilive.mvp.model.LiveDetailsModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.DateUtil;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/8/11 0011 15:06
 */

public class BestAdapter extends RecyclerView.Adapter<BestAdapter.ViewHolder> implements View.OnClickListener, TextWatcher {

    private Context context;
    public BestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_live_video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        BestNewModel.APIDATABean.RetBean.ListBean listBean = bestAdapter.get(position);
        String cid_url = listBean.getCid_url();
        String live_type = listBean.getLive_type();

        String s = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated() + "000"));
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String date1 = formatter.format(currentTime);
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

        try {
            Date parse = df.parse(s);
            Date parse1 = df.parse(date1);
            long diff = parse1.getTime() - parse.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            holder.create_time.setText(days+"天前");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if ("".equals(cid_url) && live_type.equals("1")){
            holder.play_type.setText("直播");
        }else if (!"".equals(cid_url) && live_type.equals("1")){
            holder.play_type.setText("回放");
        }else if ("".equals(cid_url) && live_type.equals("2")){
            holder.create_time.setText("预告");
        }else if (!"".equals(cid_url)&& live_type.equals("2")){
            holder.create_time.setText("预告");
        }

        Glide.with(holder.itemView.getContext()).load(listBean.getVideo_pic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.list_video_pic);
        Glide.with(holder.itemView.getContext()).load(listBean.getUser_pic()).asBitmap().placeholder(R.drawable.chat_icon).error(R.drawable.chat_icon).into(holder.user_icon);
        holder.return_title.setText(listBean.getTitle());
        holder.company_title.setText(listBean.getCompany_title());
        holder.look_people.setText(listBean.getVideo_count()+"次观看");

        String type = listBean.getType();
        if (type.equals("1")){
            holder.jiami.setVisibility(View.GONE);
        }else if (type.equals("2")){
            Toast.makeText(context, "请支付观看所有费用", Toast.LENGTH_SHORT).show();
            holder.jiami.setVisibility(View.GONE);
        }else if (type.equals("3")){
            holder.jiami.setVisibility(View.VISIBLE);
        }


        final String cid = listBean.getCid();
        final String title = listBean.getTitle();
        //分享功能
        holder.btn_shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupWindow popupWindow = new PopupWindow(context);
                View view2 = View.inflate(context, R.layout.pop_live_shared, null);

                LinearLayout live_wechat_shared =(LinearLayout) view2.findViewById(R.id.live_wechat_shared);
                LinearLayout live_wechat_friend_shared =(LinearLayout) view2.findViewById(R.id.live_wechat_friend_shared);
                LinearLayout live_sina_shared =(LinearLayout) view2.findViewById(R.id.live_sina_shared);
                TextView live_cancel_shared =(TextView) view2.findViewById(R.id.live_cancel_shared);

                popupWindow.setContentView(view2);
                popupWindow.setWidth(AutoLinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(AutoLinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.showAsDropDown((View) holder.itemView.getParent());
                //微信分享
                live_wechat_shared.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setSharePlatform(SHARE_MEDIA.WEIXIN,cid,title);
                        popupWindow.dismiss();
                    }
                });

                //朋友圈分享
                live_wechat_friend_shared.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setSharePlatform(SHARE_MEDIA.WEIXIN_CIRCLE,cid,title);
                        popupWindow.dismiss();
                    }
                });

                //新浪分享
                live_sina_shared.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setSharePlatform(SHARE_MEDIA.SINA,cid,title);
                        popupWindow.dismiss();
                    }
                });

                live_cancel_shared.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

        String string = holder.play_type.getText().toString();
        String video_pic = listBean.getVideo_pic();
        String type_value = listBean.getType_value();
        String type1 = listBean.getType();
        if (type1.equals("3")) {
            holder.jiami.addTextChangedListener(new editencryptContent(cid, string, video_pic, type_value));
        }
    }

    private void getLiveInfo(final String cid, final String type,final String video_pic) {
        Map<String,String> liveInfo = new HashMap<>();
        liveInfo.put("cid",cid);
        JSONObject jobj = GsonUtils.upJson(liveInfo);

        Subscription subscription = OkGo.post(NetUrl.GET_LIVE_DETAILS + SharedPreferencesUtil.getAccessToken(context,"access_token"))
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
                        LiveDetailsModel liveDetailsModel = GsonUtils.jsonToBean(s, LiveDetailsModel.class);
                        LiveDetailsModel.APIDATABean apidata = liveDetailsModel.getAPIDATA();
                        LiveDetailsModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200){
                            LiveDetailsModel.APIDATABean.RetBean.ListBean list = ret.getList();
                            String cid1 = list.getCid();
                            String cid_url = list.getCid_url();
                            String room_id = list.getRoom_id();
                            String pull_url = list.getPull_url();
                            String view = list.getView();
                            String like = list.getLike();
                            String pic = list.getUser_pic();
                            String logo = list.getLogo();
                            String send = list.getSend();
                            String live_type = list.getLive_type();
                            String username = list.getUsername();
                            String title = list.getTitle();
                            String created = list.getCreated();
                            String publish_id = list.getPublish_id();
                            int is_follow = list.getIs_follow();
                            Intent intent = new Intent(context, PublishVideoDetailsActivity.class);
                            intent.putExtra("pull_url",pull_url);
                            intent.putExtra("room_id",room_id);
                            intent.putExtra("cid_url",cid_url);
                            intent.putExtra("view",view);
                            intent.putExtra("like",like);
                            intent.putExtra("pic",pic);
                            intent.putExtra("logo",logo);
                            intent.putExtra("send",send);
                            intent.putExtra("live_type",live_type);
                            intent.putExtra("username",username);
                            intent.putExtra("cid",cid);
                            intent.putExtra("type",type);
                            intent.putExtra("title",title);
                            intent.putExtra("created",created);
                            intent.putExtra("video_pic",video_pic);
                            intent.putExtra("publish_id",publish_id);
                            intent.putExtra("is_follow",is_follow);
                            context.startActivity(intent);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(context, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public int getItemCount() {
        return bestAdapter!=null?bestAdapter.size():0;
    }

    private List<BestNewModel.APIDATABean.RetBean.ListBean> bestAdapter;
    public void setBestListData(List<BestNewModel.APIDATABean.RetBean.ListBean> bestAdapter) {
        this.bestAdapter = bestAdapter;
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 设置分享的平台
     *
     * @param platform
     */
    private void setSharePlatform(final SHARE_MEDIA platform, final String cid, final String title) {
        Map<String,String> liveInfo = new HashMap<>();
        liveInfo.put("cid",cid);
        JSONObject jobj = GsonUtils.upJson(liveInfo);

        Subscription subscription = OkGo.post(NetUrl.GET_LIVE_DETAILS + SharedPreferencesUtil.getAccessToken(context,"access_token"))
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
                        LiveDetailsModel liveDetailsModel = GsonUtils.jsonToBean(s, LiveDetailsModel.class);
                        LiveDetailsModel.APIDATABean apidata = liveDetailsModel.getAPIDATA();
                        LiveDetailsModel.APIDATABean.RetBean ret = apidata.getRet();
                        int code = ret.getCode();
                        if (code == 200){

                            LiveDetailsModel.APIDATABean.RetBean.ListBean list = ret.getList();
                            String cid_url = list.getCid_url();
                            UMVideo umVideo = new UMVideo(NetUrl.BASE_PUBLISH_VIDEO + cid_url);
                            umVideo.setTitle(title);
                            umVideo.setDescription(null);
                            new ShareAction((Activity) context).withText(title).withMedia(umVideo).setPlatform(platform).setCallback(shareListener).share();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(context, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            // SocializeUtils.safeCloseDialog(dialog);
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //SocializeUtils.safeCloseDialog(dialog);
        }
    };

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.play_type)
        Button play_type;
        @BindView(R.id.list_video_pic)
        ImageView list_video_pic;
        @BindView(R.id.jiami)
        EditText jiami;
        @BindView(R.id.user_icon)
        CircleImageView user_icon;
        @BindView(R.id.return_title)
        TextView return_title;
        @BindView(R.id.company_title)
        TextView company_title;
        @BindView(R.id.look_people)
        TextView look_people;
        @BindView(R.id.btn_shared)
        Button btn_shared;
        @BindView(R.id.create_time)
        TextView create_time;
        @BindView(R.id.hot_live_relative)
        AutoLinearLayout hot_live_relative;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                int adapterPosition = getAdapterPosition()-1;
                BestNewModel.APIDATABean.RetBean.ListBean listBean = bestAdapter.get(adapterPosition);
                String type = listBean.getType();
                String live_type = listBean.getLive_type();
                String video_pic = listBean.getVideo_pic();
                String string = play_type.getText().toString();
                if ("1".equals(live_type)){
                    elseType(listBean, type, video_pic, string);
                }else if ("2".equals(live_type)){
                    elseType(listBean, type, video_pic, "预告");
                    Log.e("live_type",live_type+"==============================live_type");
                }

            }catch (Exception e){
                LogUtils.e(e.toString());
            }

        }

        private void elseType(BestNewModel.APIDATABean.RetBean.ListBean listBean, String type, String video_pic, String string) {
            if (type.equals("1")){
                Log.e("follow_number",type+"***************===================");
                String cid = listBean.getCid();
                getLiveInfo(cid,string,video_pic);
                jiami.setVisibility(View.GONE);
            }else if (type.equals("2")){
                Log.e("follow_number",type+"++++++++++++++++===================");
                Toast.makeText(context, "请支付观看所有费用!", Toast.LENGTH_SHORT).show();
                jiami.setVisibility(View.GONE);
            }else if (type.equals("3")){
                String s = jiami.getText().toString();
                String type_value = listBean.getType_value();
                Log.e("follow_number",type+"===================");
                if (!s.equals(type_value)){
                    Toast.makeText(context, "请输入加密密码!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class editencryptContent implements TextWatcher{

        private String cid;
        private String string;
        private String video_pic;
        private String type_value;

        public editencryptContent(String cid, String string, String video_pic,String type_value) {
            this.cid = cid;
            this.string = string;
            this.video_pic = video_pic;
            this.type_value = type_value;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String s = editable.toString();
            if (s.equals(type_value)){
                getLiveInfo(cid,string,video_pic);
            }
        }
    }
}
