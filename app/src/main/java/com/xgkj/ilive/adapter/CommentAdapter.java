package com.xgkj.ilive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.utils.emoji.EmojiConversionUtils;
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
 * 日期: 2017/8/23 0023 10:25
 * 作用: 点播评论的适配器
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    private List<QueryChatMessageModel.APIDATABean.RetBean> ret;
    private Context context;

    public CommentAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据
     * @param ret
     */
    public void setData(List<QueryChatMessageModel.APIDATABean.RetBean> ret){
            this.ret = ret;
    }



    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        QueryChatMessageModel.APIDATABean.RetBean retBean = ret.get(position);
        String nickname = retBean.getNickname();
        holder.commet_nickname.setText(nickname);

        String content = retBean.getFiltercontent();
        SpannableString spannableString = EmojiConversionUtils.INSTANCE.getExpressionString(holder.itemView.getContext(),content);
        holder.commet_content.setText(spannableString);

        Date currentTime = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(currentTime);
        String time = retBean.getTime();
        try {
            Date parse = df.parse(time);
            Date parse1 = df.parse(format);
            long diff = parse1.getTime() - parse.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            if (days == 0){
                if (hours <1){
                    holder.commet_time.setText(minutes+"分钟前");
                }else {
                    holder.commet_time.setText(hours+"小时前");
                }
            }else {
                holder.commet_time.setText(days+"天前");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
       // holder.commet_time.setText(retBean.getTime());
        Glide.with(holder.itemView.getContext()).asBitmap().load(retBean.getPic()).apply(App.requestOptions).into(holder.commet_icon);
    }

    @Override
    public int getItemCount() {
        return ret!=null?ret.size():0;
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        
        @BindView(R.id.commet_icon)
        CircleImageView commet_icon;
        @BindView(R.id.commet_nickname)
        TextView commet_nickname;
        @BindView(R.id.commet_time)
        TextView commet_time;
        @BindView(R.id.commet_content)
        TextView commet_content;
        
        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
