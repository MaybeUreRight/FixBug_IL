package com.xgkj.ilive.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.xgkj.ilive.R;
import com.xgkj.ilive.activity.GuidePagerActivity;
import com.xgkj.ilive.activity.LoginActivity;
import com.xgkj.ilive.activity.MainActivity;
import com.xgkj.ilive.mvp.contract.GuideContract;
import com.xgkj.ilive.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 10:45
 */

public class GuidePagerPresenter implements GuideContract.Presenter {

    private GuideContract.View mGuideView;
    private Context mContext;
    private static final String IS_FIRST ="is_first";

    public GuidePagerPresenter(GuideContract.View view){
        mGuideView = view;
        mContext=(Context)view;
    }

    @Override
    public void initData() {
        List<ImageView> imageViewList = new ArrayList<>();
        imageViewList.add(setImageViewData(R.drawable.video_pager));
        imageViewList.add(setImageViewData(R.drawable.live_pager));
        imageViewList.add(setImageViewData(R.drawable.information_pager));
        imageViewList.add(setImageViewData(R.drawable.shared_pager));
        mGuideView.initDataFinish(imageViewList);
    }

    @Override
    public ImageView setImageViewData(int resId) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(resId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void startActivity() {
        SharedPreferencesUtil.savePagerStatus(mContext,IS_FIRST,false);
        String access_token = SharedPreferencesUtil.getAccessToken(mContext, "access_token");
        if ("".equals(access_token)){
            jumpPager(LoginActivity.class);
        }else{
            jumpPager(MainActivity.class);
        }
    }

    /**
     * 开始跳转
     * @param cls
     * @param <T>
     */
    private <T>void jumpPager(Class<T> cls){
        Intent intent = new Intent(mContext,cls);
        mContext.startActivity(intent);
        GuidePagerActivity guidePagerActivity = (GuidePagerActivity) this.mContext;
        guidePagerActivity.finish();
    }
}
