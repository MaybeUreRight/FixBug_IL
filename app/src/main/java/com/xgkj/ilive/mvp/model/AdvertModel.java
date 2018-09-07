package com.xgkj.ilive.mvp.model;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/16 0016 13:35
 */

public class AdvertModel {


    /**
     * APIDATA : {"ret":{"list":[{"title":"高峰论坛","url":"","pic":"http://static.devtool6.com/upload/20170714/52331500022573.jpg","cid":"4440953","video_type":"2","room_id":"9860172"},{"title":"钱江机器人现场工作展示直播","url":"","pic":"http://static.devtool6.com/upload/20170714/58471500022004.jpg","cid":"472","video_type":"1","room_id":9781113},{"title":"新松","url":"","pic":"http://static.devtool6.com/upload/20170714/26821500022198.jpg","cid":"4899077","video_type":"2","room_id":"9923661"},{"title":"Hiwin智能设备现场讲解直播","url":"","pic":"http://static.devtool6.com/upload/20170714/82331500022308.jpg","cid":"465","video_type":"1","room_id":9755562}]},"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "AdvertModel{" +
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
         * ret : {"list":[{"title":"高峰论坛","url":"","pic":"http://static.devtool6.com/upload/20170714/52331500022573.jpg","cid":"4440953","video_type":"2","room_id":"9860172"},{"title":"钱江机器人现场工作展示直播","url":"","pic":"http://static.devtool6.com/upload/20170714/58471500022004.jpg","cid":"472","video_type":"1","room_id":9781113},{"title":"新松","url":"","pic":"http://static.devtool6.com/upload/20170714/26821500022198.jpg","cid":"4899077","video_type":"2","room_id":"9923661"},{"title":"Hiwin智能设备现场讲解直播","url":"","pic":"http://static.devtool6.com/upload/20170714/82331500022308.jpg","cid":"465","video_type":"1","room_id":9755562}]}
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
                 * title : 高峰论坛
                 * url :
                 * pic : http://static.devtool6.com/upload/20170714/52331500022573.jpg
                 * cid : 4440953
                 * video_type : 2
                 * room_id : 9860172
                 */

                private String title;
                private String url;
                private String pic;
                private String cid;
                private String video_type;
                private String room_id;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            ", pic='" + pic + '\'' +
                            ", cid='" + cid + '\'' +
                            ", video_type='" + video_type + '\'' +
                            ", room_id='" + room_id + '\'' +
                            '}';
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getVideo_type() {
                    return video_type;
                }

                public void setVideo_type(String video_type) {
                    this.video_type = video_type;
                }

                public String getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(String room_id) {
                    this.room_id = room_id;
                }
            }
        }
    }
}
