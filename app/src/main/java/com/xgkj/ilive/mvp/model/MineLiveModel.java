package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.MineLiveContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 14:40
 */

public class MineLiveModel implements MineLiveContract.Model {


    /**
     * APIDATA : {"ret":{"list":[{"id":"473","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/5ae11587a094459d91ae643d11770ebd?wsSecret=1e70f5bbd368a3721390a061709c87ae&wsTime=1501843576","pull_url":"rtmp://v8f1debd5.live.126.net/live/5ae11587a094459d91ae643d11770ebd","created":"1501843554","type":"1","type_value":"","introduce":"给 vv 那就看看就经济斤斤计较调查 v 被比较","video_pic":"http://static.devtool6.com/upload/9eb852c4a63d0092ab53c8fe9eacb7e1.jpg","status":"2","live_type":"1","video_count":"0","cid":"473","title":"直播","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"474","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/adf5b9a99cf14754b432f155661ae745?wsSecret=8d067e7acb5f73920aa75e225b6f199a&wsTime=1501843635","pull_url":"rtmp://v8f1debd5.live.126.net/live/adf5b9a99cf14754b432f155661ae745","created":"1501843613","type":"1","type_value":"","introduce":"给 vv 那就看看就经济斤斤计较调查 v 被比较","video_pic":"http://static.devtool6.com/upload/ce9e1d22ef01ee35db9e81db1ec4668f.jpg","status":"2","live_type":"1","video_count":"113","cid":"474","title":"直播","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"475","cid_url":"acbd3dcfd2ba4fc9bba158aec21a47c1_1501843664537_1501843674280_20354298-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/acbd3dcfd2ba4fc9bba158aec21a47c1?wsSecret=ce6621231c9d3caf1b11e7bc6248599a&wsTime=1501843659","pull_url":"rtmp://v8f1debd5.live.126.net/live/acbd3dcfd2ba4fc9bba158aec21a47c1","created":"1501843638","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/336e6d6ee0a50feccb0b983e859ff0d0.jpg","status":"2","live_type":"1","video_count":"0","cid":"475","title":"斤斤计较","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"476","cid_url":"52af8acf16eb4a91add58f40bc53123f_1501843692098_1501844451853_20354356-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/52af8acf16eb4a91add58f40bc53123f?wsSecret=52de41a9d4290639ac5540d79e4aea5a&wsTime=1501843683","pull_url":"rtmp://v8f1debd5.live.126.net/live/52af8acf16eb4a91add58f40bc53123f","created":"1501843662","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/7d6742a040d4f65e9f3b310101f50053.jpg","status":"2","live_type":"1","video_count":"0","cid":"476","title":"斤斤计较","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"477","cid_url":"edaf434b7b2140e69724906e5431a338_1501843768175_1501843774765_20354526-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/edaf434b7b2140e69724906e5431a338?wsSecret=50efa9ef28e680c3f4ce278c92386404&wsTime=1501843762","pull_url":"rtmp://v8f1debd5.live.126.net/live/edaf434b7b2140e69724906e5431a338","created":"1501843740","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/a5c2c6151d17e8803fa7291b388ec8e9.jpg","status":"2","live_type":"1","video_count":"16","cid":"477","title":"斤斤计较","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"479","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/fef441e8e49343829219108cf8825886?wsSecret=a74d636722def1f3e9c25dd60c4d2f1c&wsTime=1502002117","pull_url":"rtmp://v8f1debd5.live.126.net/live/fef441e8e49343829219108cf8825886","created":"1502002092","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/35993e7c5908fd5fe24722cfd69a2b17.jpg","status":"1","live_type":"1","video_count":"0","cid":"479","title":"图图","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"480","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd?wsSecret=17c99f580eddd6c1c89c7dbe00566d65&wsTime=1502002290","pull_url":"rtmp://v8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd","created":"1502002265","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/92db290e6b61155641977fca669b455d.jpg","status":"1","live_type":"1","video_count":"0","cid":"480","title":"战狼2","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"481","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c?wsSecret=8ef5077d9d9aeee10846052ee596d193&wsTime=1502003542","pull_url":"rtmp://v8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c","created":"1502003517","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/02976e499a629f94a443c6c6d25b4985.jpg","status":"1","live_type":"1","video_count":"7","cid":"481","title":"路途","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"482","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/9b29e43c9bd24e1ca8f3a0feb8bc8a7e?wsSecret=507287ac8d3b03abb0733bf242dee114&wsTime=1502004415","pull_url":"rtmp://v8f1debd5.live.126.net/live/9b29e43c9bd24e1ca8f3a0feb8bc8a7e","created":"1502004390","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/ca69c90dc616f972a47e8e9bf6742f2d.jpg","status":"1","live_type":"1","video_count":"0","cid":"482","title":"兔子","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"483","cid_url":"7b87efad130a43e4acd25bb75b864fd6_1502007727488_1502007816486_20670496-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/7b87efad130a43e4acd25bb75b864fd6?wsSecret=b4eff33b2ff0ba7ed8f6954fb1a0ddaf&wsTime=1502007725","pull_url":"rtmp://v8f1debd5.live.126.net/live/7b87efad130a43e4acd25bb75b864fd6","created":"1502007700","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/e6755bbdcc952c2c9f8370109dd01359.jpg","status":"1","live_type":"1","video_count":"0","cid":"483","title":"具体","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"}]},"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "MineLiveModel{" +
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
         * ret : {"list":[{"id":"473","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/5ae11587a094459d91ae643d11770ebd?wsSecret=1e70f5bbd368a3721390a061709c87ae&wsTime=1501843576","pull_url":"rtmp://v8f1debd5.live.126.net/live/5ae11587a094459d91ae643d11770ebd","created":"1501843554","type":"1","type_value":"","introduce":"给 vv 那就看看就经济斤斤计较调查 v 被比较","video_pic":"http://static.devtool6.com/upload/9eb852c4a63d0092ab53c8fe9eacb7e1.jpg","status":"2","live_type":"1","video_count":"0","cid":"473","title":"直播","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"474","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/adf5b9a99cf14754b432f155661ae745?wsSecret=8d067e7acb5f73920aa75e225b6f199a&wsTime=1501843635","pull_url":"rtmp://v8f1debd5.live.126.net/live/adf5b9a99cf14754b432f155661ae745","created":"1501843613","type":"1","type_value":"","introduce":"给 vv 那就看看就经济斤斤计较调查 v 被比较","video_pic":"http://static.devtool6.com/upload/ce9e1d22ef01ee35db9e81db1ec4668f.jpg","status":"2","live_type":"1","video_count":"113","cid":"474","title":"直播","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"475","cid_url":"acbd3dcfd2ba4fc9bba158aec21a47c1_1501843664537_1501843674280_20354298-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/acbd3dcfd2ba4fc9bba158aec21a47c1?wsSecret=ce6621231c9d3caf1b11e7bc6248599a&wsTime=1501843659","pull_url":"rtmp://v8f1debd5.live.126.net/live/acbd3dcfd2ba4fc9bba158aec21a47c1","created":"1501843638","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/336e6d6ee0a50feccb0b983e859ff0d0.jpg","status":"2","live_type":"1","video_count":"0","cid":"475","title":"斤斤计较","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"476","cid_url":"52af8acf16eb4a91add58f40bc53123f_1501843692098_1501844451853_20354356-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/52af8acf16eb4a91add58f40bc53123f?wsSecret=52de41a9d4290639ac5540d79e4aea5a&wsTime=1501843683","pull_url":"rtmp://v8f1debd5.live.126.net/live/52af8acf16eb4a91add58f40bc53123f","created":"1501843662","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/7d6742a040d4f65e9f3b310101f50053.jpg","status":"2","live_type":"1","video_count":"0","cid":"476","title":"斤斤计较","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"477","cid_url":"edaf434b7b2140e69724906e5431a338_1501843768175_1501843774765_20354526-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/edaf434b7b2140e69724906e5431a338?wsSecret=50efa9ef28e680c3f4ce278c92386404&wsTime=1501843762","pull_url":"rtmp://v8f1debd5.live.126.net/live/edaf434b7b2140e69724906e5431a338","created":"1501843740","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/a5c2c6151d17e8803fa7291b388ec8e9.jpg","status":"2","live_type":"1","video_count":"16","cid":"477","title":"斤斤计较","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"479","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/fef441e8e49343829219108cf8825886?wsSecret=a74d636722def1f3e9c25dd60c4d2f1c&wsTime=1502002117","pull_url":"rtmp://v8f1debd5.live.126.net/live/fef441e8e49343829219108cf8825886","created":"1502002092","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/35993e7c5908fd5fe24722cfd69a2b17.jpg","status":"1","live_type":"1","video_count":"0","cid":"479","title":"图图","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"480","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd?wsSecret=17c99f580eddd6c1c89c7dbe00566d65&wsTime=1502002290","pull_url":"rtmp://v8f1debd5.live.126.net/live/cd27fa4af24442cdbe6b55cd990760bd","created":"1502002265","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/92db290e6b61155641977fca669b455d.jpg","status":"1","live_type":"1","video_count":"0","cid":"480","title":"战狼2","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"481","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c?wsSecret=8ef5077d9d9aeee10846052ee596d193&wsTime=1502003542","pull_url":"rtmp://v8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c","created":"1502003517","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/02976e499a629f94a443c6c6d25b4985.jpg","status":"1","live_type":"1","video_count":"7","cid":"481","title":"路途","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"482","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/9b29e43c9bd24e1ca8f3a0feb8bc8a7e?wsSecret=507287ac8d3b03abb0733bf242dee114&wsTime=1502004415","pull_url":"rtmp://v8f1debd5.live.126.net/live/9b29e43c9bd24e1ca8f3a0feb8bc8a7e","created":"1502004390","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/ca69c90dc616f972a47e8e9bf6742f2d.jpg","status":"1","live_type":"1","video_count":"0","cid":"482","title":"兔子","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"},{"id":"483","cid_url":"7b87efad130a43e4acd25bb75b864fd6_1502007727488_1502007816486_20670496-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/7b87efad130a43e4acd25bb75b864fd6?wsSecret=b4eff33b2ff0ba7ed8f6954fb1a0ddaf&wsTime=1502007725","pull_url":"rtmp://v8f1debd5.live.126.net/live/7b87efad130a43e4acd25bb75b864fd6","created":"1502007700","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/e6755bbdcc952c2c9f8370109dd01359.jpg","status":"1","live_type":"1","video_count":"0","cid":"483","title":"具体","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg"}]}
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

            @Override
            public String toString() {
                return "RetBean{" +
                        "list=" + list +
                        '}';
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 473
                 * cid_url :
                 * push_url : rtmp://p8f1debd5.live.126.net/live/5ae11587a094459d91ae643d11770ebd?wsSecret=1e70f5bbd368a3721390a061709c87ae&wsTime=1501843576
                 * pull_url : rtmp://v8f1debd5.live.126.net/live/5ae11587a094459d91ae643d11770ebd
                 * created : 1501843554
                 * type : 1
                 * type_value :
                 * introduce : 给 vv 那就看看就经济斤斤计较调查 v 被比较
                 * video_pic : http://static.devtool6.com/upload/9eb852c4a63d0092ab53c8fe9eacb7e1.jpg
                 * status : 2
                 * live_type : 1
                 * video_count : 0
                 * cid : 473
                 * title : 直播
                 * username : 小刘
                 * user_pic : http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg
                 */

