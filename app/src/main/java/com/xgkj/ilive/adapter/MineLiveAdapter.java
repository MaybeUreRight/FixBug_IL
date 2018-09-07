package com.xgkj.ilive.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.MineLiveModel;
import com.xgkj.ilive.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/9 0009 11:54
 */

public class MineLiveAdapter extends RecyclerView.Adapter<MineLiveAdapter.MineLiveViewHolder> {

    private List<MineLiveModel.APIDATABean.RetBean.ListBean> liveList;

    public void setData(List<MineLiveModel.APIDATABean.RetBean.ListBean> liveList) {
        this.liveList = liveList;
        Log.e("liveList", liveList.size() + "");
    }

    @Override
    public MineLiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_live_item, parent, false);
        return new MineLiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MineLiveViewHolder holder, int position) {
        MineLiveModel.APIDATABean.RetBean.ListBean listBean = liveList.get(position);
        Glide.with(holder.itemView.getContext()).load(listBean.getVideo_pic())
                .apply(App.requestOptions.placeholder(R.drawable.default_pic).error(R.drawable.default_pic))
                .into(holder.video_pic);
        holder.mine_live_title.setText(listBean.getTitle());
        String s = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated() + "000"));
        holder.live_create_time.setText(s);
    }

    @Override
    public int getItemCount() {
        return liveList != null ? liveList.size() : 0;
    }


    class MineLiveViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_pic)
        ImageView video_pic;
        @BindView(R.id.mine_live_title)
        TextView mine_live_title;
        @BindView(R.id.live_create_time)
        TextView live_create_time;

        public MineLiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
