package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.TaUserInfoContact;
import com.xgkj.ilive.mvp.model.bean.TaHistoryListBean;
import com.xgkj.ilive.mvp.model.bean.TaUserInfoBean;

import java.util.List;

/**
 * Created by admin on 2017/11/2.
 */

public class TaUserModel  implements TaUserInfoContact.Model {

    private APIDATABean APIDATA;

    public APIDATABean getAPIDATA() {
        return APIDATA;
    }

    public void setAPIDATA(APIDATABean APIDATA) {
        this.APIDATA = APIDATA;
    }

    public static class APIDATABean {
        private int code;
        private List<TaUserInfoBean> list;
        private TaHistoryListBean history_list;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", list=" + list +
                    '}';
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<TaUserInfoBean> getList() {
            return list;
        }

        public void setList(List<TaUserInfoBean> list) {
            this.list = list;
        }

        public TaHistoryListBean getHistory_list() {
            return history_list;
        }

        public void setHistory_list(TaHistoryListBean history_list) {
            this.history_list = history_list;
        }
    }

}
