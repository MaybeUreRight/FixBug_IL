package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.NewsSerachModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 12:10
 */

public interface SerachContract {
    interface Model {
    }

    interface View {
        void getHotSerachFinished( List<String> hot_search);

        void getStartHotSerachView( List<NewsSerachModel.APIDATABean.RetBean.NewsListBean> news_list);

        /**
         * 获取视频的
         * @param video_list
         */
        void getStartVideoView( List<NewsSerachModel.APIDATABean.RetBean.VideoListBean> video_list);

        void getStartLiveView(List<NewsSerachModel.APIDATABean.RetBean.LiveListBean> live_list);
    }

    interface Presenter {

        /**
         * 热门搜索
         */
        void getHotSerach();

        /**
         * 关键词搜索查询
         * @param keyword
         * @param tag
         */
        void getStartHotSerach(String keyword, int tag);
    }
}
