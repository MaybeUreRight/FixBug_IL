package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.PublishInformationContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 15:38
 */

public class PublishInformationModel implements PublishInformationContract.Model {



    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "PublishInformationModel{" +
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


        private int code;
        private RetBean ret;

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

        public RetBean getRet() {
            return ret;
        }

        public void setRet(RetBean ret) {
            this.ret = ret;
        }

        public static class RetBean {
            private List<ListBean> list;

            public List<ListBean> getList() {
                return list;
            }

            @Override
            public String toString() {
                return "RetBean{" +
                        "list=" + list +
                        '}';
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {

                private String pic;
                private String title;
                private String id;
                private String descript;
                private String content;
                private String view;
                private String created;
                private String publish_id;
                private String publish_index;
                private String status;
                private String push_index_time;
                private Object username;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "pic='" + pic + '\'' +
                            ", title='" + title + '\'' +
                            ", id='" + id + '\'' +
                            ", descript='" + descript + '\'' +
                            ", content='" + content + '\'' +
                            ", view='" + view + '\'' +
                            ", created='" + created + '\'' +
                            ", publish_id='" + publish_id + '\'' +
                            ", publish_index='" + publish_index + '\'' +
                            ", status='" + status + '\'' +
                            ", push_index_time='" + push_index_time + '\'' +
                            ", username=" + username +
                            '}';
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

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDescript() {
                    return descript;
                }

                public void setDescript(String descript) {
                    this.descript = descript;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getView() {
                    return view;
                }

                public void setView(String view) {
                    this.view = view;
                }

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public String getPublish_id() {
                    return publish_id;
                }

                public void setPublish_id(String publish_id) {
                    this.publish_id = publish_id;
                }

                public String getPublish_index() {
                    return publish_index;
                }

                public void setPublish_index(String publish_index) {
                    this.publish_index = publish_index;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPush_index_time() {
                    return push_index_time;
                }

                public void setPush_index_time(String push_index_time) {
                    this.push_index_time = push_index_time;
                }

                public Object getUsername() {
                    return username;
                }

                public void setUsername(Object username) {
                    this.username = username;
                }
            }
        }
    }
}
