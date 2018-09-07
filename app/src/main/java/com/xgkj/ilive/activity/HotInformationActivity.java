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
import com.xgkj.ilive.adapter.PublishInfoAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.HotInformationContract;
import com.xgkj.ilive.mvp.model.PublishInformationModel;
import com.xgkj.ilive.mvp.presenter.HotInformationPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.view.DividerItemDecoration;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotInformationActivity extends BaseActivity implements HotInformationContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.hot_video_back)
    ImageView hot_video_back;
    @BindView(R.id.publish_serach)
    ImageView publish_serach;
    @BindView(R.id.loading_data_progress)
    AutoLinearLayout loading_data_progress;
    @BindView(R.id.hot_video_xrecyclerview)
    XRecyclerView hot_video_xrecyclerview;

    private int pnum = 1;
    private HotInformationPresenter hotInformationPresenter;
    private List<PublishInformationModel.APIDATABean.RetBean.ListBean> hotInfoList;
    private PublishInfoAdapter publishInfoAdapter;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_information;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        MobclickAgent.setCatchUncaughtExceptions(true);

        hotInformationPresenter = new HotInformationPresenter(this);

        hot_video_xrecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        hot_video_xrecyclerview.addItemDecoration(new DividerItemDecoration(this));
        hot_video_xrecyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        hot_video_xrecyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        hot_video_xrecyclerview.setLoadingListener(this);

        hotInformationPresenter.getInfoData(pnum);
    }

    @OnClick({R.id.hot_video_back,R.id.publish_serach})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.hot_video_back:
                onBackPressed();
                break;
            case R.id.publish_serach:
                Intent intent = new Intent(this, SerachActivity.class);
                intent.putExtra("tag",1);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getInfomationListFinished(List<PublishInformationModel.APIDATABean.RetBean.ListBean> list) {
        loading_data_progress.setVisibility(View.GONE);
        hot_video_xrecyclerview.setVisibility(View.VISIBLE);
        if (list!=null && list.size()>0){
            publishInfoAdapter = new PublishInfoAdapter(this);
            hotInfoList = list;
            publishInfoAdapter.setData(hotInfoList);
            hot_video_xrecyclerview.setAdapter(publishInfoAdapter);
        }
    }

    @Override
    public void getLoadInfomationList(List<PublishInformationModel.APIDATABean.RetBean.ListBean> list) {
        if (list!=null && list.size()>0)
            hotInfoList.addAll(list);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotInformationPresenter.getInfoData(pnum);
                hot_video_xrecyclerview.refreshComplete();
                publishInfoAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotInformationPresenter.getLoadInfoData(++pnum);
                hot_video_xrecyclerview.loadMoreComplete();
                publishInfoAdapter.notifyDataSetChanged();
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
