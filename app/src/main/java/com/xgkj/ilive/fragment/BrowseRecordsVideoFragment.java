package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.BrowseRecordsVideoAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.model.BrowseRecordsVideoModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.DividerItemDecoration;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 09:26
 */

public class BrowseRecordsVideoFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    @BindView(R.id.browse_records_video)
    XRecyclerView browse_records_video;

    private  List<BrowseRecordsVideoModel.APIDATABean.RetBean.ListBean> addVideoList;
    private int pnum = 1;
    private Handler handler = new Handler();
    private BrowseRecordsVideoAdapter browseRecordsVideoAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_browserecords_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        getDotVideoData();
        browse_records_video.setRefreshProgressStyle(ProgressStyle.Pacman);
        browse_records_video.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

        browse_records_video.setLoadingListener(this);
    }

    private void getDotVideoData(){

        String access_token = SharedPreferencesUtil.getAccessToken(getActivity(), "access_token");

        Map<String,Integer>  videoMap = new HashMap<>();

        videoMap.put("type",1);
        videoMap.put("pnum",pnum);
        videoMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(videoMap);

        Subscription subscription = OkGo.post(NetUrl.BROWSE_RECORDS_LIVE_VIDEO+access_token)
                .upJson(jobj)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        BrowseRecordsVideoModel browseRecordsVideoModel = GsonUtils.jsonToBean(s, BrowseRecordsVideoModel.class);
                        BrowseRecordsVideoModel.APIDATABean apidata = browseRecordsVideoModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            BrowseRecordsVideoModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BrowseRecordsVideoModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list !=null)
                                addVideoList = list;
                            browseRecordsVideoAdapter = new BrowseRecordsVideoAdapter();
                            browseRecordsVideoAdapter.setData(addVideoList);
                            browse_records_video.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                            browse_records_video.addItemDecoration(new DividerItemDecoration(getActivity()));
                            browse_records_video.setAdapter(browseRecordsVideoAdapter);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(getActivity(),throwable.toString());
                        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDotVideoData();
                browse_records_video.refreshComplete();
                browseRecordsVideoAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDotLoadMoreVideoData();
                browse_records_video.loadMoreComplete();
                browseRecordsVideoAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    private void getDotLoadMoreVideoData(){

        String access_token = SharedPreferencesUtil.getAccessToken(getActivity(), "access_token");

        Map<String,Integer>  videoMap = new HashMap<>();

        videoMap.put("type",1);
        videoMap.put("pnum",++pnum);
        videoMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(videoMap);

        Subscription subscription = OkGo.post(NetUrl.BROWSE_RECORDS_LIVE_VIDEO+access_token)
                .upJson(jobj)
                .getCall(StringConvert.create(), RxAdapter.<String>create())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        BrowseRecordsVideoModel browseRecordsVideoModel = GsonUtils.jsonToBean(s, BrowseRecordsVideoModel.class);
                        BrowseRecordsVideoModel.APIDATABean apidata = browseRecordsVideoModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            BrowseRecordsVideoModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BrowseRecordsVideoModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list !=null)
                                addVideoList.addAll(list);

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(getActivity(),throwable.toString());
                        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }
}
