package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/7 0007 18:25
 */

public class JoinModel {


    /**
     * APIDATA : {"ret":{"code":200,"list":{"is_follow":0,"user_pic":"","username":"","cid_url":"","live_online_count":""}}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "JoinModel{" +
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
         * ret : {"code":200,"list":{"is_follow":0,"user_pic":"","username":"","cid_url":"","live_online_count":""}}
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
             * list : {"is_follow":0,"user_pic":"","username":"","cid_url":"","live_online_count":""}
             */

            private int code;
            private ListBean list;
            private String msg;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            @Override
            public String toString() {
                return "RetBean{" +
                        "code=" + code +
                        ", list=" + list +
                        ", msg='" + msg + '\'' +
                        '}';
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public ListBean getList() {
                return list;
            }

            public void setList(ListBean list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * is_follow : 0
                 * user_pic :
                 * username :
                 * cid_url :
                 * live_online_count :
                 */

                private int is_follow;
                private String user_pic;
                private String username;
                private String cid_url;
                private String live_online_count;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "is_follow=" + is_follow +
                            ", user_pic='" + user_pic + '\'' +
                            ", username='" + username + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", live_online_count='" + live_online_count + '\'' +
                            '}';
                }

                public int getIs_follow() {
                    return is_follow;
                }

                public void setIs_follow(int is_follow) {
                    this.is_follow = is_follow;
                }

                public String getUser_pic() {
                    return user_pic;
                }

                public void setUser_pic(String user_pic) {
                    this.user_pic = user_pic;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getCid_url() {
                    return cid_url;
                }

                public void setCid_url(String cid_url) {
                    this.cid_url = cid_url;
                }

                public String getLive_online_count() {
                    return live_online_count;
                }

                public void setLive_online_count(String live_online_count) {
                    this.live_online_count = live_online_count;
                }
            }
        }
    }
}
