package com.xgkj.ilive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.WhoIsLookAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.model.LookStatusModel;
import com.xgkj.ilive.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WhoMayLookActivity extends BaseActivity {

    @BindView(R.id.who_is_look_recyclerview)
    RecyclerView who_is_look_recyclerview;
    @BindView(R.id.title_finished)
    TextView title_finished;

    private String[] look_status = {"公开","付费","加密"};
    private String[] look_description = {"所有人均可观看","需要先付费才可观看","需要先验证密码才可观看"};

    private String selectWay ;
    private int index;
    private String lookWay;

    @Override
    protected int getLayoutId() {
        return R.layout.who_is_look;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        List<LookStatusModel> lookStatusModels = initData();
        WhoIsLookAdapter whoIsLookAdapter = new WhoIsLookAdapter(this);
        whoIsLookAdapter.setData(lookStatusModels);
        who_is_look_recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        who_is_look_recyclerview.addItemDecoration(new DividerItemDecoration(this));
        who_is_look_recyclerview.setAdapter(whoIsLookAdapter);
    }
    @OnClick({R.id.title_finished})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.title_finished:
                Intent intent = new Intent();
                intent.putExtra("selectWay",selectWay);
                intent.putExtra("lookWay",lookWay);
                intent.putExtra("index",index);
                setResult(2,intent);
                finish();
                break;
        }
    }
    /**
     * 初始化状态数据
     * @return
     */
    private List<LookStatusModel> initData(){
        List<LookStatusModel> lookStatusModels = new ArrayList<>();
        for (int i =0 ; i < look_status.length; i++){
            lookStatusModels.add(new LookStatusModel(false,look_status[i],look_description[i]));
        }
        return lookStatusModels;
    }

    public void setSelectText(String s, int mSelectedItem) {
        index = mSelectedItem + 1;
        selectWay = s;
    }

    public void setLookWay(String s) {
        lookWay = s;
    }
}
