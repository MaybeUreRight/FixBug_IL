package com.xgkj.ilive.mvp.contract;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.xgkj.ilive.mvp.model.AdvanceLiveModel;
import com.xgkj.ilive.mvp.model.PublishVideoDetailsModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;
import com.xgkj.ilive.player.media.NEVideoView;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/20 0020 11:17
 */

public interface PublishVideoDetailsContract {
    interface Model {
    }

    interface View {

        /**
         * 获取点播列表详情
         * @param list
         */
        void getVideoDetailsFinished(PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list);

        void getLikeDetailsFinished(PublishVideoDetailsModel.APIDATABean.RetBean.ListBean list);

        void getLikeAnvanceLiveFinished( AdvanceLiveModel.APIDATABean.RetBean.ListBean list );
        void  getAdvanceLiveDetailsFinished( AdvanceLiveModel.APIDATABean.RetBean.ListBean list );

        /**
         * //设置videoview初始化设置
         * @return
         */
        NEVideoView getNEVideoView();

        void queryChatMessageFinished( List<QueryChatMessageModel.APIDATABean.RetBean> ret);
    }

    interface Presenter {

        /**
         * 进行转发点播视频
         * @param cid
         * @param type
         */
        void startTransmit(String cid,String type,String title,String pic,String status_content, ShareBoardConfig shareBoardConfig, ShareAction shareAction );
        /**
         * 发送聊天内容
         * @param cid
         * @param content
         */
        void sendMessageContent(String cid,String content);

        /**
         * 查询聊天记录
         * @param cid
         */
        void queryChatMessage(String cid);

        /**
         * 获取点播列表
         * @param cid
         */
        void getVideoDetails(String cid);

        void initNEVideoView();

        void startClickLike(String cid,String type);

        void getAdvanceLiveDetails(String cid);
    }
}
