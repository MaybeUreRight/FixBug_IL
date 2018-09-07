package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/23 0023 10:38
 */

public class CommentModel {

    /**
     * APIDATA : {"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "CommentModel{" +
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

        private int code;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    '}';
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
