package com.xgkj.ilive.mvp.contract;

import com.xgkj.ilive.mvp.model.ChatModel;
import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.mvp.model.QueryChatMessageModel;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/27 0027 10:38
 */

public interface ChatContract {
    interface Model {

    }

    interface View {

        /**
         * 查询历史消息完成
         * @param list 获取历史消息
         */
        void pullMessageHistoryFinished( List<ChatModel.APIDATABean.RetBean.ListBean> list);



        /**
         * 获取用户信息
         * @param ret
         */
        void getUserInfoFinished(MineModel.APIDATABean.RetBean ret);

        /**
         * 查询聊天记录完成
         * @param ret
         */
        void queryChatMessageFinished(List<QueryChatMessageModel.APIDATABean.RetBean> ret);
    }

    interface Presenter {

        /**
         * 进入聊天室的操作
         * @param room_id 聊天室id
         */
        void enterChatRoom(String room_id);

        /**
         * 获取历史消息
         * @param roomId
         */
        void pullMessageHistory(String roomId);

        /**
         * 获取聊天室成员
         * @param roomId
         */
        void getChatRoomMember(String roomId);

        /**
         * 获取用户信息
         */
        void getUserInfo();

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
    }
}
