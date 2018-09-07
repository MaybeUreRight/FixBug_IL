package com.xgkj.ilive.mvp.model;

/**
 * Created by admin on 2017/11/3.
 */

public class UserInfoModel {

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
        private int code;
        private RetBean ret;

        public APIDATABean(int code,RetBean ret) {
            this.code = code;
            this.ret = ret;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public RetBean getRet() {
            return ret;
        }

        public void setRet(RetBean ret) {
            this.ret = ret;
        }
    }

    public static class RetBean {
        private long video_count;
        private long fans_count;
        private long follow_count;

        public long getVideo_count() {
            return video_count;
        }

        public void setVideo_count(long video_count) {
            this.video_count = video_count;
        }

        public long getFans_count() {
            return fans_count;
        }

        public void setFans_count(long fans_count) {
            this.fans_count = fans_count;
        }

        public long getFollow_count() {
            return follow_count;
        }

        public void setFollow_count(long follow_count) {
            this.follow_count = follow_count;
        }
    }
}
