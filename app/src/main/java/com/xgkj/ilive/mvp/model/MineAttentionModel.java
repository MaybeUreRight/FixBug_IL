package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.MineAttentionContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:11
 */

public class MineAttentionModel implements MineAttentionContract.Model {


    /**
     * APIDATA : {"ret":{"list":[{"to_pic":"http://192.168.0.127:9003/upload/","to_username":"落寞的小丑","to_uid":17},{"to_pic":"http://192.168.0.127:9003/upload/","to_username":"Harley","to_uid":15}]},"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "MineAttentionModel{" +
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
         * ret : {"list":[{"to_pic":"http://192.168.0.127:9003/upload/","to_username":"落寞的小丑","to_uid":17},{"to_pic":"http://192.168.0.127:9003/upload/","to_username":"Harley","to_uid":15}]}
         * code : 200
         */

        private RetBean ret;
        private int code;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "ret=" + ret +
                    ", code=" + code +
                    '}';
        }

        public RetBean getRet() {
            return ret;
        }

        public void setRet(RetBean ret) {
            this.ret = ret;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static class RetBean {
            private List<ListBean> list;

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * to_pic : http://192.168.0.127:9003/upload/
                 * to_username : 落寞的小丑
                 * to_uid : 17
                 */

                private String to_pic;
                private String to_username;
                private int to_uid;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "to_pic='" + to_pic + '\'' +
                            ", to_username='" + to_username + '\'' +
                            ", to_uid=" + to_uid +
                            '}';
                }

                public String getTo_pic() {
                    return to_pic;
                }

                public void setTo_pic(String to_pic) {
                    this.to_pic = to_pic;
                }

                public String getTo_username() {
                    return to_username;
                }

                public void setTo_username(String to_username) {
                    this.to_username = to_username;
                }

                public int getTo_uid() {
                    return to_uid;
                }

                public void setTo_uid(int to_uid) {
                    this.to_uid = to_uid;
                }
            }
        }
    }
}
