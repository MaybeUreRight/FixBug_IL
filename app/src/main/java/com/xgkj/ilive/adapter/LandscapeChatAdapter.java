package com.xgkj.ilive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/23 0023 14:59
 */

public class LandscapeChatAdapter extends RecyclerView.Adapter<LandscapeChatAdapter.LandscapeChatViewHolder> {

    private List<QueryChatMessageModel.APIDATABean.RetBean> ret;

    public void setData(List<QueryChatMessageModel.APIDATABean.RetBean> ret) {
        this.ret = ret;
    }

    @Override
    public LandscapeChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_stream_chatroom_items, parent, false);
        return new LandscapeChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LandscapeChatViewHolder holder, int position) {
        QueryChatMessageModel.APIDATABean.RetBean retBean = ret.get(position);
        Glide.with(holder.itemView.getContext()).load(retBean.getPic()).asBitmap().placeholder(R.drawable.chat_icon).error(R.drawable.chat_icon).into(holder.live_stream_icon);
        holder.live_stream_content.setText(retBean.getNickname()+":"+retBean.getFiltercontent());
    }

    @Override
    public int getItemCount() {
        return ret != null ? ret.size() : 0;
    }

    class LandscapeChatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.live_stream_icon)
        ImageView live_stream_icon;
        @BindView(R.id.live_stream_content)
        TextView live_stream_content;

        public LandscapeChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
