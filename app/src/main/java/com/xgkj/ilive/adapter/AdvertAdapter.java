package com.xgkj.ilive.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xgkj.ilive.R;
import com.xgkj.ilive.app.App;
import com.xgkj.ilive.mvp.model.AdvertModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/16 0016 13:42
 */

public class AdvertAdapter extends PagerAdapter{

    private List<AdvertModel.APIDATABean.RetBean.ListBean> advertList;
    private Context context;

    public AdvertAdapter(Context context, List<AdvertModel.APIDATABean.RetBean.ListBean> advertList) {
        this.advertList = advertList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int i = position % advertList.size();
        ImageView advert = new ImageView(context);
        advert.setScaleType(ImageView.ScaleType.FIT_XY);
        AdvertModel.APIDATABean.RetBean.ListBean listBean = advertList.get(i);
        Glide.with(context).load(listBean.getPic())
                .apply(App.requestOptions.placeholder(R.drawable.default_pic))
                .into(advert);
        container.addView(advert);
        return advert;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }
}
