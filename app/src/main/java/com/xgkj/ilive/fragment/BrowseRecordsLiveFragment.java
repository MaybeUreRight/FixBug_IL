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
import com.xgkj.ilive.adapter.BrowseRecordsLiveAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.model.BrowseRecordsLiveModel;
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
 * 日期: 2017/7/15 0015 09:25
 * 作用:浏览记录直播的内容
 */

public class BrowseRecordsLiveFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    @BindView(R.id.browse_records_live)
    XRecyclerView browse_records_live;

    private int pnum = 1;
    private List<BrowseRecordsLiveModel.APIDATABean.RetBean.ListBean> allList;
    private BrowseRecordsLiveAdapter browseRecordsLiveAdapter;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_browserecords_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MobclickAgent.setCatchUncaughtExceptions(true);

        getLiveBrowseRecordsLive();

        browse_records_live.setRefreshProgressStyle(ProgressStyle.Pacman);
        browse_records_live.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

        browse_records_live.setLoadingListener(this);
    }

    /**
     * 获取直播
     */
    private void getLiveBrowseRecordsLive() {
        String access_token = SharedPreferencesUtil.getAccessToken(getActivity(), "access_token");
        Map<String,Integer> liveMap = new HashMap<>();
        liveMap.put("type",1);
        liveMap.put("pnum",pnum);
        liveMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(liveMap);
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
                        BrowseRecordsLiveModel browseRecordsLiveModel = GsonUtils.jsonToBean(s, BrowseRecordsLiveModel.class);
                        BrowseRecordsLiveModel.APIDATABean apidata = browseRecordsLiveModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            BrowseRecordsLiveModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BrowseRecordsLiveModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list != null)
                                 allList = list;
                            browseRecordsLiveAdapter = new BrowseRecordsLiveAdapter();
                            browseRecordsLiveAdapter.setData(allList);
                            browse_records_live.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                            browse_records_live.addItemDecoration(new DividerItemDecoration(getActivity()));
                            browse_records_live.setAdapter(browseRecordsLiveAdapter);
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
                getLiveBrowseRecordsLive();
                browse_records_live.refreshComplete();
                browseRecordsLiveAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoadMoreBrowseRecordsLive();
                browse_records_live.loadMoreComplete();
                browseRecordsLiveAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    /**
     * 获取直播
     */
    private void getLoadMoreBrowseRecordsLive() {
        String access_token = SharedPreferencesUtil.getAccessToken(getActivity(), "access_token");
        Map<String,Integer> liveMap = new HashMap<>();
        liveMap.put("type",1);
        liveMap.put("pnum",++pnum);
        liveMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(liveMap);
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
                        BrowseRecordsLiveModel browseRecordsLiveModel = GsonUtils.jsonToBean(s, BrowseRecordsLiveModel.class);
                        BrowseRecordsLiveModel.APIDATABean apidata = browseRecordsLiveModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            BrowseRecordsLiveModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<BrowseRecordsLiveModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list !=null)
                                allList .addAll(list);
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
