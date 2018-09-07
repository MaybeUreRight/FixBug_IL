package com.xgkj.ilive.mvp.model;

/**
 * Created by admin on 2017/11/3.
 */

public class FollowModel {
    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "ThirdLoginModel{" +
                "APIDATA=" + APIDATA +
                '}';
    }

    public APIDATABean getAPIDATA() {
        return APIDATA;
    }

    public void setAPIDATA(APIDATABean APIDATA) {
        this.APIDATA = APIDATA;
    }

    public static class APIDATABean {
        /**
         * nickname : nickname
         * access-token : e0c1b938ce320a2dc35128c67532ee6f
         * code : 200
         */

        private int code;
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public APIDATABean(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
