package com.xgkj.ilive.mvp.model;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/9 0009 10:48
 */

public class BrowseRecordsVideoModel {

    /**
     * APIDATA : {"ret":{"list":[{"id":"478","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/551a57129664453ab252887a988da418?wsSecret=4204a6d2a05d6bccc2e6e01324b17fbd&wsTime=1502001950","pull_url":"rtmp://v8f1debd5.live.126.net/live/551a57129664453ab252887a988da418","created":"1502001925","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/7c4f9e272b40b4e98228991d1a96220c.jpg","status":"2","live_type":"1","video_count":"6","cid":"478","title":"测试","username":"杨东","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","view_time":"1502075242"},{"id":"481","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c?wsSecret=8ef5077d9d9aeee10846052ee596d193&wsTime=1502003542","pull_url":"rtmp://v8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c","created":"1502003517","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/02976e499a629f94a443c6c6d25b4985.jpg","status":"1","live_type":"1","video_count":"7","cid":"481","title":"路途","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075228"},{"id":"486","cid_url":"94a505dd74864b86983e532adaf55afc_1502008523272_1502008583524_20671650-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/94a505dd74864b86983e532adaf55afc?wsSecret=1aa16d00000ec535facdfbd8cb80f83f&wsTime=1502008508","pull_url":"rtmp://v8f1debd5.live.126.net/live/94a505dd74864b86983e532adaf55afc","created":"1502008483","type":"1","type_value":"","introduce":"dju","video_pic":"http://static.devtool6.com/upload/d4ef8ce285024bff3294819a853bb79e.jpg","status":"1","live_type":"1","video_count":"8","cid":"486","title":"规划好","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075211"},{"id":"490","cid_url":"42b9011119df4bdaba0a2cce73bb9823_1502012975429_1502013027372_20677638-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/42b9011119df4bdaba0a2cce73bb9823?wsSecret=6f9a713ffcfe727f13970615d34bb53a&wsTime=1502012970","pull_url":"rtmp://v8f1debd5.live.126.net/live/42b9011119df4bdaba0a2cce73bb9823","created":"1502012945","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/9cab2c0588e72be573ca9e3cdb17b756.jpg","status":"1","live_type":"1","video_count":"6","cid":"490","title":"考虑","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075193"},{"id":"491","cid_url":"7aadad97951e4dc5b18ea40682abdb7f_1502013464367_1502013476983_20678297-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/7aadad97951e4dc5b18ea40682abdb7f?wsSecret=989bb27c0328a50c98fa5126680e2b8e&wsTime=1502013450","pull_url":"rtmp://v8f1debd5.live.126.net/live/7aadad97951e4dc5b18ea40682abdb7f","created":"1502013425","type":"1","type_value":"","introduce":"哦天头发","video_pic":"http://static.devtool6.com/upload/343718ef513e2b2884e0a000081da0a7.jpg","status":"2","live_type":"1","video_count":"7","cid":"491","title":"染发膏规划","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075171"},{"id":"492","cid_url":"","push_url":null,"pull_url":null,"created":"1502075024","type":"1","type_value":"","introduce":"发广告","video_pic":"http://static.devtool6.com/upload/718fa1e28a9126d19cf5bf2010a602b3.jpg","status":"1","live_type":"1","video_count":"16","cid":"492","title":"ghh","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075089"},{"id":"493","cid_url":"","push_url":null,"pull_url":null,"created":"1502075050","type":"1","type_value":"","introduce":"哥哥哥哥","video_pic":"http://static.devtool6.com/upload/44c72cce26baf0c8d0f15117423f0b89.jpg","status":"1","live_type":"1","video_count":"19","cid":"493","title":"vvv","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075075"},{"id":"494","cid_url":"","push_url":null,"pull_url":null,"created":"1502084602","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/0c776bf97660aafdfc0ad4dda01dc192.jpg","status":"1","live_type":"1","video_count":"8","cid":"494","title":"gvv","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502084609"},{"id":"522","cid_url":"31c3d637c3fb4435a931ac1f703b6652_1502103508255_1502103574200_20803327-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/31c3d637c3fb4435a931ac1f703b6652?wsSecret=52ad53c3ee0d8cbf12250117caa4fd3d&wsTime=1502103503","pull_url":"rtmp://v8f1debd5.live.126.net/live/31c3d637c3fb4435a931ac1f703b6652","created":"1502103476","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/986512885d9bdc80f228ac4845a67b05.jpg","status":"1","live_type":"1","video_count":"19","cid":"522","title":"看了一下","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502103513"}]},"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "BrowseRecordsVideoModel{" +
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
         * ret : {"list":[{"id":"478","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/551a57129664453ab252887a988da418?wsSecret=4204a6d2a05d6bccc2e6e01324b17fbd&wsTime=1502001950","pull_url":"rtmp://v8f1debd5.live.126.net/live/551a57129664453ab252887a988da418","created":"1502001925","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/7c4f9e272b40b4e98228991d1a96220c.jpg","status":"2","live_type":"1","video_count":"6","cid":"478","title":"测试","username":"杨东","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","view_time":"1502075242"},{"id":"481","cid_url":"","push_url":"rtmp://p8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c?wsSecret=8ef5077d9d9aeee10846052ee596d193&wsTime=1502003542","pull_url":"rtmp://v8f1debd5.live.126.net/live/9d1bf3828566492e9ad1b830a147fb3c","created":"1502003517","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/02976e499a629f94a443c6c6d25b4985.jpg","status":"1","live_type":"1","video_count":"7","cid":"481","title":"路途","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075228"},{"id":"486","cid_url":"94a505dd74864b86983e532adaf55afc_1502008523272_1502008583524_20671650-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/94a505dd74864b86983e532adaf55afc?wsSecret=1aa16d00000ec535facdfbd8cb80f83f&wsTime=1502008508","pull_url":"rtmp://v8f1debd5.live.126.net/live/94a505dd74864b86983e532adaf55afc","created":"1502008483","type":"1","type_value":"","introduce":"dju","video_pic":"http://static.devtool6.com/upload/d4ef8ce285024bff3294819a853bb79e.jpg","status":"1","live_type":"1","video_count":"8","cid":"486","title":"规划好","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075211"},{"id":"490","cid_url":"42b9011119df4bdaba0a2cce73bb9823_1502012975429_1502013027372_20677638-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/42b9011119df4bdaba0a2cce73bb9823?wsSecret=6f9a713ffcfe727f13970615d34bb53a&wsTime=1502012970","pull_url":"rtmp://v8f1debd5.live.126.net/live/42b9011119df4bdaba0a2cce73bb9823","created":"1502012945","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/9cab2c0588e72be573ca9e3cdb17b756.jpg","status":"1","live_type":"1","video_count":"6","cid":"490","title":"考虑","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075193"},{"id":"491","cid_url":"7aadad97951e4dc5b18ea40682abdb7f_1502013464367_1502013476983_20678297-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/7aadad97951e4dc5b18ea40682abdb7f?wsSecret=989bb27c0328a50c98fa5126680e2b8e&wsTime=1502013450","pull_url":"rtmp://v8f1debd5.live.126.net/live/7aadad97951e4dc5b18ea40682abdb7f","created":"1502013425","type":"1","type_value":"","introduce":"哦天头发","video_pic":"http://static.devtool6.com/upload/343718ef513e2b2884e0a000081da0a7.jpg","status":"2","live_type":"1","video_count":"7","cid":"491","title":"染发膏规划","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075171"},{"id":"492","cid_url":"","push_url":null,"pull_url":null,"created":"1502075024","type":"1","type_value":"","introduce":"发广告","video_pic":"http://static.devtool6.com/upload/718fa1e28a9126d19cf5bf2010a602b3.jpg","status":"1","live_type":"1","video_count":"16","cid":"492","title":"ghh","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075089"},{"id":"493","cid_url":"","push_url":null,"pull_url":null,"created":"1502075050","type":"1","type_value":"","introduce":"哥哥哥哥","video_pic":"http://static.devtool6.com/upload/44c72cce26baf0c8d0f15117423f0b89.jpg","status":"1","live_type":"1","video_count":"19","cid":"493","title":"vvv","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502075075"},{"id":"494","cid_url":"","push_url":null,"pull_url":null,"created":"1502084602","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/0c776bf97660aafdfc0ad4dda01dc192.jpg","status":"1","live_type":"1","video_count":"8","cid":"494","title":"gvv","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502084609"},{"id":"522","cid_url":"31c3d637c3fb4435a931ac1f703b6652_1502103508255_1502103574200_20803327-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/31c3d637c3fb4435a931ac1f703b6652?wsSecret=52ad53c3ee0d8cbf12250117caa4fd3d&wsTime=1502103503","pull_url":"rtmp://v8f1debd5.live.126.net/live/31c3d637c3fb4435a931ac1f703b6652","created":"1502103476","type":"1","type_value":"","introduce":"","video_pic":"http://static.devtool6.com/upload/986512885d9bdc80f228ac4845a67b05.jpg","status":"1","live_type":"1","video_count":"19","cid":"522","title":"看了一下","username":"小刘","user_pic":"http://static.devtool6.com/upload/http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","view_time":"1502103513"}]}
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
                 * id : 478
                 * cid_url :
                 * push_url : rtmp://p8f1debd5.live.126.net/live/551a57129664453ab252887a988da418?wsSecret=4204a6d2a05d6bccc2e6e01324b17fbd&wsTime=1502001950
                 * pull_url : rtmp://v8f1debd5.live.126.net/live/551a57129664453ab252887a988da418
                 * created : 1502001925
                 * type : 1
                 * type_value :
                 * introduce :
                 * video_pic : http://static.devtool6.com/upload/7c4f9e272b40b4e98228991d1a96220c.jpg
                 * status : 2
                 * live_type : 1
                 * video_count : 6
                 * cid : 478
                 * title : 测试
                 * username : 杨东
                 * user_pic : http://static.devtool6.com/upload/http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg
                 * view_time : 1502075242
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
                private String view_time;

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
                            ", view_time='" + view_time + '\'' +
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

                public String getView_time() {
                    return view_time;
                }

                public void setView_time(String view_time) {
                    this.view_time = view_time;
                }
            }
        }
    }
}
