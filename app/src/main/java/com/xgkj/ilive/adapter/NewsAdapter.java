package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.InfotmationDetailActivity;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.NewsSerachModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/14 0014 17:58
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsSerachModel.APIDATABean.RetBean.NewsListBean> news_list;
    private Context context;

    public NewsAdapter(List<NewsSerachModel.APIDATABean.RetBean.NewsListBean> news_list, Context context) {
        this.news_list = news_list;
        this.context = context;
        Log.e("news_list", "**********************************" + news_list.toString());
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publish_info_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsSerachModel.APIDATABean.RetBean.NewsListBean newsListBean = news_list.get(position);
        Glide.with(context).load(newsListBean.getPic()).apply(App.requestOptions).into(holder.info_pic);
        //设置标题加粗
        TextPaint tp = holder.info_title.getPaint();
        tp.setFakeBoldText(true);
        holder.info_title.setText(newsListBean.getTitle());

        String created = newsListBean.getCreated();
        if (created != null || !"".equals(created)) {
            /**
             * php 时间戳 10位
             * java 时间戳 13位
             * 在php后面的时间戳乘1000*/
            String s = DateUtil.formatTimeInMillis(Long.parseLong(created + "000"));
            holder.info_time.setText(s);
        }
    }

    @Override
    public int getItemCount() {
        return news_list != null ? news_list.size() : 0;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.info_pic)
        ImageView info_pic;
        @BindView(R.id.info_title)
        TextView info_title;
        @BindView(R.id.info_content)
        TextView info_content;
        @BindView(R.id.info_time)
        TextView info_time;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            NewsSerachModel.APIDATABean.RetBean.NewsListBean newsListBean = news_list.get(adapterPosition);
            String id = newsListBean.getId();
            Intent intent = new Intent(context, InfotmationDetailActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }
}
