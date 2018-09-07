package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.PublishVideoDetailsContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/20 0020 11:17
 */

public class PublishVideoDetailsModel implements PublishVideoDetailsContract.Model {


    @Override
    public String toString() {
        return "PublishVideoDetailsModel{" +
                "APIDATA=" + APIDATA +
                '}';
    }

    /**
     * APIDATA : {"ret":{"code":200,"list":{"id":"211","company_title":"I智播","title":"WFL M60 CNC数控机床","created":"1500454815","like":"0","send":"0","video_pic":"http://static.devtool6.com/upload/20170719/75231500454757.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","cid":"5496629","view":"31","cid_url":"c7880a16-77d1-40ca-8562-c7144d356d20.mp4","publish_id":"27","logo":"http://static.devtool6.com/upload/20170630/94781498811651.png","is_follow":0},"msg":null}}
     */


    private APIDATABean APIDATA;

    public APIDATABean getAPIDATA() {
        return APIDATA;
    }

    public void setAPIDATA(APIDATABean APIDATA) {
        this.APIDATA = APIDATA;
    }

    public static class APIDATABean {
        /**
         * ret : {"code":200,"list":{"id":"211","company_title":"I智播","title":"WFL M60 CNC数控机床","created":"1500454815","like":"0","send":"0","video_pic":"http://static.devtool6.com/upload/20170719/75231500454757.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","cid":"5496629","view":"31","cid_url":"c7880a16-77d1-40ca-8562-c7144d356d20.mp4","publish_id":"27","logo":"http://static.devtool6.com/upload/20170630/94781498811651.png","is_follow":0},"msg":null}
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
             * list : {"id":"211","company_title":"I智播","title":"WFL M60 CNC数控机床","created":"1500454815","like":"0","send":"0","video_pic":"http://static.devtool6.com/upload/20170719/75231500454757.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","cid":"5496629","view":"31","cid_url":"c7880a16-77d1-40ca-8562-c7144d356d20.mp4","publish_id":"27","logo":"http://static.devtool6.com/upload/20170630/94781498811651.png","is_follow":0}
             * msg : null
             */

            private int code;
            private ListBean list;
            private Object msg;

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

            public Object getMsg() {
                return msg;
            }

            public void setMsg(Object msg) {
                this.msg = msg;
            }

            public static class ListBean {
                /**
                 * id : 211
                 * company_title : I智播
                 * title : WFL M60 CNC数控机床
                 * created : 1500454815
                 * like : 0
                 * send : 0
                 * video_pic : http://static.devtool6.com/upload/20170719/75231500454757.png
                 * user_pic : http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg
                 * username : 落寞的小丑
                 * cid : 5496629
                 * view : 31
                 * cid_url : c7880a16-77d1-40ca-8562-c7144d356d20.mp4
                 * publish_id : 27
                 * logo : http://static.devtool6.com/upload/20170630/94781498811651.png
                 * is_follow : 0
                 */

                private String id;
                private String company_title;
                private String title;
                private String created;
                private String like;
                private String send;
                private String video_pic;
                private String user_pic;
                private String username;
                private String cid;
                private String view;
                private String cid_url;
                private String publish_id;
                private String logo;
                private int is_follow;
                private String view_count;

                public String getView_count() {
                    return view_count;
                }

                public void setView_count(String view_count) {
                    this.view_count = view_count;
                }

                @Override
                public String toString() {
                    return "ListBean{" +
                            "id='" + id + '\'' +
                            ", company_title='" + company_title + '\'' +
                            ", title='" + title + '\'' +
                            ", created='" + created + '\'' +
                            ", like='" + like + '\'' +
                            ", send='" + send + '\'' +
                            ", video_pic='" + video_pic + '\'' +
                            ", user_pic='" + user_pic + '\'' +
                            ", username='" + username + '\'' +
                            ", cid='" + cid + '\'' +
                            ", view='" + view + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", publish_id='" + publish_id + '\'' +
                            ", logo='" + logo + '\'' +
                            ", is_follow=" + is_follow +
                            '}';
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCompany_title() {
                    return company_title;
                }

                public void setCompany_title(String company_title) {
                    this.company_title = company_title;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getLike() {
                    return like;
                }

                public void setLike(String like) {
                    this.like = like;
                }

                public String getSend() {
                    return send;
                }

                public void setSend(String send) {
                    this.send = send;
                }

                public String getVideo_pic() {
                    return video_pic;
                }

                public void setVideo_pic(String video_pic) {
                    this.video_pic = video_pic;
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

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getView() {
                    return view;
                }

                public void setView(String view) {
                    this.view = view;
                }

                public String getCid_url() {
                    return cid_url;
                }

                public void setCid_url(String cid_url) {
                    this.cid_url = cid_url;
                }

                public String getPublish_id() {
                    return publish_id;
                }

                public void setPublish_id(String publish_id) {
                    this.publish_id = publish_id;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public int getIs_follow() {
                    return is_follow;
                }

                public void setIs_follow(int is_follow) {
                    this.is_follow = is_follow;
                }
            }
        }
    }
}
