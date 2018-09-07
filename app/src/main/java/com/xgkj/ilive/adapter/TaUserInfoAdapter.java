package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.InfotmationDetailActivity;
import com.xgkj.ilive.activity.PublishVideoDetailsActivity;
import com.xgkj.ilive.activity.TaHomeDetailsActivity;
import com.xgkj.ilive.mvp.model.HomeModel;
import com.xgkj.ilive.mvp.model.LiveDetailsModel;
import com.xgkj.ilive.mvp.model.MineAttentionModel;
import com.xgkj.ilive.mvp.model.bean.TaHistoryListBean;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.DateUtil;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.CircleImageView;

import org.json.JSONObject;

import java.util.ArrayList;
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
 * 日期: 2017/8/9 0009 11:21
 */

public class TaUserInfoAdapter extends RecyclerView.Adapter<TaUserInfoAdapter.MineAttentionViewHolder>{

    private Context context;

    private List<TaHistoryListBean.ListBean> addAttenTionList;
    private String type;

    public void setData(List<TaHistoryListBean.ListBean> addAttenTionList) {
        this.addAttenTionList = addAttenTionList;
    }

    public TaUserInfoAdapter(Context context,String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public MineAttentionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new MineAttentionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MineAttentionViewHolder holder, int position) {
        TaHistoryListBean.ListBean listBean = addAttenTionList.get(position);
        Glide.with(context).load(listBean.getVideo_pic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.new_pic);
        //设置标题加粗
        TextPaint tp = holder.news_title.getPaint();
        tp.setFakeBoldText(true);
        holder.news_title.setText(listBean.getTitle());
        holder.news_content.setText(listBean.getUsername());

        String millis = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated() + "000"));
        holder.hot_infotmation_time.setText(millis);
    }

    @Override
    public int getItemCount() {
        return addAttenTionList!=null?addAttenTionList.size():0;
    }



    class MineAttentionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.new_pic)
        ImageView new_pic;
        @BindView(R.id.news_title)
        TextView news_title;
        @BindView(R.id.news_content)
        TextView news_content;
        @BindView(R.id.hot_infotmation_time)
        TextView hot_infotmation_time;

        public MineAttentionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition()-1;
            TaHistoryListBean.ListBean listBean = addAttenTionList.get(adapterPosition);
            if ("1".equals(type)){
                getLiveInfo(listBean.getCid(),listBean.getVideo_pic());
            }else {
                Intent intent = new Intent(context, PublishVideoDetailsActivity.class);
                intent.putExtra("cid",listBean.getCid());
                intent.putExtra("live_type",listBean.getLive_type());
                context.startActivity(intent);
            }
        }
    }

    public void resetUserData(List<TaHistoryListBean.ListBean> list) {
        if (null == addAttenTionList) {
            addAttenTionList = new ArrayList<>();
        }
        addAttenTionList.addAll(list);
        notifyDataSetChanged();
    }

    private void getLiveInfo(final String cid, final String video_pic) {
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
                            intent.putExtra("title",title);
                            intent.putExtra("created",created);
                            intent.putExtra("type","回放");
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
}
