package com.xgkj.ilive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.HighlightsActivity;
import com.xgkj.ilive.activity.HotInformationActivity;
import com.xgkj.ilive.activity.HotVideoActivity;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.log.LogUtils;
import com.xgkj.ilive.mvp.model.AdvertModel;
import com.xgkj.ilive.mvp.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.umeng.analytics.pro.x.W;

/**
 * 作者：刘净辉
 * 日期: 2017/7/16 0016 16:31
 * 作用：用来展示首页的推荐列表
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<HomeModel.APIDATABean.LiveListBean> livenow_list;
    private List<HomeModel.APIDATABean.NewsListBean> news_list;
    private List<HomeModel.APIDATABean.VideoListBean> video_list;
    private List<HomeModel.APIDATABean.LiveListBean> live_list;
    List<AdvertModel.APIDATABean.RetBean.ListBean> mAdvertList;

    //type
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
    public static final int TYPE_5 = 0xff05;
    public static final int TYPE_MAIN = 0xffff;

    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(HomeModel.APIDATABean apidata) {
        try {
            if (apidata != null) {
                //热门资讯
                news_list = apidata.getNews_list();
                //热门视频
                video_list = apidata.getVideo_list();
                //精彩一样
                live_list = apidata.getLive_list();
                //正在直播
                livenow_list = apidata.getLivenow_list();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e.toString());
        }

    }

    public void setAdvertData(List<AdvertModel.APIDATABean.RetBean.ListBean> mAdvertList) {
        if (mAdvertList == null || mAdvertList.size() <= 0) {
            return;
        }
        try {
            this.mAdvertList = mAdvertList;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e.toString());
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_2:
                        case TYPE_4:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new SlidViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycle_item_slider, parent, false));
            case TYPE_2:
                return new HotVideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_list, parent, false));
            case TYPE_3:
                return new HotInformationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_new_item, parent, false));
            case TYPE_4:
                return new HotLiveViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_live_item, parent, false));
            case TYPE_5:
                return new LivingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.living_item, parent, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SlidViewPagerViewHolder) {
            SlidViewPagerViewHolder slidePagerHolder = (SlidViewPagerViewHolder) holder;
            setSliderPager(slidePagerHolder, position);
        } else if (holder instanceof HotVideoViewHolder) {
            HotVideoViewHolder hotviewHolder = (HotVideoViewHolder) holder;
            bindHotVideoType(hotviewHolder, position);
        } else if (holder instanceof HotInformationViewHolder) {
            HotInformationViewHolder informationViewHolder = (HotInformationViewHolder) holder;
            bindHotInfoType(informationViewHolder, position);
        } else if (holder instanceof HotLiveViewHolder) {
            HotLiveViewHolder hotLiveViewHolder = (HotLiveViewHolder) holder;
            bindLiveType(hotLiveViewHolder, position);
        }else if (holder instanceof LivingViewHolder){
            if (null != livenow_list || livenow_list.size() != 0){
                LivingViewHolder livingViewHolder = (LivingViewHolder) holder;
                bindLivingType(livingViewHolder,position);
            }
        }
    }

    /**
     * 正在直播数据
     *
     * @param
     * @param position
     */
    private void bindLivingType(LivingViewHolder livingViewHolder, int position) {
        livingViewHolder.home_living_recycler.setLayoutManager(new GridLayoutManager(context, 2));
        HotLiveAdapter hotLiveAdapter = new HotLiveAdapter(context);
        hotLiveAdapter.setData(livenow_list);
        livingViewHolder.home_living_recycler.setAdapter(hotLiveAdapter);

        livingViewHolder.tv_living_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HighlightsActivity.class));
            }
        });
    }
    /**
     * 精彩回放数据
     *
     * @param hotLiveViewHolder
     * @param position
     */
    private void bindLiveType(HotLiveViewHolder hotLiveViewHolder, int position) {
        hotLiveViewHolder.home_live_recycler.setLayoutManager(new GridLayoutManager(context, 2));
        HotLiveAdapter hotLiveAdapter = new HotLiveAdapter(context);
        hotLiveAdapter.setData(live_list);
        hotLiveViewHolder.home_live_recycler.setAdapter(hotLiveAdapter);

        hotLiveViewHolder.tv_highlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HighlightsActivity.class));
            }
        });
    }

    /**
     * 热门资讯的数据
     *
     * @param informationViewHolder
     * @param position
     */
    private void bindHotInfoType(HotInformationViewHolder informationViewHolder, int position) {
        informationViewHolder.home_information_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        HotInformationAdapter hotInformationAdapter = new HotInformationAdapter(context);
        if (news_list != null)
            hotInformationAdapter.setData(news_list);
        informationViewHolder.home_information_recycler.setAdapter(hotInformationAdapter);

        informationViewHolder.hot_info_many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HotInformationActivity.class));
            }
        });
    }

    /**
     * 热门视频的数据
     *
     * @param hotviewHolder
     * @param position
     */
    private void bindHotVideoType(HotVideoViewHolder hotviewHolder, int position) {
        hotviewHolder.home_video_recycler.setLayoutManager(new GridLayoutManager(context, 2));
        HotVideoAdapter hotVideoAdapter = new HotVideoAdapter(context);
        if (video_list != null)
            hotVideoAdapter.setData(video_list);
        hotviewHolder.home_video_recycler.setAdapter(hotVideoAdapter);

        //热门视频 点击更多按钮
        hotviewHolder.home_video_many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HotVideoActivity.class));
            }
        });
    }

    /**
     * 无限轮播的使用
     *
     * @param slidePagerHolder
     * @param position
     */
    private void setSliderPager(SlidViewPagerViewHolder slidePagerHolder, int position) {

        List<String> list = new ArrayList<>();
        if (mAdvertList != null) {
            for (int i = 0; i < mAdvertList.size(); i++) {
                AdvertModel.APIDATABean.RetBean.ListBean listBean = mAdvertList.get(i);
                list.add(listBean.getPic());
            }
        }
        //开始自动翻页
        slidePagerHolder.convenientBanner.startTurning(4000);
        slidePagerHolder.convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, list).setPageIndicator(new int[]{R.drawable.circle_unchecked, R.drawable.circle_checked}).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        slidePagerHolder.convenientBanner.startTurning(2000);
        slidePagerHolder.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if ("100".equals(mAdvertList.get(position).getVideo_type()) && !TextUtils.isEmpty(mAdvertList.get(position).getUrl())){
                    WebView webView = new WebView(context);
                    webView.loadUrl(mAdvertList.get(position).getUrl());
                }
            }
        });
    }

    public class NetworkImageHolderView implements Holder<String> {

        private ImageView view;

        @Override
        public View createView(Context context) {
            view = new ImageView(context);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load(data)
                    .apply(App.requestOptions.placeholder(R.drawable.default_pic).error(R.drawable.default_pic))
                    .into(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (null == livenow_list || livenow_list.size() == 0){
            if (position == 0) {
                return TYPE_1;
            } else if (position == 1){
                return TYPE_2;
            }else if (position == 2) {
                return TYPE_3;
            } else if (position == 3) {
                return TYPE_4;
            }
        }else {
            if (position == 0) {
                return TYPE_1;
            } else if (position == 1){
                return TYPE_5;
            }else if (position == 2) {
                return TYPE_2;
            } else if (position == 3) {
                return TYPE_3;
            } else if (position == 4) {
                return TYPE_4;
            }
        }
        return TYPE_MAIN;

    }

    @Override
    public int getItemCount() {
        if (null == livenow_list || livenow_list.size() == 0){
            return 4;
        }else {
            return 5;
        }
    }


    /**
     * 无限轮播的viewholder
     */
    class SlidViewPagerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.convenientBanner)
        ConvenientBanner convenientBanner;

        public SlidViewPagerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 热门视频
     */
    class HotVideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_video_recycler)
        RecyclerView home_video_recycler;
        @BindView(R.id.home_video_many)
        TextView home_video_many;

        public HotVideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 热门资讯
     */
    class HotInformationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_information_recycler)
        RecyclerView home_information_recycler;
        @BindView(R.id.hot_info_many)
        TextView hot_info_many;

        public HotInformationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 精彩回放
     */
    class HotLiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_live_recycler)
        RecyclerView home_live_recycler;
        @BindView(R.id.tv_highlights)
        TextView tv_highlights;
        public HotLiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 正在直播
     */
    class LivingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_living_recycler)
        RecyclerView home_living_recycler;
        @BindView(R.id.tv_living_more)
        TextView tv_living_more;
        public LivingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
