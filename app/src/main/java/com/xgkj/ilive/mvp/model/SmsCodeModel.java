package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 15:39
 */

public class SmsCodeModel {

    /**
     * APIDATA : {"code":200}
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
         * code : 200
         */

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SmsCodeModel{" +
                "APIDATA=" + APIDATA +
                '}';
    }
}
