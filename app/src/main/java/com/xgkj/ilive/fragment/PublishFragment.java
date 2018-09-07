package com.xgkj.ilive.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.SerachActivity;
import com.xgkj.ilive.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 16:52
 */

public class PublishFragment extends BaseFragment{

    @BindView(R.id.publish_information)
    TextView publish_information;
    @BindView(R.id.publish_video)
    TextView publish_video;
    @BindView(R.id.publish_serach)
    ImageView publish_serach;
    private FragmentManager manager;
    private int tag = 1;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        //默认选中的是资讯列表
        manager = getActivity().getSupportFragmentManager();
        publish_information.setTextColor(getResources().getColor(R.color.publish_font_color));
        publish_video.setTextColor(getResources().getColor(android.R.color.white));
        manager.beginTransaction().replace(R.id.publish_framelayout,new PublishInformationFragment()).commit();
    }

    @OnClick({R.id.publish_information,R.id.publish_video,R.id.publish_serach})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.publish_information:
                publish_information.setTextColor(getResources().getColor(R.color.publish_font_color));
                publish_video.setTextColor(getResources().getColor(android.R.color.white));
                manager.beginTransaction().replace(R.id.publish_framelayout,new PublishInformationFragment()).commit();

                tag = 1;
                break;
            case R.id.publish_video:
                publish_video.setTextColor(getResources().getColor(R.color.publish_font_color));
                publish_information.setTextColor(getResources().getColor(android.R.color.white));
                manager.beginTransaction().replace(R.id.publish_framelayout,new PublishVideoFragment()).commit();

                tag = 2;
                break;
            case R.id.publish_serach:
                Intent intent = new Intent(getActivity(), SerachActivity.class);
                intent.putExtra("tag",tag);
                getActivity().startActivity(intent);
                break;
        }
    }
}
