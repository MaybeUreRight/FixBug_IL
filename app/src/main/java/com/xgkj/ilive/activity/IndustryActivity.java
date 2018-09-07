package com.xgkj.ilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.IndustryAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.IndustryContract;
import com.xgkj.ilive.mvp.model.IndustryModel;
import com.xgkj.ilive.mvp.presenter.IndustryPresenter;
import com.xgkj.ilive.view.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IndustryActivity extends BaseActivity implements IndustryContract.View{

    @BindView(R.id.industry_list)
    RecyclerView industry_list;
    @BindView(R.id.industry_back)
    ImageView industry_back;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_industry;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        MobclickAgent.setCatchUncaughtExceptions(true);
        IndustryPresenter industryPresenter = new IndustryPresenter(this);
        industryPresenter.getIndustryList();
    }

    @OnClick({R.id.industry_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.industry_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void getIndustryFinish(List<IndustryModel.APIDATABean.ListBean> list) {
        industry_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        industry_list.addItemDecoration(new DividerItemDecoration(this));
        IndustryAdapter industryAdapter = new IndustryAdapter();
        industryAdapter.setData(this,list);
        industry_list.setAdapter(industryAdapter);
    }

    public void setIndustry(String id, String title) {
        Intent intent = new Intent();
        intent.putExtra("id",id);
        intent.putExtra("title",title);
        setResult(3,intent);
        finish();
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
