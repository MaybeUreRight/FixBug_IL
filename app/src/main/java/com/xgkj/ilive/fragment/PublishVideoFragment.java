package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.PublishVideoAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.contract.PublishVideoContract;
import com.xgkj.ilive.mvp.model.PublishVideoModel;
import com.xgkj.ilive.mvp.presenter.PublishVideoPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 11:22
 */

public class PublishVideoFragment extends BaseFragment implements PublishVideoContract.View, XRecyclerView.LoadingListener {

    private PublishVideoPresenter publishVideoPresenter;
    private List<PublishVideoModel.APIDATABean.RetBean.ListBean> videoList;

    @BindView(R.id.info_video_recyclerview)
    XRecyclerView info_video_recyclerview;
    private int pagerNumber = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what){
                case 1:
                    List<PublishVideoModel.APIDATABean.RetBean.ListBean> list= (List<PublishVideoModel.APIDATABean.RetBean.ListBean>) msg.obj;
                    info_video_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    publishVideoAdapter = new PublishVideoAdapter(getActivity());
                    videoList = list;
                    publishVideoAdapter.setData(videoList);
                    info_video_recyclerview.setAdapter(publishVideoAdapter);
                    break;
            }
        }
    };
    private PublishVideoAdapter publishVideoAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        publishVideoPresenter = new PublishVideoPresenter(this, getActivity());
        //获取点播视频的列表
        publishVideoPresenter.getVideoList(1,10,pagerNumber);

        info_video_recyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        info_video_recyclerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        info_video_recyclerview.setLoadingMoreEnabled(true);

        info_video_recyclerview.setLoadingListener(this);
    }

    @Override
    public void getVideoListFininsh(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list) {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = list;
        handler.sendMessage(message);
    }

    @Override
    public void getVideoLoadMore(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list) {
        if (videoList!=null && videoList.size()>0);
            videoList.addAll(list);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                publishVideoPresenter.getVideoList(1,10,pagerNumber);
                info_video_recyclerview.refreshComplete();
                publishVideoAdapter.notifyDataSetChanged();
            }
        },2000);

    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                publishVideoPresenter.getVideoLoadMore(1,10,++pagerNumber);
                info_video_recyclerview.loadMoreComplete();
                publishVideoAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
