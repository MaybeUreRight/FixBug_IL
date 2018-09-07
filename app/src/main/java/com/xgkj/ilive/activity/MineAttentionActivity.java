package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.MineAttentionAdapter;
import com.xgkj.ilive.adapter.SpaceItemDecoration;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.MineAttentionContract;
import com.xgkj.ilive.mvp.model.MineAttentionModel;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.DividerItemDecoration;

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

public class MineAttentionActivity extends BaseActivity implements MineAttentionContract.View, XRecyclerView.LoadingListener {

    @BindView(R.id.login_back)
    ImageView mine_attention_back;
    @BindView(R.id.title_content)
    TextView title_content;
    @BindView(R.id.mine_attention_recyclerview)
    XRecyclerView mine_attention_recyclerview;
    @BindView(R.id.not_show_attention)
    RelativeLayout not_show_attention;

    private int pnum=1;
    private List<MineAttentionModel.APIDATABean.RetBean.ListBean> addAttenTionList;
    private MineAttentionAdapter mineAttentionAdapter;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_attention;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        title_content.setText("我的关注");
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        getAttentionData();
        mine_attention_recyclerview.setLoadingListener(this);
    }

    private void getAttentionData(){
        String access_token = SharedPreferencesUtil.getAccessToken(this, "access_token");
        Map<String,Integer> attentionMap = new HashMap<>();
        attentionMap.put("pnum",pnum);
        attentionMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(attentionMap);
        Subscription subscription = OkGo.post(NetUrl.MINE_ATTENTION+access_token)
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
                        MineAttentionModel mineAttentionModel = GsonUtils.jsonToBean(s, MineAttentionModel.class);
                        MineAttentionModel.APIDATABean apidata = mineAttentionModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            MineAttentionModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<MineAttentionModel.APIDATABean.RetBean.ListBean> list = ret.getList();

                            if (list == null || list.size() ==0){
                                not_show_attention.setVisibility(View.VISIBLE);
                                mine_attention_recyclerview.setVisibility(View.GONE);
                            }else {
                                if (list.size() <10){
                                    mine_attention_recyclerview.setNoMore(true);
                                }else {
                                    mine_attention_recyclerview.setNoMore(false);
                                }
                                not_show_attention.setVisibility(View.GONE);
                                mine_attention_recyclerview.setVisibility(View.VISIBLE);
                                addAttenTionList = list;
                                mineAttentionAdapter = new MineAttentionAdapter(MineAttentionActivity.this);
                                mineAttentionAdapter.setData(addAttenTionList);
                                mine_attention_recyclerview.setLayoutManager(new LinearLayoutManager(MineAttentionActivity.this,LinearLayoutManager.VERTICAL,false));
                                mine_attention_recyclerview.addItemDecoration(new DividerItemDecoration(MineAttentionActivity.this));
                                mine_attention_recyclerview.setAdapter(mineAttentionAdapter);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(MineAttentionActivity.this,throwable.toString());
                        Toast.makeText(MineAttentionActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getLoadMoreAttentionData(){
        String access_token = SharedPreferencesUtil.getAccessToken(this, "access_token");
        Map<String,Integer> attentionMap = new HashMap<>();
        attentionMap.put("pnum",++pnum);
        attentionMap.put("records",10);

        JSONObject jobj = GsonUtils.upIntJson(attentionMap);
        Subscription subscription = OkGo.post(NetUrl.MINE_ATTENTION+access_token)
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
                        MineAttentionModel mineAttentionModel = GsonUtils.jsonToBean(s, MineAttentionModel.class);
                        MineAttentionModel.APIDATABean apidata = mineAttentionModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            MineAttentionModel.APIDATABean.RetBean ret = apidata.getRet();
                            List<MineAttentionModel.APIDATABean.RetBean.ListBean> list = ret.getList();

                            if (list == null || list.size() ==0){
                                not_show_attention.setVisibility(View.VISIBLE);
                            }else {
                                not_show_attention.setVisibility(View.GONE);
                                addAttenTionList.addAll(list);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MobclickAgent.reportError(MineAttentionActivity.this,throwable.toString());
                        Toast.makeText(MineAttentionActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
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
                getAttentionData();
                mine_attention_recyclerview.refreshComplete();
                mineAttentionAdapter.notifyDataSetChanged();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoadMoreAttentionData();
                mine_attention_recyclerview.loadMoreComplete();
                mineAttentionAdapter.notifyDataSetChanged();
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
