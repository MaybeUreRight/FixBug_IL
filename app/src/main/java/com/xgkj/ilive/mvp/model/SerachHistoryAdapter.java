package com.xgkj.ilive.mvp.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xgkj.ilive.R;
import com.xgkj.ilive.db.DBManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/15 0015 16:13
 * 作用：历史记录的展示
 */

public class SerachHistoryAdapter extends RecyclerView.Adapter<SerachHistoryAdapter.SerachHistoryViewHolder>{

    private List<DBSerachModel> list;

    public void setData( List<DBSerachModel> list){
        this.list = list;
        this.notifyDataSetChanged();
    }



    @Override
    public SerachHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new SerachHistoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final SerachHistoryViewHolder holder, final int position) {
        DBSerachModel dbSerachModel = list.get(position);
        String data = dbSerachModel.getData();
        if (data!=null) {
            holder.tv_history.setText(data);
        }
        Log.e("onBindViewHolder",list.size()+"==========================");
        if (list.size() -1 == position){
            holder.clear_history_serach.setVisibility(View.VISIBLE);
            holder.clear_history_serach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBManager.getInstance().deleteData();
                    Toast.makeText(holder.itemView.getContext(), "历史记录清除成功!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            holder.clear_history_serach.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return list!=null? list.size():0;
    }

    class SerachHistoryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_history)
        TextView tv_history;
        @BindView(R.id.clear_history_serach)
        TextView clear_history_serach;

        public SerachHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
