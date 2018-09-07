package com.xgkj.ilive.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.adapter.VideoPagerAdapter;
import com.xgkj.ilive.base.BaseActivity;
import com.xgkj.ilive.mvp.contract.TaUserInfoContact;
import com.xgkj.ilive.mvp.model.TaUserModel;
import com.xgkj.ilive.mvp.model.bean.TaHistoryListBean;
import com.xgkj.ilive.mvp.model.bean.TaUserInfoBean;
import com.xgkj.ilive.mvp.presenter.TaUserInfoPresenter;
import com.xgkj.ilive.utils.AppManager;
import com.xgkj.ilive.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017/10/31.
 */

public class TaHomeDetailsActivity extends BaseActivity implements TaUserInfoContact.View,ViewPager.OnPageChangeListener{

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_sign)
    TextView tv_sign;
    @BindView(R.id.tv_follow)
    TextView tv_follow;

    @BindView(R.id.ll_video)
    LinearLayout ll_video;
    @BindView(R.id.tv_video)
    TextView tv_video;
    @BindView(R.id.line_video)
    TextView line_video;

    @BindView(R.id.ll_live)
    LinearLayout ll_live;
    @BindView(R.id.tv_live)
    TextView tv_live;
    @BindView(R.id.line_live)
    TextView line_live;

    @BindView(R.id.video_viewpage)
    ViewPager video_viewpage;
    VideoPagerAdapter adapter;

    private TaUserInfoPresenter taUserInfoPresenter;
    public int to_uid;

    private List<TaHistoryListBean.ListBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ta_home_details;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        to_uid = getIntent().getIntExtra("to_uid",0);
        AppManager.getAppManager().addActivity(this);
        adapter = new VideoPagerAdapter(getSupportFragmentManager());
        video_viewpage.setOnPageChangeListener(this);
        taUserInfoPresenter = new TaUserInfoPresenter(this);
        taUserInfoPresenter.getUserInfo(to_uid,"1");
        taUserInfoPresenter.getUserInfo(to_uid,"2");
        showLine(1);
    }

    private void showLine(int flag) {
        switch (flag){
            case 1:
                tv_video.setTextColor(getColor(R.color.color_ff6854));
                tv_live.setTextColor(getColor(R.color.color_black_333333));
                line_video.setVisibility(View.VISIBLE);
                line_live.setVisibility(View.INVISIBLE);
                break;
            case 2:
                tv_video.setTextColor(getColor(R.color.color_black_333333));
                tv_live.setTextColor(getColor(R.color.color_ff6854));
                line_video.setVisibility(View.INVISIBLE);
                line_live.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.iv_back,R.id.ll_video,R.id.ll_live})
    protected  void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_video:
                showLine(1);
                video_viewpage.setCurrentItem(0);
                break;
            case R.id.ll_live:
                showLine(2);
                video_viewpage.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                showLine(1);
                break;
            case 1:
                showLine(2);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void getUserInfoFinished(TaUserModel.APIDATABean ret, String type) {
        if (ret.getList().size()>0){
            TaUserInfoBean bean = ret.getList().get(0);
            Glide.with(this).load(bean.getPic()).asBitmap().centerCrop().error(R.drawable.mine_circle_icon).placeholder(R.drawable.mine_circle_icon).into(iv_head);
            tv_nickname.setText(bean.getNickname());
//            tv_sign.setText(bean.getNickname());
        }
        list = ret.getHistory_list().getRet().getList();
        video_viewpage.setAdapter(adapter);
        video_viewpage.setCurrentItem(0);
    }

    public List<TaHistoryListBean.ListBean> getLiveData(){
        return list;
    }

    public List<TaHistoryListBean.ListBean> getVideoData(){
        return list;
    }
}
