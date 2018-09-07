package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx.RxAdapter;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.MineAttentionActivity;
import com.xgkj.ilive.activity.TaHomeDetailsActivity;
import com.xgkj.ilive.adapter.MineAttentionAdapter;
import com.xgkj.ilive.adapter.TaUserInfoAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.model.TaUserModel;
import com.xgkj.ilive.mvp.model.bean.TaHistoryListBean;
import com.xgkj.ilive.net.NetUrl;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.utils.GsonUtils;
import com.xgkj.ilive.utils.SharedPreferencesUtil;
import com.xgkj.ilive.view.DividerItemDecoration;
import com.zhy.autolayout.AutoRelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by admin on 2017/10/31.
 */

public class MyLiveFragment extends BaseFragment implements  XRecyclerView.LoadingListener  {

    @BindView(R.id.not_show_attention)
    AutoRelativeLayout not_show_attention;

    @BindView(R.id.lv_live)
    XRecyclerView lv_live;
    TaUserInfoAdapter adapter;

    private List<TaHistoryListBean.ListBean> list;
    private int currPage = 1;
    private int to_uid;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_my_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TaHomeDetailsActivity activity = (TaHomeDetailsActivity) getActivity();
        list = new ArrayList<>();
        to_uid = activity.to_uid;
        lv_live.setVisibility(View.VISIBLE);
        adapter = new TaUserInfoAdapter(getActivity(),"1");
        adapter.setData(list);
        lv_live.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        lv_live.setAdapter(adapter);
        lv_live.setLoadingListener(this);
        getLive();
    }

    private  void getLive(){
        String access_token = SharedPreferencesUtil.getAccessToken(getActivity(), "access_token");
        JSONObject root = new JSONObject();
        JSONObject jobj = new JSONObject();
        try {
            root.put("to_uid",to_uid);
            root.put("page",currPage);
            root.put("type","1");
            jobj.put("APIDATA",root);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Subscription subscription = OkGo.post(NetUrl.OTHERVIEW+access_token)
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
                        Log.d("summer",s);
                        TaUserModel taUserModel = GsonUtils.jsonToBean(s, TaUserModel.class);
                        TaUserModel.APIDATABean apidata = taUserModel.getAPIDATA();
                        int code = apidata.getCode();
                        if (code == 200){
                            if (null != apidata.getHistory_list().getRet().getList() || apidata.getHistory_list().getRet().getList().size()>0){
                                if (1 == currPage){
                                    list.clear();
                                }
                                not_show_attention.setVisibility(View.GONE);
                                lv_live.setVisibility(View.VISIBLE);
                                adapter.resetUserData(apidata.getHistory_list().getRet().getList());
                                lv_live.refreshComplete();
                                lv_live.loadMoreComplete();
                            }else {
                                if (1 == currPage){
                                    not_show_attention.setVisibility(View.VISIBLE);
                                    lv_live.setVisibility(View.GONE);
                                    return;
                                }
                                lv_live.refreshComplete();
                                lv_live.loadMoreComplete();
                                lv_live.setNoMore(true);
                            }
                        }else {
                            not_show_attention.setVisibility(View.VISIBLE);
                            lv_live.setVisibility(View.GONE);
                            lv_live.refreshComplete();
                            lv_live.loadMoreComplete();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("joinPeople",throwable.toString());
                        not_show_attention.setVisibility(View.VISIBLE);
                        lv_live.setVisibility(View.GONE);
                        lv_live.refreshComplete();
                        lv_live.loadMoreComplete();
                    }
                });
    }
    @Override
    public void onRefresh() {
        currPage = 1;
        getLive();
    }

    @Override
    public void onLoadMore() {
        currPage++;
        getLive();
    }
}
