package com.xgkj.ilive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.WhoMayLookActivity;
import com.xgkj.ilive.mvp.model.LookStatusModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/4 0004 15:46
 */

public class WhoIsLookAdapter extends RecyclerView.Adapter<WhoIsLookAdapter.WhoIsLookViewHolder>{

    private List<LookStatusModel> list;
    private int mSelectedItem = -1;
    private Context context;

    public WhoIsLookAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LookStatusModel> list){
        this.list = list;
    }

    @Override
    public WhoIsLookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.look_item, parent, false);
        return new WhoIsLookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WhoIsLookViewHolder holder, int position) {
        LookStatusModel lookStatusModel = list.get(position);
        holder.select_way.setText(lookStatusModel.getLook_status());
        holder.select_description.setText(lookStatusModel.getLook_description());
        if (mSelectedItem == 0){
            holder.look_select.setId(position);
            if (position == mSelectedItem){
                holder.look_select.setImageResource(R.drawable.rb_checked);
            }else {
                holder.look_select.setImageResource(R.drawable.rb_unchecked);
            }
            holder.et_settings_pwd.setVisibility(View.GONE);
            holder.et_settings_pwd.setText("");
        }else if (mSelectedItem == 1){
            if (position == mSelectedItem){
                holder.look_select.setImageResource(R.drawable.rb_checked);
            }else {
                holder.look_select.setImageResource(R.drawable.rb_unchecked);
            }
            Toast.makeText(context, "付费功能暂未开通", Toast.LENGTH_SHORT).show();
            holder.et_settings_pwd.setVisibility(View.GONE);
            holder.et_settings_pwd.setText("");
        }else if (mSelectedItem == 2){
            holder.look_select.setId(position);
            if (position == mSelectedItem){
                holder.look_select.setImageResource(R.drawable.rb_checked);
            }else {
                holder.look_select.setImageResource(R.drawable.rb_unchecked);
            }

            if (position == mSelectedItem){
                holder.et_settings_pwd.setFocusableInTouchMode(true);
                holder.et_settings_pwd.requestFocus();
                holder.et_settings_pwd.setVisibility(View.VISIBLE);
                WhoMayLookActivity liveActivity= (WhoMayLookActivity) context;
                liveActivity.setLookWay(holder.et_settings_pwd.getText().toString());
            }else {
                holder.et_settings_pwd.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    class WhoIsLookViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.look_select)
        ImageView look_select;
        @BindView(R.id.select_way)
        TextView select_way;
        @BindView(R.id.select_description)
        TextView select_description;
        @BindView(R.id.et_settings_pwd)
        EditText et_settings_pwd;

        public WhoIsLookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            View.OnClickListener clickListener = new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, list.size());
                    WhoMayLookActivity liveActivity= (WhoMayLookActivity) context;
                    liveActivity.setSelectText(select_way.getText().toString(),mSelectedItem);
                }
            };
            itemView.setOnClickListener(clickListener);
            look_select.setOnClickListener(clickListener);
        }

    }
}
