package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/9 0009 15:20
 */

public class BindingModel {

    /**
     * APIDATA : {"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "BindingModel{" +
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
         * code : 200
         */
        private String msg;
        private int code;

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "msg='" + msg + '\'' +
                    ", code=" + code +
                    '}';
        }

        public void setMsg(String msg) {
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
