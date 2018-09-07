package com.xgkj.ilive.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/11/2.
 */

public class TaHistoryListBean implements Serializable {

    private RetBean ret;
    private int code;

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
    }

    public static class ListBean {

        private String cid;
        private String cid_url;
        private String created;
        private int id;
        private int live_type;
        private String title;
        private String user_pic;
        private String username;
        private String video_pic;
        private String view_count;
        private String view_time;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLive_type() {
            return live_type;
        }

        public void setLive_type(int live_type) {
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

        public String getView_time() {
            return view_time;
        }

        public void setView_time(String view_time) {
            this.view_time = view_time;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "cid='" + cid + '\'' +
                    ", cid_url='" + cid_url + '\'' +
                    ", created='" + created + '\'' +
                    ", id=" + id +
                    ", live_type=" + live_type +
                    ", title='" + title + '\'' +
                    ", user_pic='" + user_pic + '\'' +
                    ", username='" + username + '\'' +
                    ", video_pic='" + video_pic + '\'' +
                    ", view_count='" + view_count + '\'' +
                    ", view_time='" + view_time + '\'' +
                    '}';
        }
    }
}
