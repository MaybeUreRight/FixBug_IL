package com.xgkj.ilive.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/23 0023 10:55
 * 作用: 查询聊天消息实体类
 */

public class QueryChatMessageModel {

    /**
     * APIDATA : {"code":200,"ret":[{"access-token":"92dcaed7c6663af7683d5d912314cedb","content":"你好呢","filtercontent":"你好呢","nickname":"小刘","pic":"http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg","time":"2017-08-23 10:49:47","userid":"92dcaed7c6663af7683d5d912314cedb"},{"access-token":"92dcaed7c6663af7683d5d912314cedb","content":"你妈的，干什么呢？","filtercontent":"***，***什么呢？","nickname":"小刘","pic":"http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg","time":"2017-08-23 10:50:03","userid":"92dcaed7c6663af7683d5d912314cedb"}]}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "QueryChatMessageModel{" +
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
         * ret : [{"access-token":"92dcaed7c6663af7683d5d912314cedb","content":"你好呢","filtercontent":"你好呢","nickname":"小刘","pic":"http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg","time":"2017-08-23 10:49:47","userid":"92dcaed7c6663af7683d5d912314cedb"},{"access-token":"92dcaed7c6663af7683d5d912314cedb","content":"你妈的，干什么呢？","filtercontent":"***，***什么呢？","nickname":"小刘","pic":"http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg","time":"2017-08-23 10:50:03","userid":"92dcaed7c6663af7683d5d912314cedb"}]
         */

        private int code;
        private List<RetBean> ret;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", ret=" + ret +
                    '}';
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<RetBean> getRet() {
            return ret;
        }

        public void setRet(List<RetBean> ret) {
            this.ret = ret;
        }

        public static class RetBean {
            /**
             * access-token : 92dcaed7c6663af7683d5d912314cedb
             * content : 你好呢
             * filtercontent : 你好呢
             * nickname : 小刘
             * pic : http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg
             * time : 2017-08-23 10:49:47
             * userid : 92dcaed7c6663af7683d5d912314cedb
             */

            @SerializedName("access-token")
            private String accesstoken;
            private String content;
            private String filtercontent;
            private String nickname;
            private String pic;
            private String time;
            private String userid;

            @Override
            public String toString() {
                return "RetBean{" +
                        "accesstoken='" + accesstoken + '\'' +
                        ", content='" + content + '\'' +
                        ", filtercontent='" + filtercontent + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", pic='" + pic + '\'' +
                        ", time='" + time + '\'' +
                        ", userid='" + userid + '\'' +
                        '}';
            }

            public String getAccesstoken() {
                return accesstoken;
            }

            public void setAccesstoken(String accesstoken) {
                this.accesstoken = accesstoken;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getFiltercontent() {
                return filtercontent;
            }

            public void setFiltercontent(String filtercontent) {
                this.filtercontent = filtercontent;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }
}
