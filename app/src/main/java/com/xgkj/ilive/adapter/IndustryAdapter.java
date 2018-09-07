package com.xgkj.ilive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.IndustryActivity;
import com.xgkj.ilive.mvp.model.IndustryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/8/10 0010 11:02
 */

public class IndustryAdapter extends RecyclerView.Adapter<IndustryAdapter.IndustryViewHolder>{

    private Context context;
    private  List<IndustryModel.APIDATABean.ListBean> list;

    public void setData(Context context, List<IndustryModel.APIDATABean.ListBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public IndustryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.industry_item, parent, false);
        return new IndustryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IndustryViewHolder holder, int position) {
        IndustryModel.APIDATABean.ListBean listBean = list.get(position);
        String title = listBean.getTitle();
        holder.tv_industry.setText(title);
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    class IndustryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_industry)
        TextView tv_industry;

        public IndustryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            IndustryModel.APIDATABean.ListBean listBean = list.get(adapterPosition);
            IndustryActivity industry= (IndustryActivity) context;
            industry.setIndustry(listBean.getId(),listBean.getTitle());
        }
    }
}
