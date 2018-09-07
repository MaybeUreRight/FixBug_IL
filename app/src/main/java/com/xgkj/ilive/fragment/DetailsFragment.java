package com.xgkj.ilive.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.xgkj.ilive.R;
import com.xgkj.ilive.base.BaseFragment;
import com.xgkj.ilive.mvp.model.PublishVideoDetailsModel;
import com.xgkj.ilive.utils.DateUtil;

import butterknife.BindView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/20 0020 19:08
 */

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.details_title)
    TextView details_title;
    @BindView(R.id.details_time)
    TextView details_time;
    @BindView(R.id.home_details_name)
    TextView home_details_name;
    private PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list;

    private String title;
    private String time;
    private String username;

    public static DetailsFragment newInstance(String title, String time, String username) {
        DetailsFragment newFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("time", time);
        bundle.putString("username", username);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        MobclickAgent.setCatchUncaughtExceptions(true);

        Bundle args = getArguments();
        if (args != null) {
            title = args.getString("title");
            time = args.getString("time");
            username = args.getString("username");
        }
        details_title.setText(title);
        String millis = DateUtil.formatTimeInMillis(Long.parseLong(time + "000"));
        details_time.setText(millis);
        home_details_name.setText(username);

    }
}
