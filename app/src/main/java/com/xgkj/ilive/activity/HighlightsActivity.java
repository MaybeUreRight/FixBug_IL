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
import com.xgkj.ilive.adapter.BestAdapter;
import com.xgkj.ilive.adapter.SpaceItemDecoration;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.HighlightsContract;
import com.xgkj.ilive.mvp.model.BestNewModel;
import com.xgkj.ilive.mvp.presenter.HighlightsPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HighlightsActivity extends BaseActivity implements HighlightsContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.hot_video_back)
    ImageView hot_video_back;
    @BindView(R.id.publish_serach)
    ImageView publish_serach;
    @BindView(R.id.loading_data_progress)
    AutoLinearLayout loading_data_progress;
    @BindView(R.id.hot_video_xrecyclerview)
    XRecyclerView hot_video_xrecyclerview;

    private HighlightsPresenter highlightsPresenter;
    private List<BestNewModel.APIDATABean.RetBean.ListBean> highlightList;
    private BestAdapter bestAdapter;
    private int pnum = 1;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_highlights;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);

        highlightsPresenter = new HighlightsPresenter(this);

        hot_video_xrecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        hot_video_xrecyclerview.addItemDecoration(spaceItemDecoration);
        hot_video_xrecyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        hot_video_xrecyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        hot_video_xrecyclerview.setLoadingListener(this);

        highlightsPresenter.getHighlightsData(pnum);
    }

    @Override
    public void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list) {
        if (list!=null && list.size() >0){

            loading_data_progress.setVisibility(View.GONE);
            hot_video_xrecyclerview.setVisibility(View.VISIBLE);

            highlightList = list;
            bestAdapter = new BestAdapter(this);
            bestAdapter.setBestListData(highlightList);
            hot_video_xrecyclerview.setAdapter(bestAdapter);
        }
    }

    @Override
    public void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list) {
        if (list!=null && list.size()>0)
            highlightList.addAll(list);
    }

    @OnClick({R.id.hot_video_back,R.id.publish_serach})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.hot_video_back:
                onBackPressed();
                break;
            case R.id.publish_serach:
                Intent intent = new Intent(this, SerachActivity.class);
                intent.putExtra("tag",3);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                highlightsPresenter.getHighlightsData(pnum);
                bestAdapter.notifyDataSetChanged();
                hot_video_xrecyclerview.refreshComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                highlightsPresenter.getLoadMoreHighlightsData(++pnum);
                bestAdapter.notifyDataSetChanged();
                hot_video_xrecyclerview.loadMoreComplete();
            }
        },2000);
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
