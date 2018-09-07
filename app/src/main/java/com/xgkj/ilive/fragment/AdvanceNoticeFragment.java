package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.BestAdapter;
import com.xgkj.ilive.adapter.SpaceItemDecoration;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.contract.AdvanceNoticeContract;
import com.xgkj.ilive.mvp.model.BestNewModel;
import com.xgkj.ilive.mvp.presenter.AdvanceNoticePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 19:01
 */

public class AdvanceNoticeFragment extends BaseFragment implements AdvanceNoticeContract.View, XRecyclerView.LoadingListener {


    @BindView(R.id.advance_recyclerview)
    XRecyclerView advance_recyclerview;

    private AdvanceNoticePresenter advanceNoticePresenter;
    private int get_video = 0;
    private int type = 4;
    private int records = 30;
    private int pnum = 1;
    private List<BestNewModel.APIDATABean.RetBean.ListBean> advanceNoticeList;
    private Handler handler = new Handler();
    private BestAdapter bestAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_advance_notice;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        advanceNoticePresenter = new AdvanceNoticePresenter(this, getActivity());

        MobclickAgent.setCatchUncaughtExceptions(true);

        advanceNoticePresenter.getHotData(get_video,type,records,pnum);

        advance_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.drivder_item_height);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(dimensionPixelSize);
        advance_recyclerview.addItemDecoration(spaceItemDecoration);

        advance_recyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        advance_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        advance_recyclerview.setLoadingListener(this);
    }

    @Override
    public void getHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret) {
        bestAdapter = new BestAdapter(getActivity());
        advanceNoticeList = ret;
        Log.e("getHotDataFinish",advanceNoticeList.size()+"========");
        bestAdapter.setBestListData(advanceNoticeList);

        advance_recyclerview.setAdapter(bestAdapter);
    }

    @Override
    public void getLoadMoreHotDataFinish(List<BestNewModel.APIDATABean.RetBean.ListBean> ret) {
        if (ret != null)
            advanceNoticeList.addAll(ret);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                advanceNoticePresenter.getHotData(get_video,type,records,pnum);
                advance_recyclerview.refreshComplete();
                bestAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                advanceNoticePresenter.getLoadMoreHotData(get_video,type,records,++pnum);
                advance_recyclerview.loadMoreComplete();
                bestAdapter.notifyDataSetChanged();
            }
        },2000);
    }
}
