package com.xgkj.ilive.mvp.model;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/14 0014 17:26
 */

public class NewsSerachModel {


    /**
     * APIDATA : {"code":200,"ret":{"hot_search":["能力风暴机器人","钛金属的锻造","GF加工方案"],"live_list":[],"news_list":[{"created":"1502692607","id":"406","pic":"http://static.devtool6.com/upload/20170814/52601502692893.jpg","title":"我是谷歌机器人，我认为人类天生不适合在科技行业工作","view":"368"}],"video_list":[{"cid":"2111781","cid_url":"90b2b019-d4e2-41f8-9afe-46db304dfec8.mp4","created":"1496222168","live_type":"2","title":"能力风暴机器人实验室\u2014智能柔性制造系统","user_pic":"http://static.devtool6.com/http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_pic":"http://static.devtool6.com/upload/20170531/63521496222157.png","view_count":"34"}]}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "NewsSerachModel{" +
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
         * ret : {"hot_search":["能力风暴机器人","钛金属的锻造","GF加工方案"],"live_list":[],"news_list":[{"created":"1502692607","id":"406","pic":"http://static.devtool6.com/upload/20170814/52601502692893.jpg","title":"我是谷歌机器人，我认为人类天生不适合在科技行业工作","view":"368"}],"video_list":[{"cid":"2111781","cid_url":"90b2b019-d4e2-41f8-9afe-46db304dfec8.mp4","created":"1496222168","live_type":"2","title":"能力风暴机器人实验室\u2014智能柔性制造系统","user_pic":"http://static.devtool6.com/http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_pic":"http://static.devtool6.com/upload/20170531/63521496222157.png","view_count":"34"}]}
         */

        private int code;
        private RetBean ret;
        private String msg;

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

        public RetBean getRet() {
            return ret;
        }

        public void setRet(RetBean ret) {
            this.ret = ret;
        }

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", ret=" + ret +
                    ", msg='" + msg + '\'' +
                    '}';
        }

        public static class RetBean {
            private List<String> hot_search;
            private List<LiveListBean> live_list;
            private List<NewsListBean> news_list;
            private List<VideoListBean> video_list;

            public List<String> getHot_search() {
                return hot_search;
            }

            public void setHot_search(List<String> hot_search) {
                this.hot_search = hot_search;
            }

            public List<LiveListBean> getLive_list() {
                return live_list;
            }

            public void setLive_list(List<LiveListBean> live_list) {
                this.live_list = live_list;
            }

            public List<NewsListBean> getNews_list() {
                return news_list;
            }

            public void setNews_list(List<NewsListBean> news_list) {
                this.news_list = news_list;
            }

            public List<VideoListBean> getVideo_list() {
                return video_list;
            }

            @Override
            public String toString() {
                return "RetBean{" +
                        "hot_search=" + hot_search +
                        ", live_list=" + live_list +
                        ", news_list=" + news_list +
                        ", video_list=" + video_list +
                        '}';
            }

            public void setVideo_list(List<VideoListBean> video_list) {
                this.video_list = video_list;
            }

            public static class  LiveListBean{
                private String cid;
                private String cid_url;
                private String company_title;
                private String live_time;
                private String live_type;
                private String pic;
                private String pull_url;
                private String push_url;
                private String title;
                private String view;

                @Override
                public String toString() {
                    return "LiveListBean{" +
                            "cid='" + cid + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", company_title='" + company_title + '\'' +
                            ", live_time='" + live_time + '\'' +
                            ", live_type='" + live_type + '\'' +
                            ", pic='" + pic + '\'' +
                            ", pull_url='" + pull_url + '\'' +
                            ", push_url='" + push_url + '\'' +
                            ", title='" + title + '\'' +
                            ", view='" + view + '\'' +
                            '}';
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getCid_url() {
                    return cid_url;
                }

                public void setCid_url(String cid_url) {
                    this.cid_url = cid_url;
                }

                public String getCompany_title() {
                    return company_title;
                }

                public void setCompany_title(String company_title) {
                    this.company_title = company_title;
                }

                public String getLive_time() {
                    return live_time;
                }

                public void setLive_time(String live_time) {
                    this.live_time = live_time;
                }

                public String getLive_type() {
                    return live_type;
                }

                public void setLive_type(String live_type) {
                    this.live_type = live_type;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getPull_url() {
                    return pull_url;
                }

                public void setPull_url(String pull_url) {
                    this.pull_url = pull_url;
                }

                public String getPush_url() {
                    return push_url;
                }

                public void setPush_url(String push_url) {
                    this.push_url = push_url;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getView() {
                    return view;
                }

                public void setView(String view) {
                    this.view = view;
                }
            }
            public static class NewsListBean {
                /**
                 * created : 1502692607
                 * id : 406
                 * pic : http://static.devtool6.com/upload/20170814/52601502692893.jpg
                 * title : 我是谷歌机器人，我认为人类天生不适合在科技行业工作
                 * view : 368
                 */

                private String created;
                private String id;
                private String pic;
                private String title;
                private String view;
                private String content;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                @Override
                public String toString() {
                    return "NewsListBean{" +
                            "created='" + created + '\'' +
                            ", id='" + id + '\'' +
                            ", pic='" + pic + '\'' +
                            ", title='" + title + '\'' +
                            ", view='" + view + '\'' +
                            ", content='" + content + '\'' +
                            '}';
                }

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getView() {
                    return view;
                }

                public void setView(String view) {
                    this.view = view;
                }
            }

            public static class VideoListBean {
                /**
                 * cid : 2111781
                 * cid_url : 90b2b019-d4e2-41f8-9afe-46db304dfec8.mp4
                 * created : 1496222168
                 * live_type : 2
                 * title : 能力风暴机器人实验室—智能柔性制造系统
                 * user_pic : http://static.devtool6.com/http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg
                 * username : 落寞的小丑
                 * video_pic : http://static.devtool6.com/upload/20170531/63521496222157.png
                 * view_count : 34
                 */

                private String cid;
                private String cid_url;
                private String created;
                private String live_type;
                private String title;
                private String user_pic;
                private String username;
                private String video_pic;
                private String view_count;
                private String company_title;

                public String getCompany_title() {
                    return company_title;
                }

                public void setCompany_title(String company_title) {
                    this.company_title = company_title;
                }

                @Override
                public String toString() {
                    return "VideoListBean{" +
                            "cid='" + cid + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", created='" + created + '\'' +
                            ", live_type='" + live_type + '\'' +
                            ", title='" + title + '\'' +
                            ", user_pic='" + user_pic + '\'' +
                            ", username='" + username + '\'' +
                            ", video_pic='" + video_pic + '\'' +
                            ", view_count='" + view_count + '\'' +
                            ", company_title='" + company_title + '\'' +
                            '}';
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getCid_url() {
                    return cid_url;
                }

                public void setCid_url(String cid_url) {
                    this.cid_url = cid_url;
                }

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getLive_type() {
                    return live_type;
                }

                public void setLive_type(String live_type) {
                    this.live_type = live_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public String getVideo_pic() {
                    return video_pic;
                }

                public void setVideo_pic(String video_pic) {
                    this.video_pic = video_pic;
                }

                public String getView_count() {
                    return view_count;
                }

                public void setView_count(String view_count) {
                    this.view_count = view_count;
                }
            }
        }
    }
}
