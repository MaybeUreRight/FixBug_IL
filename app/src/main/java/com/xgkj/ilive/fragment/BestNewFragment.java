package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.BestAdapter;
import com.xgkj.ilive.adapter.SpaceItemDecoration;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.contract.BestNewContract;
import com.xgkj.ilive.mvp.model.BestNewModel;
import com.xgkj.ilive.mvp.presenter.BestNewPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 18:58
 */

public class BestNewFragment extends BaseFragment implements BestNewContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.best_new_recyclerview)
    XRecyclerView best_new_recyclerview;
    private BestNewPresenter bestNewPresenter;
    private  List<BestNewModel.APIDATABean.RetBean.ListBean> bestListBean;
    private Handler handler = new Handler();
    private int pnum = 1;
    private BestAdapter bestAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_best_new;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bestNewPresenter = new BestNewPresenter(this, getActivity());

        MobclickAgent.setCatchUncaughtExceptions(true);

        best_new_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.drivder_item_height);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(dimensionPixelSize);
        best_new_recyclerview.addItemDecoration(spaceItemDecoration);

        bestNewPresenter.getBestNewData();
        best_new_recyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        best_new_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        best_new_recyclerview.setLoadingListener(this);
    }


    @Override
    public void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list) {
        bestListBean  = list;

        bestAdapter = new BestAdapter(getActivity());
        bestAdapter.setBestListData(bestListBean);

        best_new_recyclerview.setAdapter(bestAdapter);
    }

    @Override
    public void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> list) {
        if (list!=null)
            bestListBean.addAll(list);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bestNewPresenter.getBestNewData();
                best_new_recyclerview.refreshComplete();
                bestAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bestNewPresenter.getLoadMoreBestNewData(++pnum);
                best_new_recyclerview.loadMoreComplete();
                bestAdapter.notifyDataSetChanged();
            }
        },2000);
    }
}
