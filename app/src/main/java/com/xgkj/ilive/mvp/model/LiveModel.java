package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.LiveContract;

/**
 * 作者：刘净辉
 * 日期: 2017/8/6 0006 09:55
 */

public class LiveModel implements LiveContract.Model {


    /**
     * APIDATA : {"ret":{"httpPullUrl":"http://flv8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd.flv?netease=flv8f1debd5.live.126.net","hlsPullUrl":"http://pullhls8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd/playlist.m3u8","rtmpPullUrl":"rtmp://v8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd","name":"战狼2","pushUrl":"rtmp://p8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd?wsSecret=17c99f580eddd6c1c89c7dbe00566d65&wsTime=1502002290","ctime":1502002290578,"cid":"cd27fa4af24442cdbe6b55cd990760bd","id":"480"},"requestId":"live74a61e8a98f942f69d97e4b76514fa00","code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "LiveModel{" +
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
         * ret : {"httpPullUrl":"http://flv8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd.flv?netease=flv8f1debd5.live.126.net","hlsPullUrl":"http://pullhls8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd/playlist.m3u8","rtmpPullUrl":"rtmp://v8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd","name":"战狼2","pushUrl":"rtmp://p8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd?wsSecret=17c99f580eddd6c1c89c7dbe00566d65&wsTime=1502002290","ctime":1502002290578,"cid":"cd27fa4af24442cdbe6b55cd990760bd","id":"480"}
         * requestId : live74a61e8a98f942f69d97e4b76514fa00
         * code : 200
         */

        private RetBean ret;
        private String requestId;
        private int code;
        private String msg;


        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "ret=" + ret +
                    ", requestId='" + requestId + '\'' +
                    ", code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }

        public RetBean getRet() {
            return ret;
        }

        public void setRet(RetBean ret) {
            this.ret = ret;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static class RetBean {
            /**
             * httpPullUrl : http://flv8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd.flv?netease=flv8f1debd5.live.126.net
             * hlsPullUrl : http://pullhls8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd/playlist.m3u8
             * rtmpPullUrl : rtmp://v8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd
             * name : 战狼2
             * pushUrl : rtmp://p8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd?wsSecret=17c99f580eddd6c1c89c7dbe00566d65&wsTime=1502002290
             * ctime : 1502002290578
             * cid : cd27fa4af24442cdbe6b55cd990760bd
             * id : 480
             */

            private String httpPullUrl;
            private String hlsPullUrl;
            private String rtmpPullUrl;
            private String name;
            private String pushUrl;
            private long ctime;
            private String cid;
            private String id;

            @Override
            public String toString() {
                return "RetBean{" +
                        "httpPullUrl='" + httpPullUrl + '\'' +
                        ", hlsPullUrl='" + hlsPullUrl + '\'' +
                        ", rtmpPullUrl='" + rtmpPullUrl + '\'' +
                        ", name='" + name + '\'' +
                        ", pushUrl='" + pushUrl + '\'' +
                        ", ctime=" + ctime +
                        ", cid='" + cid + '\'' +
                        ", id='" + id + '\'' +
                        '}';
            }

            public String getHttpPullUrl() {
                return httpPullUrl;
            }

            public void setHttpPullUrl(String httpPullUrl) {
                this.httpPullUrl = httpPullUrl;
            }

            public String getHlsPullUrl() {
                return hlsPullUrl;
            }

            public void setHlsPullUrl(String hlsPullUrl) {
                this.hlsPullUrl = hlsPullUrl;
            }

            public String getRtmpPullUrl() {
                return rtmpPullUrl;
            }

            public void setRtmpPullUrl(String rtmpPullUrl) {
                this.rtmpPullUrl = rtmpPullUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPushUrl() {
                return pushUrl;
            }

            public void setPushUrl(String pushUrl) {
                this.pushUrl = pushUrl;
            }

            public long getCtime() {
                return ctime;
            }

            public void setCtime(long ctime) {
                this.ctime = ctime;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
