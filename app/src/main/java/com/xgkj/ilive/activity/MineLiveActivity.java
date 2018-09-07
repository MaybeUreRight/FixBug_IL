package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.MineLiveAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.MineLiveContract;
import com.xgkj.ilive.mvp.model.MineLiveModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class MineLiveActivity extends BaseActivity implements MineLiveContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.login_back)
    ImageView mine_live_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.mine_live_recyclerview)
    XRecyclerView mine_live_recyclerview;

    private int pnum = 1;
    private  List<MineLiveModel.APIDATABean.RetBean.ListBean> liveList;
    private Handler handler = new Handler();
    private MineLiveAdapter mineLiveAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("我的直播");
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        mine_live_recyclerview.setLayoutManager(new LinearLayoutManager(MineLiveActivity.this,LinearLayoutManager.VERTICAL,false));
        //mine_live_recyclerview.addItemDecoration(new DividerItemDecoration(this));

        getMineLive();
        mine_live_recyclerview.setLoadingListener(this);
    }

    private void getMineLive(){
        String access_token = SharedPreferencesUtil.getAccessToken(this, "access_token");

        Map<String,Integer> mineLiveMap = new HashMap<>();
        mineLiveMap.put("pnum",pnum);
        mineLiveMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(mineLiveMap);
        Subscription subscription = OkGo.post(NetUrl.MINE_LIVE+access_token)
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
                        MineLiveModel mineLiveModel = GsonUtils.jsonToBean(s, MineLiveModel.class);
                        MineLiveModel.APIDATABean apidata = mineLiveModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            MineLiveModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<MineLiveModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list!=null)
                                liveList = list;
                            mineLiveAdapter = new MineLiveAdapter();
                            mineLiveAdapter.setData(liveList);
                            mine_live_recyclerview.setAdapter(mineLiveAdapter);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(MineLiveActivity.this,throwable.toString());
                        Toast.makeText(MineLiveActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getLoadMoreMineLive(){
        String access_token = SharedPreferencesUtil.getAccessToken(this, "access_token");

        Map<String,Integer> mineLiveMap = new HashMap<>();
        mineLiveMap.put("pnum",++pnum);
        mineLiveMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(mineLiveMap);
        Subscription subscription = OkGo.post(NetUrl.MINE_LIVE+access_token)
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
                        MineLiveModel mineLiveModel = GsonUtils.jsonToBean(s, MineLiveModel.class);
                        MineLiveModel.APIDATABean apidata = mineLiveModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            MineLiveModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<MineLiveModel.APIDATABean.RetBean.ListBean> list = ret.getList();
                            if (list!=null)
                                liveList.addAll(list);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(MineLiveActivity.this,throwable.toString());
                        Toast.makeText(MineLiveActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.login_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getMineLive();
                mine_live_recyclerview.refreshComplete();
                mineLiveAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoadMoreMineLive();
                mine_live_recyclerview.loadMoreComplete();
                mineLiveAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
