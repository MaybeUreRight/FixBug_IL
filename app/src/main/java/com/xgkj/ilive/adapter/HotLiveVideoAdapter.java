package com.xgkj.ilive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xgkj.ilive.R;
import com.xgkj.ilive.mvp.model.HotModel;
import com.xgkj.ilive.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 13:03
 */

public class HotLiveVideoAdapter extends RecyclerView.Adapter<HotLiveVideoAdapter.HotLiveVideoViewHolder>{

    private Context context;
    private List<HotModel.APIDATABean.RetBean.VideoListBean> video_list;

    public HotLiveVideoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HotModel.APIDATABean.RetBean.VideoListBean> video_list){
        if (video_list == null){
            return;
        }
        notifyDataSetChanged();
        this.video_list = video_list;
    }

    @Override
    public HotLiveVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_live_video_item, parent, false);
        return new HotLiveVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotLiveVideoViewHolder holder, int position) {
        HotModel.APIDATABean.RetBean.VideoListBean videoListBean = video_list.get(position);

        Glide.with(context).load(videoListBean.getVideo_pic()).error(R.drawable.default_pic).placeholder(R.drawable.default_pic).into(holder.list_video_pic);
        Glide.with(context).load(videoListBean.getUser_pic()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().error(R.drawable.mine_circle_icon).placeholder(R.drawable.mine_circle_icon).into(holder.user_icon);
        holder.return_title.setText(videoListBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return video_list != null?video_list.size() :0;
    }

    class HotLiveVideoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_video_pic)
        ImageView list_video_pic;
        @BindView(R.id.user_icon)
        CircleImageView user_icon;
        @BindView(R.id.return_title)
        TextView return_title;


        public HotLiveVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
