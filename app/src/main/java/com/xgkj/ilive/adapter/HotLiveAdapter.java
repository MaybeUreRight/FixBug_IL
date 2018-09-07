package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.HomeModel;
import com.xgkj.ilive.mvp.model.LiveDetailsModel;
import com.xgkj.ilive.net.NetUrl;
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
 * 日期: 2017/8/12 0012 11:08
 */

public class HotLiveAdapter extends RecyclerView.Adapter<HotLiveAdapter.HotViewHolder>{

    private Context context;
    private List<HomeModel.APIDATABean.LiveListBean> live_list;

    public HotLiveAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_gridview, parent, false);
        return new HotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        HomeModel.APIDATABean.LiveListBean liveListBean = live_list.get(position);
        Glide.with(context).load(liveListBean.getPic())
                .apply(App.requestOptions.placeholder(R.drawable.default_pic).error(R.drawable.default_pic))
                .into(holder.hot_video_pic);
        holder.hot_video_title.setText(liveListBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return live_list!=null?live_list.size():0;
    }

    public void setData(List<HomeModel.APIDATABean.LiveListBean> live_list) {
        this.live_list = live_list;
    }

    class HotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.hot_video_pic)
        ImageView hot_video_pic;
        @BindView(R.id.hot_video_title)
        TextView hot_video_title;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            HomeModel.APIDATABean.LiveListBean liveListBean = live_list.get(adapterPosition);
            String cid = liveListBean.getCid();
            String video_pic = liveListBean.getPic();
            getLiveInfo(cid,video_pic);
        }
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
