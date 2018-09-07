package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.InfotmationDetailActivity;
import com.xgkj.ilive.mvp.model.PublishInformationModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 18:41
 */

public class PublishInfoAdapter extends RecyclerView.Adapter<PublishInfoAdapter.PublishInfoViewHolder>{

    private List<PublishInformationModel.APIDATABean.RetBean.ListBean> infoListAll;
    private Context context;

    public PublishInfoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PublishInformationModel.APIDATABean.RetBean.ListBean> infoListAll){
        notifyDataSetChanged();
        this.infoListAll = infoListAll;
    }
    @Override
    public PublishInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publish_info_item, parent, false);
        return new PublishInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublishInfoViewHolder holder, int position) {
        PublishInformationModel.APIDATABean.RetBean.ListBean listBean = infoListAll.get(position);

        Glide.with(context).load(listBean.getPic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.info_pic);
        //设置标题加粗
        TextPaint tp = holder.info_title.getPaint();
        tp.setFakeBoldText(true);
        holder.info_title.setText(listBean.getTitle());

        holder.info_content.setText(Html.fromHtml(listBean.getContent()));

        /**
         * php 时间戳 10位
         * java 时间戳 13位
         * 在php后面的时间戳乘1000*/
        String s = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated()+"000"));
        holder.info_time.setText(s);

    }

    @Override
    public int getItemCount() {
        return infoListAll!=null?infoListAll.size():0;
    }

    class PublishInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.info_pic)
        ImageView info_pic;
        @BindView(R.id.info_title)
        TextView info_title;
        @BindView(R.id.info_content)
        TextView info_content;
        @BindView(R.id.info_time)
        TextView info_time;

        public PublishInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition()-1;
            PublishInformationModel.APIDATABean.RetBean.ListBean listBean = infoListAll.get(adapterPosition);
            String id = listBean.getId();
            Intent intent = new Intent(context, InfotmationDetailActivity.class);
            intent.putExtra("id",id);
            context.startActivity(intent);
        }
    }


}
