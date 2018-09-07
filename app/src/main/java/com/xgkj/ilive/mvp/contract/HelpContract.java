package com.xgkj.ilive.mvp.contract;

/**
 * 作者：刘净辉
 * 日期: 2017/8/28 0028 10:23
 */

public interface HelpContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
        /**
         * 提交建议
         * @param content
         */
        void commitSuggest(String content);
    }
}
