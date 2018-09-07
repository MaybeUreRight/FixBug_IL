package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.TaHomeDetailsActivity;
import com.xgkj.ilive.mvp.model.MineAttentionModel;
import com.xgkj.ilive.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/9 0009 11:21
 */

public class MineAttentionAdapter extends RecyclerView.Adapter<MineAttentionAdapter.MineAttentionViewHolder>{

    private Context context;

    private List<MineAttentionModel.APIDATABean.RetBean.ListBean> addAttenTionList;
    public void setData(List<MineAttentionModel.APIDATABean.RetBean.ListBean> addAttenTionList) {
        this.addAttenTionList = addAttenTionList;
    }

    public MineAttentionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MineAttentionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_attention_item, parent, false);
        return new MineAttentionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MineAttentionViewHolder holder, int position) {
        MineAttentionModel.APIDATABean.RetBean.ListBean listBean = addAttenTionList.get(position);
        Glide.with(holder.itemView.getContext()).load(listBean.getTo_pic()).asBitmap().placeholder(R.drawable.mine_circle_icon).error(R.drawable.mine_circle_icon).into(holder.attention_icon);
        holder.attention_name.setText(listBean.getTo_username());
    }

    @Override
    public int getItemCount() {
        return addAttenTionList!=null?addAttenTionList.size():0;
    }



    class MineAttentionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.attention_icon)
        CircleImageView attention_icon;
        @BindView(R.id.attention_name)
        TextView attention_name;

        public MineAttentionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition()-1;
            Intent intent = new Intent(context, TaHomeDetailsActivity.class);
            intent.putExtra("to_uid",addAttenTionList.get(adapterPosition).getTo_uid());
            context.startActivity(intent);
        }
    }
}
