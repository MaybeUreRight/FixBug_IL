package com.xgkj.ilive.mvp.contract;

import com.netease.vcloud.video.render.NeteaseView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.xgkj.ilive.mvp.model.JoinModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/6 0006 15:45
 * 作用:开始直播的操作类
 */

public interface LiveStreamingContract {
    interface Model {
    }

    interface View {
        NeteaseView getNEVideoViewWidget();

        void  getJoinPeopleFinish( JoinModel.APIDATABean.RetBean ret);

        void queryChatMessageFinished( List<QueryChatMessageModel.APIDATABean.RetBean>  ret);

    }

    interface Presenter {

        /**
         * 参与的人数
         * @param cid
         */
        void joinPeople(String cid);

        void sendMessageContent(String cid,String content);

        /**
         * 查询聊天消息
         * @param cid
         */
        void queryChatMessage(String cid);

        void sharedLive(String uid,String type, ShareAction shareAction, ShareBoardConfig shareBoardConfig,String title,String pic);

        void getLiveDetails(String cid);

    }
}