                private String id;
                private String cid_url;
                private String push_url;
                private String pull_url;
                private String created;
                private String type;
                private String type_value;
                private String introduce;
                private String video_pic;
                private String status;
                private String live_type;
                private String video_count;
                private String cid;
                private String title;
                private String username;
                private String user_pic;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "id='" + id + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", push_url='" + push_url + '\'' +
                            ", pull_url='" + pull_url + '\'' +
                            ", created='" + created + '\'' +
                            ", type='" + type + '\'' +
                            ", type_value='" + type_value + '\'' +
                            ", introduce='" + introduce + '\'' +
                            ", video_pic='" + video_pic + '\'' +
                            ", status='" + status + '\'' +
                            ", live_type='" + live_type + '\'' +
                            ", video_count='" + video_count + '\'' +
                            ", cid='" + cid + '\'' +
                            ", title='" + title + '\'' +
                            ", username='" + username + '\'' +
                            ", user_pic='" + user_pic + '\'' +
                            '}';
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCid_url() {
                    return cid_url;
                }

                public void setCid_url(String cid_url) {
                    this.cid_url = cid_url;
                }

                public String getPush_url() {
                    return push_url;
                }

                public void setPush_url(String push_url) {
                    this.push_url = push_url;
                }

                public String getPull_url() {
                    return pull_url;
                }

                public void setPull_url(String pull_url) {
                    this.pull_url = pull_url;
                }

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getType_value() {
                    return type_value;
                }

                public void setType_value(String type_value) {
                    this.type_value = type_value;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getVideo_pic() {
                    return video_pic;
                }

                public void setVideo_pic(String video_pic) {
                    this.video_pic = video_pic;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getLive_type() {
                    return live_type;
                }

                public void setLive_type(String live_type) {
                    this.live_type = live_type;
                }

                public String getVideo_count() {
                    return video_count;
                }

                public void setVideo_count(String video_count) {
                    this.video_count = video_count;
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getUser_pic() {
                    return user_pic;
                }

                public void setUser_pic(String user_pic) {
                    this.user_pic = user_pic;
                }
            }
        }
    }
}
