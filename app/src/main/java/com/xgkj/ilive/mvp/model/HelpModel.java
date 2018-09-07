package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.HelpContract;

/**
 * 作者：刘净辉
 * 日期: 2017/8/28 0028 10:23
 */

public class HelpModel implements HelpContract.Model {


    /**
     * APIDATA : {"code":404,"msg":"投诉建议不能为空"}
     */

    private APIDATABean APIDATA;

    public APIDATABean getAPIDATA() {
        return APIDATA;
    }

    public void setAPIDATA(APIDATABean APIDATA) {
        this.APIDATA = APIDATA;
    }

    public static class APIDATABean {
        /**
         * code : 404
         * msg : 投诉建议不能为空
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
