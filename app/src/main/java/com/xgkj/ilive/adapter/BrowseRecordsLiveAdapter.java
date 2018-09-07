package com.xgkj.ilive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.BrowseRecordsLiveModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/8 0008 17:55
 */

public class BrowseRecordsLiveAdapter extends RecyclerView.Adapter<BrowseRecordsLiveAdapter.BrowseRecordsLiveViewHolder>{

    private List<BrowseRecordsLiveModel.APIDATABean.RetBean.ListBean> list;
    public void setData(List<BrowseRecordsLiveModel.APIDATABean.RetBean.ListBean> list){
        this.list = list;
    }

    @Override
    public BrowseRecordsLiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_records_live_item, parent, false);
        return new BrowseRecordsLiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BrowseRecordsLiveViewHolder holder, int position) {
        BrowseRecordsLiveModel.APIDATABean.RetBean.ListBean listBean = list.get(position);
        Glide.with(holder.itemView.getContext()).load(listBean.getVideo_pic()).apply(App.requestOptions).into(holder.browse_records_img);
        holder.enterprise_name.setText(listBean.getUsername());
        holder.enterprise_all.setText("所属部分:"+listBean.getIntroduce());
        String s = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated() + "000"));
        holder.enterprise_time.setText(s);
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size() :0;
    }

    class BrowseRecordsLiveViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.browse_records_img)
        ImageView browse_records_img;
        @BindView(R.id.enterprise_name)
        TextView enterprise_name;
        @BindView(R.id.enterprise_all)
        TextView enterprise_all;
        @BindView(R.id.enterprise_time)
        TextView enterprise_time;

        public BrowseRecordsLiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
