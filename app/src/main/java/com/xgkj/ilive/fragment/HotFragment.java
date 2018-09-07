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
import com.xgkj.ilive.mvp.contract.HotContract;
import com.xgkj.ilive.mvp.model.BestNewModel;
import com.xgkj.ilive.mvp.presenter.HotPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 18:57
 */

public class HotFragment extends BaseFragment implements HotContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.hot_recyclerview)
    XRecyclerView hot_recyclerview;


    private int pnum = 0;
    private HotPresenter hotPresenter;
    private List<BestNewModel.APIDATABean.RetBean.ListBean> hotList;
    private Handler handler = new Handler();
    private BestAdapter bestAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        hotPresenter = new HotPresenter(this, getActivity());
        hotPresenter.getHotData(pnum);
        hot_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.drivder_item_height);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(dimensionPixelSize);
        hot_recyclerview.addItemDecoration(spaceItemDecoration);

        hot_recyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        hot_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        hot_recyclerview.setLoadingListener(this);
    }

    @Override
    public void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret) {
        bestAdapter = new BestAdapter(getActivity());
        hotList = ret;
        bestAdapter.setBestListData(hotList);

        hot_recyclerview.setAdapter(bestAdapter);
    }

    @Override
    public void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret) {
        if (ret != null)
            hotList.addAll(ret);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotPresenter.getHotData(pnum);
                hot_recyclerview.refreshComplete();
                bestAdapter.notifyDataSetChanged();
            }
        },2000);

    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hotPresenter.getLoadMoreHotData(++pnum);
                hot_recyclerview.loadMoreComplete();
                bestAdapter.notifyDataSetChanged();
            }
        },2000);
    }
}
