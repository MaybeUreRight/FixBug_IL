package com.xgkj.ilive.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.mvp.model.NewsSerachModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/15 0015 14:35
 * 作用：视频结果展示
 */

public class SerachVideoAdapter extends RecyclerView.Adapter<SerachVideoAdapter.SerachVideoViewHolder> {

    private List<NewsSerachModel.APIDATABean.RetBean.VideoListBean> video_list;

    public void setData(List<NewsSerachModel.APIDATABean.RetBean.VideoListBean> video_list) {
        this.video_list = video_list;
    }

    @Override
    public SerachVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serach_info_video_item, parent, false);
        return new SerachVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SerachVideoViewHolder holder, int position) {
        NewsSerachModel.APIDATABean.RetBean.VideoListBean videoListBean = video_list.get(position);
        Glide.with(holder.itemView.getContext()).load(videoListBean.getVideo_pic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.info_pic);
        String title = videoListBean.getTitle();
        if (title != null) {
            //设置标题加粗
            TextPaint tp = holder.info_title.getPaint();
            tp.setFakeBoldText(true);
            holder.info_title.setText(title);
        }
        String created = videoListBean.getCreated();
        if (created != null) {
            String s = DateUtil.formatTimeInMillis(Long.parseLong(created + "000"));
            holder.info_time.setText(s);
        }

        String company_title = videoListBean.getCompany_title();
        if (company_title != null){
            holder.affiliated_enterprise.setText(company_title);
        }
    }

    @Override
    public int getItemCount() {
        return video_list != null ? video_list.size() : 0;
    }

    class SerachVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.info_pic)
        ImageView info_pic;
        @BindView(R.id.info_title)
        TextView info_title;
        @BindView(R.id.affiliated_enterprise)
        TextView affiliated_enterprise;
        @BindView(R.id.info_time)
        TextView info_time;

        public SerachVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
