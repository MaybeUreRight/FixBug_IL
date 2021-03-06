package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/10 0010 15:32
 */

public class LiveDetailsModel {

    /**
     * APIDATA : {"ret":{"code":200,"list":{"cid":"6a1cc7af64c04c50a52d1f3954956070","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","company_title":"I智播","created":"1499331475","hpull_url":"http://pullhls8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070/playlist.m3u8","id":"472","introduce":"","is_follow":0,"like":"20","live_online_count":"1","live_time":"1499331474","live_type":"1","logo":"http://static.devtool6.com/upload/20170630/94781498811651.png","pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg","publish_id":"27","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","push_index":"1","push_index_time":"1506873600","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","room_id":"10318635","send":"5","status":"2","title":"钱江机器人现场工作展示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","view":"900","yglike":"0","ygsend":"0"}}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "LiveDetailsModel{" +
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
         * ret : {"code":200,"list":{"cid":"6a1cc7af64c04c50a52d1f3954956070","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","company_title":"I智播","created":"1499331475","hpull_url":"http://pullhls8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070/playlist.m3u8","id":"472","introduce":"","is_follow":0,"like":"20","live_online_count":"1","live_time":"1499331474","live_type":"1","logo":"http://static.devtool6.com/upload/20170630/94781498811651.png","pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg","publish_id":"27","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","push_index":"1","push_index_time":"1506873600","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","room_id":"10318635","send":"5","status":"2","title":"钱江机器人现场工作展示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","view":"900","yglike":"0","ygsend":"0"}}
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
             * list : {"cid":"6a1cc7af64c04c50a52d1f3954956070","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","company_title":"I智播","created":"1499331475","hpull_url":"http://pullhls8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070/playlist.m3u8","id":"472","introduce":"","is_follow":0,"like":"20","live_online_count":"1","live_time":"1499331474","live_type":"1","logo":"http://static.devtool6.com/upload/20170630/94781498811651.png","pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg","publish_id":"27","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","push_index":"1","push_index_time":"1506873600","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","room_id":"10318635","send":"5","status":"2","title":"钱江机器人现场工作展示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","view":"900","yglike":"0","ygsend":"0"}
             */

            private int code;
            private ListBean list;

            @Override
            public String toString() {
                return "RetBean{" +
                        "code=" + code +
                        ", list=" + list +
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
                 * cid : 6a1cc7af64c04c50a52d1f3954956070
                 * cid_url : 6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4
                 * company_title : I智播
                 * created : 1499331475
                 * hpull_url : http://pullhls8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070/playlist.m3u8
                 * id : 472
                 * introduce :
                 * is_follow : 0
                 * like : 20
                 * live_online_count : 1
                 * live_time : 1499331474
                 * live_type : 1
                 * logo : http://static.devtool6.com/upload/20170630/94781498811651.png
                 * pic : http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg
                 * publish_id : 27
                 * pull_url : rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070
                 * push_index : 1
                 * push_index_time : 1506873600
                 * push_url : rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474
                 * room_id : 10318635
                 * send : 5
                 * status : 2
                 * title : 钱江机器人现场工作展示直播
                 * type : 1
                 * type_value :
                 * user_pic : http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg
                 * username : 落寞的小丑
                 * view : 900
                 * yglike : 0
                 * ygsend : 0
                 */

                private String cid;
                private String cid_url;
                private String company_title;
                private String created;
                private String hpull_url;
                private String id;
                private String introduce;
                private int is_follow;
                private String like;
                private String live_online_count;
                private String live_time;
                private String live_type;
                private String logo;
                private String pic;
                private String publish_id;
                private String pull_url;
                private String push_index;
                private String push_index_time;
                private String push_url;
                private String room_id;
                private String send;
                private String status;
                private String title;
                private String type;
                private String type_value;
                private String user_pic;
                private String username;
                private String view;
                private String yglike;
                private String ygsend;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "cid='" + cid + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", company_title='" + company_title + '\'' +
                            ", created='" + created + '\'' +
                            ", hpull_url='" + hpull_url + '\'' +
                            ", id='" + id + '\'' +
                            ", introduce='" + introduce + '\'' +
                            ", is_follow=" + is_follow +
                            ", like='" + like + '\'' +
                            ", live_online_count='" + live_online_count + '\'' +
                            ", live_time='" + live_time + '\'' +
                            ", live_type='" + live_type + '\'' +
                            ", logo='" + logo + '\'' +
                            ", pic='" + pic + '\'' +
                            ", publish_id='" + publish_id + '\'' +
                            ", pull_url='" + pull_url + '\'' +
                            ", push_index='" + push_index + '\'' +
                            ", push_index_time='" + push_index_time + '\'' +
                            ", push_url='" + push_url + '\'' +
                            ", room_id='" + room_id + '\'' +
                            ", send='" + send + '\'' +
                            ", status='" + status + '\'' +
                            ", title='" + title + '\'' +
                            ", type='" + type + '\'' +
                            ", type_value='" + type_value + '\'' +
                            ", user_pic='" + user_pic + '\'' +
                            ", username='" + username + '\'' +
                            ", view='" + view + '\'' +
                            ", yglike='" + yglike + '\'' +
                            ", ygsend='" + ygsend + '\'' +
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

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getHpull_url() {
                    return hpull_url;
                }

                public void setHpull_url(String hpull_url) {
                    this.hpull_url = hpull_url;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public int getIs_follow() {
                    return is_follow;
                }

                public void setIs_follow(int is_follow) {
                    this.is_follow = is_follow;
                }

                public String getLike() {
                    return like;
                }

                public void setLike(String like) {
                    this.like = like;
                }

                public String getLive_online_count() {
                    return live_online_count;
                }

                public void setLive_online_count(String live_online_count) {
                    this.live_online_count = live_online_count;
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

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getPublish_id() {
                    return publish_id;
                }

                public void setPublish_id(String publish_id) {
                    this.publish_id = publish_id;
                }

                public String getPull_url() {
                    return pull_url;
                }

                public void setPull_url(String pull_url) {
                    this.pull_url = pull_url;
                }

                public String getPush_index() {
                    return push_index;
                }

                public void setPush_index(String push_index) {
                    this.push_index = push_index;
                }

                public String getPush_index_time() {
                    return push_index_time;
                }

                public void setPush_index_time(String push_index_time) {
                    this.push_index_time = push_index_time;
                }

                public String getPush_url() {
                    return push_url;
                }

                public void setPush_url(String push_url) {
                    this.push_url = push_url;
                }

                public String getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(String room_id) {
                    this.room_id = room_id;
                }

                public String getSend() {
                    return send;
                }

                public void setSend(String send) {
                    this.send = send;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public String getView() {
                    return view;
                }

                public void setView(String view) {
                    this.view = view;
                }

                public String getYglike() {
                    return yglike;
                }

                public void setYglike(String yglike) {
                    this.yglike = yglike;
                }

                public String getYgsend() {
                    return ygsend;
                }

                public void setYgsend(String ygsend) {
                    this.ygsend = ygsend;
                }
            }
        }
    }
}
