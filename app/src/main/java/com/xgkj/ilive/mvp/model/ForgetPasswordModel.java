package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.ForgetPasswordContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/16 0016 12:00
 */

public class ForgetPasswordModel implements ForgetPasswordContract.Model {


    /**
     * APIDATA : {"code":404,"msg":"验证码不正确"}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "ForgetPasswordModel{" +
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
         * code : 404
         * msg : 验证码不正确
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
