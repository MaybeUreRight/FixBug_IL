package com.xgkj.ilive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.mvp.model.BrowseRecordsVideoModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/9 0009 10:42
 */

public class BrowseRecordsVideoAdapter extends RecyclerView.Adapter<BrowseRecordsVideoAdapter.BrowseRecordsVideViewHolder>{

    private List<BrowseRecordsVideoModel.APIDATABean.RetBean.ListBean> addVideoList;

    public void setData(List<BrowseRecordsVideoModel.APIDATABean.RetBean.ListBean> addVideoList){
        this.addVideoList = addVideoList;
    }

    @Override
    public BrowseRecordsVideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_records_live_item, parent, false);
        return new BrowseRecordsVideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BrowseRecordsVideViewHolder holder, int position) {
        BrowseRecordsVideoModel.APIDATABean.RetBean.ListBean listBean = addVideoList.get(position);
        Glide.with(holder.itemView.getContext()).load(listBean.getVideo_pic()).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(holder.browse_records_img);
        holder.enterprise_name.setText(listBean.getUsername());
        holder.enterprise_all.setText("所属部分:"+listBean.getIntroduce());
        String s = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated() + "000"));
        holder.enterprise_time.setText(s);
    }

    @Override
    public int getItemCount() {
        return addVideoList!=null?addVideoList.size():0;
    }

    class BrowseRecordsVideViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.browse_records_img)
        ImageView browse_records_img;
        @BindView(R.id.enterprise_name)
        TextView enterprise_name;
        @BindView(R.id.enterprise_all)
        TextView enterprise_all;
        @BindView(R.id.enterprise_time)
        TextView enterprise_time;

        public BrowseRecordsVideViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
