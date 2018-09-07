package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.PublishVideoDetailsActivity;
import com.xgkj.ilive.mvp.model.HomeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 14:55
 */

public class HotVideoAdapter extends RecyclerView.Adapter<HotVideoAdapter.VideoHolder> {

    private List<HomeModel.APIDATABean.VideoListBean> video_list;
    private Context context;

    public HotVideoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HomeModel.APIDATABean.VideoListBean> video_list) {
        this.video_list = video_list;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_gridview, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        HomeModel.APIDATABean.VideoListBean videoListBean = video_list.get(position);
        Glide.with(context).load(videoListBean.getVideo_pic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.hot_video_pic);
        holder.hot_video_title.setText(videoListBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return video_list != null ? video_list.size() : 0;
    }

    class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.hot_video_pic)
        ImageView hot_video_pic;
        @BindView(R.id.hot_video_title)
        TextView hot_video_title;

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            HomeModel.APIDATABean.VideoListBean videoListBean = video_list.get(adapterPosition);
            String cid = videoListBean.getCid();
            String room_id = videoListBean.getRoom_id();
            String live_type = videoListBean.getLive_type();
            Intent intent = new Intent(context, PublishVideoDetailsActivity.class);
            intent.putExtra("cid",cid);
            intent.putExtra("room_id",room_id);
            intent.putExtra("live_type",live_type);
            context.startActivity(intent);
        }
    }
}
