package com.xgkj.ilive.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：刘净辉
 * 日期: 2017/8/3 0003 18:35
 */

public class ThirdLoginModel {


    /**
     * APIDATA : {"nickname":"nickname","access-token":"e0c1b938ce320a2dc35128c67532ee6f","code":200}
     */

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

        private String nickname;
        @SerializedName("access-token")
        private String accesstoken;
        private int code;
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public APIDATABean(String nickname, String accesstoken, int code, String msg) {
            this.nickname = nickname;
            this.accesstoken = accesstoken;
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "nickname='" + nickname + '\'' +
                    ", accesstoken='" + accesstoken + '\'' +
                    ", code=" + code +
                    '}';
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAccesstoken() {
            return accesstoken;
        }

        public void setAccesstoken(String accesstoken) {
            this.accesstoken = accesstoken;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
