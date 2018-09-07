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

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.InfotmationDetailActivity;
import com.xgkj.ilive.mvp.model.HomeModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 16:04
 */

public class HotInformationAdapter extends RecyclerView.Adapter<HotInformationAdapter.HotInfoViewHolder> {

    private List<HomeModel.APIDATABean.NewsListBean> news_list;
    private Context context;

    public HotInformationAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HomeModel.APIDATABean.NewsListBean> news_list) {
        this.news_list = news_list;
    }

    @Override
    public HotInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        return new HotInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotInfoViewHolder holder, int position) {
        HomeModel.APIDATABean.NewsListBean newsListBean = news_list.get(position);

        Glide.with(context).load(newsListBean.getPic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.new_pic);
        //设置标题加粗
        TextPaint tp = holder.news_title.getPaint();
        tp.setFakeBoldText(true);
        holder.news_title.setText(newsListBean.getTitle());

        holder.news_content.setText(newsListBean.getDescript());

        String millis = DateUtil.formatTimeInMillis(Long.parseLong(newsListBean.getCreated() + "000"));
        holder.hot_infotmation_time.setText(millis);
    }

    @Override
    public int getItemCount() {
        return news_list != null ? news_list.size() : 0;
    }

    class HotInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.new_pic)
        ImageView new_pic;
        @BindView(R.id.news_title)
        TextView news_title;
        @BindView(R.id.news_content)
        TextView news_content;
        @BindView(R.id.hot_infotmation_time)
        TextView hot_infotmation_time;

        public HotInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            HomeModel.APIDATABean.NewsListBean listBean = news_list.get(adapterPosition);
            String id = listBean.getId();
            Intent intent = new Intent(context, InfotmationDetailActivity.class);
            intent.putExtra("id",id);
            context.startActivity(intent);
        }
    }
}
