package com.xgkj.ilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.PublishVideoAdapter;
import com.xgkj.ilive.adapter.SpaceItemDecoration;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.HotVideoContract;
import com.xgkj.ilive.mvp.model.PublishVideoModel;
import com.xgkj.ilive.mvp.presenter.HotVideoPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotVideoActivity extends BaseActivity implements HotVideoContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.hot_video_back)
    ImageView hot_video_back;
    @BindView(R.id.publish_serach)
    ImageView publish_serach;
    @BindView(R.id.hot_video_xrecyclerview)
    XRecyclerView hot_video_xrecyclerview;
    @BindView(R.id.loading_data_progress)
    AutoLinearLayout loading_data_progress;

    private int pnum = 1;
    private HotVideoPresenter hotVideoPresenter;
    private List<PublishVideoModel.APIDATABean.RetBean.ListBean> hotVideoList;
    private PublishVideoAdapter publishVideoAdapter;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);

        hotVideoPresenter = new HotVideoPresenter(this);

        //设置recyclerview一些样式
        hot_video_xrecyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        hot_video_xrecyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        hot_video_xrecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        hot_video_xrecyclerview.addItemDecoration(new SpaceItemDecoration(6));
        hot_video_xrecyclerview.setLoadingListener(this);

        //获取热门视频数据
        hotVideoPresenter.getHotVideoList(pnum);
    }

    @OnClick({R.id.hot_video_back,R.id.publish_serach})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.hot_video_back:
                onBackPressed();
                break;
            case R.id.publish_serach:
                Intent intent = new Intent(this, SerachActivity.class);
                intent.putExtra("tag",2);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getHotVideoListFininsh(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list) {
        if (list!=null && list.size()>0){
            publishVideoAdapter = new PublishVideoAdapter(this);
            hotVideoList = list;
            publishVideoAdapter.setData(hotVideoList);
            hot_video_xrecyclerview.setAdapter(publishVideoAdapter);
        }
    }

    @Override
    public void getLoadHotVideoListFininsh(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list) {
        if (list!=null && list.size()>0)
                hotVideoList.addAll(list);
    }


    @Override
    public AutoLinearLayout getProgressWidget() {
        return loading_data_progress;
    }

    @Override
    public XRecyclerView getRecyclerviewWidget() {
        return hot_video_xrecyclerview;
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotVideoPresenter.getHotVideoList(pnum);
                hot_video_xrecyclerview.refreshComplete();
                publishVideoAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotVideoPresenter.getLoadHotVideoList(++pnum);
                hot_video_xrecyclerview.loadMoreComplete();
                publishVideoAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
