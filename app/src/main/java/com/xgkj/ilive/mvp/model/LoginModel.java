package com.xgkj.ilive.mvp.model;

import com.google.gson.annotations.SerializedName;
import com.xgkj.ilive.mvp.contract.LoginContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 14:15
 */

public class LoginModel implements LoginContract.Model {


    /**
     * APIDATA : {"ret":{"code":200,"nickname":null,"mobile":null,"access-token":"e10adc3949ba59abbe56e057f20f883e","pic":"http://192.168.0.127:9003/upload/dsjfkdshfkjdshf.jpg"}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "LoginModel{" +
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
         * ret : {"code":200,"nickname":null,"mobile":null,"access-token":"e10adc3949ba59abbe56e057f20f883e","pic":"http://192.168.0.127:9003/upload/dsjfkdshfkjdshf.jpg"}
         */

        private RetBean ret;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "ret=" + ret +
                    '}';
        }

        public RetBean getRet() {
            return ret;
        }

        public void setRet(RetBean ret) {
            this.ret = ret;
        }

        public static class RetBean {
            /**
             * code : 200
             * nickname : null
             * mobile : null
             * access-token : e10adc3949ba59abbe56e057f20f883e
             * pic : http://192.168.0.127:9003/upload/dsjfkdshfkjdshf.jpg
             */

            private int code;
            private Object nickname;
            private Object mobile;
            @SerializedName("access-token")
            private String accesstoken;
            private String pic;
            private String msg;

            @Override
            public String toString() {
                return "RetBean{" +
                        "code=" + code +
                        ", nickname=" + nickname +
                        ", mobile=" + mobile +
                        ", accesstoken='" + accesstoken + '\'' +
                        ", pic='" + pic + '\'' +
                        ", msg='" + msg + '\'' +
                        '}';
            }

            public String getMsg() {
                return msg;
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

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public String getAccesstoken() {
                return accesstoken;
            }

            public void setAccesstoken(String accesstoken) {
                this.accesstoken = accesstoken;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
