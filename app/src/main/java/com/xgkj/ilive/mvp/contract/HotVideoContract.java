package com.xgkj.ilive.mvp.contract;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xgkj.ilive.mvp.model.PublishVideoModel;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/17 0017 10:37
 */

public interface HotVideoContract {
    interface Model {
    }

    interface View {

        void getHotVideoListFininsh(List<PublishVideoModel.APIDATABean.RetBean.ListBean> list);

        void getLoadHotVideoListFininsh( List<PublishVideoModel.APIDATABean.RetBean.ListBean> list);

        /**
         * 获取进度
         * @return
         */
        AutoLinearLayout getProgressWidget();

        /**
         * 获取数据的控件
         * @return
         */
        XRecyclerView getRecyclerviewWidget();
    }

    interface Presenter {
        /**
         * 获取热门视频
         * @param pnum
         */
       void getHotVideoList(int pnum);

        /**
         * 获取加载更多视频
         * @param pnum
         */
        void getLoadHotVideoList(int pnum);
    }
}
