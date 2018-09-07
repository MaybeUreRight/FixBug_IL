package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.HomeAdapter;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.contract.HomeContract;
import com.xgkj.ilive.mvp.model.AdvertModel;
import com.xgkj.ilive.mvp.model.HomeModel;
import com.xgkj.ilive.mvp.presenter.HomePresenter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 16:49
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {


    @BindView(R.id.home_recyclerview)
    RecyclerView home_recyclerview;
//    @BindView(R.id.home_serach)
//    ImageView home_serach;
    @BindView(R.id.loading_progress)
    AutoLinearLayout loading_progress;

    private HomePresenter homePresenter;


    private HomeAdapter homeAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        MobclickAgent.setCatchUncaughtExceptions(true);

        homePresenter = new HomePresenter(this, getActivity());
        homeAdapter = new HomeAdapter(getActivity());
        MobclickAgent.setCatchUncaughtExceptions(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        if (linearLayoutManager != null) {
            home_recyclerview.setLayoutManager(linearLayoutManager);
        }

        homePresenter.getAdvertPic();
        homePresenter.getHomeGroom();

    }



    @Override
    public void getAdvertFinish(List<AdvertModel.APIDATABean.RetBean.ListBean> advertList) {
        if (advertList != null && advertList.size() > 0) {
            homeAdapter.setAdvertData(advertList);
        }
    }

    @Override
    public void getGroomListItem(HomeModel.APIDATABean apidata) {

        if (apidata != null) {
            loading_progress.setVisibility(View.GONE);
            home_recyclerview.setVisibility(View.VISIBLE);
            homeAdapter.setData(apidata);
            home_recyclerview.setAdapter(homeAdapter);
        }else {
            loading_progress.setVisibility(View.VISIBLE);
            home_recyclerview.setVisibility(View.GONE);
        }

    }

//    @OnClick({R.id.home_serach})
//    public void onClick(View view){
//        switch (view.getId()){
//           case R.id.home_serach:
//               Intent intent = new Intent(getActivity(), SerachActivity.class);
//               intent.putExtra("tag",1);
//               startActivity(intent);
//                break;
//        }
//    }
}
