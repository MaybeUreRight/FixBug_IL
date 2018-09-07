package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/10 0010 14:48
 */

public class UpdateInfoModel {

    /**
     * APIDATA : {"code":200,"msg":null}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "UpdateInfoModel{" +
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
         * msg : null
         */

        private int code;
        private String msg;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }

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
