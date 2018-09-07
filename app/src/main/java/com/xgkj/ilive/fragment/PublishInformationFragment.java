package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.PublishInfoAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.contract.PublishInformationContract;
import com.xgkj.ilive.mvp.model.PublishInformationModel;
import com.xgkj.ilive.mvp.presenter.PublishInformationPresenter;
import com.xgkj.ilive.view.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 11:21
 */

public class PublishInformationFragment extends BaseFragment implements PublishInformationContract.View, XRecyclerView.LoadingListener {

    private PublishInformationPresenter publishInformationPresenter;
    private int pagerNumber = 1;
    private List<PublishInformationModel.APIDATABean.RetBean.ListBean> infoListAll;


    @BindView(R.id.publisj_info_recycler)
    XRecyclerView publisj_info_recycler;



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what){
                case 1:
                    List<PublishInformationModel.APIDATABean.RetBean.ListBean> list= (List<PublishInformationModel.APIDATABean.RetBean.ListBean>) msg.obj;
                    publisj_info_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    publishInfoAdapter = new PublishInfoAdapter(getActivity());
                    infoListAll = list;
                    publishInfoAdapter.notifyDataSetChanged();
                    publishInfoAdapter.setData(infoListAll);
                    publisj_info_recycler.addItemDecoration(new DividerItemDecoration(getActivity()));
                    publisj_info_recycler.setAdapter(publishInfoAdapter);
                    break;

            }
        }
    };
    private PublishInfoAdapter publishInfoAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        publishInformationPresenter = new PublishInformationPresenter(this, getActivity());
        //进行请求数据
        publishInformationPresenter.getInformationList(1,10,pagerNumber);

        publisj_info_recycler.setRefreshProgressStyle(ProgressStyle.Pacman);
        publisj_info_recycler.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
//        publisj_info_recycler.setLoadingMoreEnabled(true);

        publisj_info_recycler.setLoadingListener(this);
    }


    @Override
    public void getInfomationListFinished(List<PublishInformationModel.APIDATABean.RetBean.ListBean> list) {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = list;
        handler.sendMessage(message);
    }

    @Override
    public void getLoadMore(List<PublishInformationModel.APIDATABean.RetBean.ListBean> list) {
        if (infoListAll!=null && infoListAll.size()>0)
            infoListAll.addAll(list);
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                publishInformationPresenter.getInformationList(1,10,pagerNumber);
                publishInfoAdapter.notifyDataSetChanged();
                publisj_info_recycler.refreshComplete();
            }
        },2000);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                publishInformationPresenter.getLoadMore(1,10,++pagerNumber);
                publishInfoAdapter.notifyDataSetChanged();
                publisj_info_recycler.loadMoreComplete();
            }
        },2000);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
