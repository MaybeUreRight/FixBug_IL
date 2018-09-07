package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.PublishVideoDetailsActivity;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.PublishVideoModel;
import com.xgkj.ilive.utils.DateUtil;
import com.xgkj.ilive.view.CircleImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 16:41
 */

public class PublishVideoAdapter extends RecyclerView.Adapter<PublishVideoAdapter.PublishVideoViewHolder> {

    private Context context;
    private List<PublishVideoModel.APIDATABean.RetBean.ListBean> list;

    public PublishVideoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public PublishVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publish_video_item, parent, false);
        return new PublishVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublishVideoViewHolder holder, int position) {

        if (list.size() - 1 == position) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.bottomMargin = 0;
            holder.publish_video.setLayoutParams(params);
        }

        PublishVideoModel.APIDATABean.RetBean.ListBean listBean = list.get(position);
        Glide.with(context).load(listBean.getUser_pic()).apply(App.requestOptions).into(holder.publish_user_icon);
        //实现点击播放按钮进行播放
        holder.look_people.setText(listBean.getVideo_count() + "次播放");
        holder.company_title.setText("企业名称:"+listBean.getCompany_title());
        Glide.with(context).load(listBean.getVideo_pic()).apply(App.requestOptions).into(holder.list_video_pic);

        String live_type = listBean.getLive_type();
        String cid_url = listBean.getCid_url();
        if ("".equals(cid_url) && live_type.equals("1")){
            holder.play_type.setText("回放");
        }else if (!"".equals(cid_url) && live_type.equals("1")){
            holder.play_type.setText("直播");
        }else if ("".equals(cid_url) && live_type.equals("2")){
            holder.create_time.setText("预告");
        }else if (!"".equals(cid_url)&& live_type.equals("2")){
            holder.create_time.setText("预告");
        }

        String s = DateUtil.formatTimeInMillis(Long.parseLong(listBean.getCreated() + "000"));
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String date1 = formatter.format(currentTime);
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Date parse = df.parse(s);
            Date parse1 = df.parse(date1);
            long diff = parse1.getTime() - parse.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            holder.create_time.setText(days+"天前");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.company_title.setText(listBean.getCompany_title());
        holder.return_title.setText(listBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class PublishVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.publish_video)
        LinearLayout publish_video;
        @BindView(R.id.publish_user_icon)
        CircleImageView publish_user_icon;
        @BindView(R.id.return_title)
        TextView return_title;
        @BindView(R.id.create_time)
        TextView create_time;
        @BindView(R.id.play_type)
        Button play_type;
        @BindView(R.id.list_video_pic)
        ImageView list_video_pic;
        @BindView(R.id.company_title)
        TextView company_title;
        @BindView(R.id.look_people)
        TextView look_people;
        @BindView(R.id.btn_shared)
        Button btn_shared;

        public PublishVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //得到当前的条目
            int adapterPosition = getAdapterPosition()-1;
            PublishVideoModel.APIDATABean.RetBean.ListBean listBean = list.get(adapterPosition);
            String cid = listBean.getCid();
            String room_id = listBean.getRoom_id();
            String live_type = listBean.getLive_type();


            Intent intent = new Intent(context, PublishVideoDetailsActivity.class);
            intent.putExtra("cid",cid);
            intent.putExtra("room_id",room_id);
            intent.putExtra("live_type",live_type);
            context.startActivity(intent);
        }
    }
}

