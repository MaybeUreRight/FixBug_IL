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
import com.xgkj.ilive.activity.PublishVideoDetailsActivity;
import com.xgkj.ilive.mvp.model.LiveDetailsModel;
import com.xgkj.ilive.mvp.model.NewsSerachModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.DateUtil;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

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
 * 日期: 2017/8/17 0017 15:19
 */

public class SerachLiveAdapter extends RecyclerView.Adapter<SerachLiveAdapter.SerachLiveViewHolder>{

    private List<NewsSerachModel.APIDATABean.RetBean.LiveListBean> live_list;

    public void  setData(List<NewsSerachModel.APIDATABean.RetBean.LiveListBean> live_list){
        this.live_list = live_list;
    }

    @Override
    public SerachLiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serach_info_video_item, parent, false);
        return new SerachLiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SerachLiveViewHolder holder, int position) {
        NewsSerachModel.APIDATABean.RetBean.LiveListBean liveListBean = live_list.get(position);
        String pic = liveListBean.getPic();
        if (pic !=null){
            Glide.with(holder.itemView.getContext()).load(pic).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.info_pic);
        }
        String company_title = liveListBean.getCompany_title();
        if (company_title!=null){
            holder.affiliated_enterprise.setText(company_title);
        }

        String title = liveListBean.getTitle();
        if (title!=null) {
            //设置标题加粗
            TextPaint tp = holder.info_title.getPaint();
            tp.setFakeBoldText(true);
            holder.info_title.setText(title);
        }

        String live_time = liveListBean.getLive_time();
        String s = DateUtil.formatTimeInMillis(Long.parseLong(live_time + "000"));
        holder.info_time.setText(s);
    }

    @Override
    public int getItemCount() {
        return live_list!=null?live_list.size():0;
    }

    class SerachLiveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.info_pic)
        ImageView info_pic;
        @BindView(R.id.info_title)
        TextView info_title;
        @BindView(R.id.affiliated_enterprise)
        TextView affiliated_enterprise;
        @BindView(R.id.info_time)
        TextView info_time;
        public SerachLiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            NewsSerachModel.APIDATABean.RetBean.LiveListBean liveListBean = live_list.get(adapterPosition);
            String cid = liveListBean.getCid();
            getLiveInfo(cid,view.getContext());
        }
    }

    private void getLiveInfo(String cid, final Context context) {
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
