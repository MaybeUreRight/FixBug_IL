package com.xgkj.ilive.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.NewsAdapter;
import com.xgkj.ilive.adapter.SerachLiveAdapter;
import com.xgkj.ilive.adapter.SerachVideoAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.db.DBManager;
import com.xgkj.ilive.mvp.contract.SerachContract;
import com.xgkj.ilive.mvp.model.DBSerachModel;
import com.xgkj.ilive.mvp.model.NewsSerachModel;
import com.xgkj.ilive.mvp.model.SerachHistoryAdapter;
import com.xgkj.ilive.mvp.presenter.SerachPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.view.DividerItemDecoration;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SerachActivity extends BaseActivity implements SerachContract.View {

    @BindView(R.id.et_serach)
    EditText et_serach;
    @BindView(R.id.tv_serach_content)
    TextView tv_serach_content;
    @BindView(R.id.serach_flexbox_layout)
    FlexboxLayout serach_flexbox_layout;
    @BindView(R.id.serach_recycler)
    RecyclerView serach_recycler;
    @BindView(R.id.hot_serach)
    AutoLinearLayout hot_serach;
    @BindView(R.id.history_recyclerview)
    RecyclerView history_recyclerview;

    private SerachPresenter serachPresenter;
    /**
     * 请求权限的标识
     */
    private int WRITE_TAG = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_serach;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);

        MobclickAgent.setCatchUncaughtExceptions(true);

        /**
         * android 6.0后动态的检查权限
         */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_TAG);
        }
        DBManager.getInstance().init(this);

        //文本改变监听
        et_serach.addTextChangedListener(new SerachTextChangedListener());

        et_serach.setOnKeyListener(new View.OnKeyListener() {

            @Override

            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SerachActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //搜索
                    onClick(tv_serach_content);
                }
                return false;
            }
        });


        serachPresenter = new SerachPresenter(this);
        serachPresenter.getHotSerach();

        history_recyclerview.setLayoutManager(new LinearLayoutManager(SerachActivity.this, LinearLayoutManager.VERTICAL, false));
        history_recyclerview.addItemDecoration(new DividerItemDecoration(SerachActivity.this));

        queryHistoryData();
    }

    /**
     * 查询数据库
     */
    private void queryHistoryData() {
        //进行查询数据库
        List<DBSerachModel> list = DBManager.getInstance().queryData();
        SerachHistoryAdapter serachHistoryAdapter = new SerachHistoryAdapter();
        if (list != null && list.size() > 0) {
            serachHistoryAdapter.setData(list);
            history_recyclerview.setAdapter(serachHistoryAdapter);
        }
        serachHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHotSerachFinished(List<String> hot_search) {
        Intent intent = getIntent();
        final int index = intent.getIntExtra("tag", 0);
        for (int i = 0; i < hot_search.size(); i++) {
            final TextView textView = new TextView(this);
            String s = hot_search.get(i);
            textView.setText(s);
            textView.setPadding(30, 20, 40, 20);
            AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 20, 30, 40);
            serach_flexbox_layout.setLayoutParams(params);
            serach_flexbox_layout.addView(textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s1 = textView.getText().toString();
                    DBManager.getInstance().insertData(s1);
                    queryHistoryData();
                    serachPresenter.getStartHotSerach(s1, index);
                }
            });
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == WRITE_TAG) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "开启了写的权限", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void getStartHotSerachView(List<NewsSerachModel.APIDATABean.RetBean.NewsListBean> news_list) {

        if (news_list != null && news_list.size() > 0) {
            try {
                hot_serach.setVisibility(View.GONE);
                serach_recycler.setVisibility(View.VISIBLE);
                serach_recycler.setLayoutManager(new LinearLayoutManager(SerachActivity.this, LinearLayoutManager.VERTICAL, false));
                serach_recycler.addItemDecoration(new DividerItemDecoration(this));
                NewsAdapter newsAdapter = new NewsAdapter(news_list, SerachActivity.this);
                serach_recycler.setAdapter(newsAdapter);
            } catch (Exception e) {
                e.printStackTrace();
                MobclickAgent.reportError(this, e.toString());
            }

        } else {
            hot_serach.setVisibility(View.VISIBLE);
            serach_recycler.setVisibility(View.GONE);
            Toast.makeText(SerachActivity.this, "没有搜索到相关内容", Toast.LENGTH_SHORT).show();
        }

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

    @Override
    public void getStartVideoView(List<NewsSerachModel.APIDATABean.RetBean.VideoListBean> video_list) {
        if (video_list != null && video_list.size() > 0) {
            hot_serach.setVisibility(View.GONE);
            serach_recycler.setVisibility(View.VISIBLE);
            serach_recycler.setLayoutManager(new LinearLayoutManager(SerachActivity.this, LinearLayoutManager.VERTICAL, false));
            SerachVideoAdapter serachVideoAdapter = new SerachVideoAdapter();
            serachVideoAdapter.setData(video_list);
            serach_recycler.addItemDecoration(new DividerItemDecoration(this));
            serach_recycler.setAdapter(serachVideoAdapter);
        } else {
            hot_serach.setVisibility(View.VISIBLE);
            serach_recycler.setVisibility(View.GONE);
            Toast.makeText(SerachActivity.this, "没有搜索到相关内容", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getStartLiveView(List<NewsSerachModel.APIDATABean.RetBean.LiveListBean> live_list) {
        if (live_list != null && live_list.size() > 0) {
            Log.e("getStartLiveView", "live_list===" + live_list.size());

            hot_serach.setVisibility(View.GONE);
            serach_recycler.setVisibility(View.VISIBLE);
            SerachLiveAdapter serachLiveAdapter = new SerachLiveAdapter();
            serachLiveAdapter.setData(live_list);
            serach_recycler.setLayoutManager(new LinearLayoutManager(SerachActivity.this, LinearLayoutManager.VERTICAL, false));
            serach_recycler.addItemDecoration(new DividerItemDecoration(this));
            serach_recycler.setAdapter(serachLiveAdapter);
        } else {
            hot_serach.setVisibility(View.VISIBLE);
            serach_recycler.setVisibility(View.GONE);
            Toast.makeText(SerachActivity.this, "没有搜索到相关内容", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.tv_serach_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_serach_content:
                String s = et_serach.getText().toString().trim();
                String serach_content = tv_serach_content.getText().toString().trim();
                if (serach_content.equals("搜索")) {
                    if (TextUtils.isEmpty(s)) {
                        Toast.makeText(this, "关键词不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tv_serach_content.setText("取消");
                    Intent intent = getIntent();
                    int tag = intent.getIntExtra("tag", 0);
                    DBManager.getInstance().insertData(s);
                    queryHistoryData();
                    serachPresenter.getStartHotSerach(s, tag);
                } else {
                    tv_serach_content.setText("搜索");
                    et_serach.setText("");
                    hot_serach.setVisibility(View.VISIBLE);
                    serach_recycler.setVisibility(View.GONE);
                }
                break;
        }
    }

    /**
     * 文本改变监听
     */
    class SerachTextChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String s = editable.toString();
            if ("".equals(s) || s == null || s.length() > 0) {
                hot_serach.setVisibility(View.VISIBLE);
                serach_recycler.setVisibility(View.GONE);
                tv_serach_content.setText("搜索");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
