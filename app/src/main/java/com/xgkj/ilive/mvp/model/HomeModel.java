package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.HomeContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/13 0013 17:14
 */

public class HomeModel implements HomeContract.Model {


    /**
     * APIDATA : {"code":200,"live_list":[{"company_title":"I智播","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","cid":"472","pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg","live_time":"1499331474","title":"钱江机器人现场工作展示直播","view":"254"},{"company_title":"I智播","cid_url":"566229cb6062499bb32c760e62618708_1499319480416_1499324000223_16003556-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708?wsSecret=4e310262a189dfcff41657ccb763bc11&wsTime=1499319467","pull_url":"rtmp://v8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708","cid":"469","pic":"http://static.devtool6.com/upload/85f845f5be287f4c0c265cc949ac026e.jpg","live_time":"1499319467","title":"2017中国国际服务机器人发展论坛","view":"75"},{"company_title":"I智播","cid_url":"25a54dd26e554dfeb619a1a5d70f921d_1499309454179_1499313012432_15998332-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d?wsSecret=7fc7e279cc389cecd017e0cf8d996cc8&wsTime=1499309440","pull_url":"rtmp://v8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d","cid":"468","pic":"http://static.devtool6.com/upload/2ed9ed296221855d464267403802e660.jpg","live_time":"1499309439","title":"首届中国智能制造产业园区Top50高峰论坛","view":"62"},{"company_title":"I智播","cid_url":"f972e54f248d4e0b8b425155334ed76b_1499238092820_1499238822870_15967742-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b?wsSecret=70bed4f64b52f66e3b9095a6daa0cffc&wsTime=1499238074","pull_url":"rtmp://v8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b","cid":"465","pic":"http://static.devtool6.com/upload/2974ff6d6630e9f07299243663947b3e.jpg","live_time":"1499238074","title":"Hiwin智能设备现场讲解直播","view":"60"}],"news_list":[{"id":"284","title":"博世力士乐召开首届包装技术论坛、五大关键字解读包装行业新趋势","view":"689","created":"1500007639","pic":"http://static.devtool6.com/upload/20170714/75581500007639.jpg"},{"id":"282","title":"工业机器人实现柔性制造离不开的核心部件","view":"401","created":"1500005878","pic":"http://static.devtool6.com/upload/20170714/72021500005878.png"},{"id":"281","title":"什么是除锈机器人？1台船舶除锈爬壁机器人相当于7个工人","view":"401","created":"1500004248","pic":"http://static.devtool6.com/upload/20170714/35151500004248.png"},{"id":"280","title":"解读马云的新制造 基于数据而进行的制造业","view":"389","created":"1500004072","pic":"http://static.devtool6.com/upload/20170714/84101500004072.jpg"}],"video_list":[{"title":"新松研究院院长邹风山-服务机器人创新发展","room_id":"9923661","company_title":"I智播","created":"1500021709","video_pic":"http://static.devtool6.com/upload/20170714/61881500021330.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4899077","video_count":"20","cid_url":"3e324a6d-4c26-41fa-8bfd-abaadbeccf2e.mp4","live_type":"2"},{"title":"日本ZMP公司7月13日发布了一款可以在人行道上行驶的快递机器人","room_id":"9915787","company_title":"I智播","created":"1500019410","video_pic":"http://static.devtool6.com/upload/20170714/49761500019398.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4895741","video_count":"0","cid_url":"ec658497-0b06-4913-ab13-419768ad7e8e.mp4","live_type":"2"},{"title":"ROS机器人平台：全方位、自平衡和四轮","room_id":"9924655","company_title":"I智播","created":"1500019303","video_pic":"http://static.devtool6.com/upload/20170714/34111500019290.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4893686","video_count":"0","cid_url":"4bb775b9-155c-4453-b450-bf69999376ac.mp4","live_type":"2"},{"title":"8轮机器人平台","room_id":"9924647","company_title":"I智播","created":"1500019148","video_pic":"http://static.devtool6.com/upload/20170714/70161500019132.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4893625","video_count":"0","cid_url":"9f756a0c-724f-4bc4-8059-139ecf863840.mp4","live_type":"2"}]}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "HomeModel{" +
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
         * live_list : [{"company_title":"I智播","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","cid":"472","pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg","live_time":"1499331474","title":"钱江机器人现场工作展示直播","view":"254"},{"company_title":"I智播","cid_url":"566229cb6062499bb32c760e62618708_1499319480416_1499324000223_16003556-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708?wsSecret=4e310262a189dfcff41657ccb763bc11&wsTime=1499319467","pull_url":"rtmp://v8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708","cid":"469","pic":"http://static.devtool6.com/upload/85f845f5be287f4c0c265cc949ac026e.jpg","live_time":"1499319467","title":"2017中国国际服务机器人发展论坛","view":"75"},{"company_title":"I智播","cid_url":"25a54dd26e554dfeb619a1a5d70f921d_1499309454179_1499313012432_15998332-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d?wsSecret=7fc7e279cc389cecd017e0cf8d996cc8&wsTime=1499309440","pull_url":"rtmp://v8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d","cid":"468","pic":"http://static.devtool6.com/upload/2ed9ed296221855d464267403802e660.jpg","live_time":"1499309439","title":"首届中国智能制造产业园区Top50高峰论坛","view":"62"},{"company_title":"I智播","cid_url":"f972e54f248d4e0b8b425155334ed76b_1499238092820_1499238822870_15967742-00000.mp4","push_url":"rtmp://p8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b?wsSecret=70bed4f64b52f66e3b9095a6daa0cffc&wsTime=1499238074","pull_url":"rtmp://v8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b","cid":"465","pic":"http://static.devtool6.com/upload/2974ff6d6630e9f07299243663947b3e.jpg","live_time":"1499238074","title":"Hiwin智能设备现场讲解直播","view":"60"}]
         * news_list : [{"id":"284","title":"博世力士乐召开首届包装技术论坛、五大关键字解读包装行业新趋势","view":"689","created":"1500007639","pic":"http://static.devtool6.com/upload/20170714/75581500007639.jpg"},{"id":"282","title":"工业机器人实现柔性制造离不开的核心部件","view":"401","created":"1500005878","pic":"http://static.devtool6.com/upload/20170714/72021500005878.png"},{"id":"281","title":"什么是除锈机器人？1台船舶除锈爬壁机器人相当于7个工人","view":"401","created":"1500004248","pic":"http://static.devtool6.com/upload/20170714/35151500004248.png"},{"id":"280","title":"解读马云的新制造 基于数据而进行的制造业","view":"389","created":"1500004072","pic":"http://static.devtool6.com/upload/20170714/84101500004072.jpg"}]
         * video_list : [{"title":"新松研究院院长邹风山-服务机器人创新发展","room_id":"9923661","company_title":"I智播","created":"1500021709","video_pic":"http://static.devtool6.com/upload/20170714/61881500021330.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4899077","video_count":"20","cid_url":"3e324a6d-4c26-41fa-8bfd-abaadbeccf2e.mp4","live_type":"2"},{"title":"日本ZMP公司7月13日发布了一款可以在人行道上行驶的快递机器人","room_id":"9915787","company_title":"I智播","created":"1500019410","video_pic":"http://static.devtool6.com/upload/20170714/49761500019398.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4895741","video_count":"0","cid_url":"ec658497-0b06-4913-ab13-419768ad7e8e.mp4","live_type":"2"},{"title":"ROS机器人平台：全方位、自平衡和四轮","room_id":"9924655","company_title":"I智播","created":"1500019303","video_pic":"http://static.devtool6.com/upload/20170714/34111500019290.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4893686","video_count":"0","cid_url":"4bb775b9-155c-4453-b450-bf69999376ac.mp4","live_type":"2"},{"title":"8轮机器人平台","room_id":"9924647","company_title":"I智播","created":"1500019148","video_pic":"http://static.devtool6.com/upload/20170714/70161500019132.png","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","cid":"4893625","video_count":"0","cid_url":"9f756a0c-724f-4bc4-8059-139ecf863840.mp4","live_type":"2"}]
         */

        private int code;
        private List<LiveListBean> livenow_list;
        private List<LiveListBean> live_list;
        private List<NewsListBean> news_list;
        private List<VideoListBean> video_list;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", live_list=" + live_list +
                    ", news_list=" + news_list +
                    ", video_list=" + video_list +
                    '}';
        }

        public List<LiveListBean> getLivenow_list() {
            return livenow_list;
        }

        public void setLivenow_list(List<LiveListBean> livenow_list) {
            this.livenow_list = livenow_list;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
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

        public void setVideo_list(List<VideoListBean> video_list) {
            this.video_list = video_list;
        }

        public static class LiveListBean {
            /**
             * company_title : I智播
             * cid_url : 6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4
             * push_url : rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474
             * pull_url : rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070
             * cid : 472
             * pic : http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg
             * live_time : 1499331474
             * title : 钱江机器人现场工作展示直播
             * view : 254
             */

            private String company_title;
            private String cid_url;
            private String push_url;
            private String pull_url;
            private String cid;
            private String pic;
            private String live_time;
            private String title;
            private String view;

            @Override
            public String toString() {
                return "LiveListBean{" +
                        "company_title='" + company_title + '\'' +
                        ", cid_url='" + cid_url + '\'' +
                        ", push_url='" + push_url + '\'' +
                        ", pull_url='" + pull_url + '\'' +
                        ", cid='" + cid + '\'' +
                        ", pic='" + pic + '\'' +
                        ", live_time='" + live_time + '\'' +
                        ", title='" + title + '\'' +
                        ", view='" + view + '\'' +
                        '}';
            }

            public String getCompany_title() {
                return company_title;
            }

            public void setCompany_title(String company_title) {
                this.company_title = company_title;
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

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLive_time() {
                return live_time;
            }

            public void setLive_time(String live_time) {
                this.live_time = live_time;
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
             * id : 284
             * title : 博世力士乐召开首届包装技术论坛、五大关键字解读包装行业新趋势
             * view : 689
             * created : 1500007639
             * pic : http://static.devtool6.com/upload/20170714/75581500007639.jpg
             */

            private String id;
            private String title;
            private String view;
            private String created;
            private String pic;
            private String descript;

            @Override
            public String toString() {
                return "NewsListBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", view='" + view + '\'' +
                        ", created='" + created + '\'' +
                        ", pic='" + pic + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }
        }

        public static class VideoListBean {
            /**
             * title : 新松研究院院长邹风山-服务机器人创新发展
             * room_id : 9923661
             * company_title : I智播
             * created : 1500021709
             * video_pic : http://static.devtool6.com/upload/20170714/61881500021330.png
             * user_pic : http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg
             * cid : 4899077
             * video_count : 20
             * cid_url : 3e324a6d-4c26-41fa-8bfd-abaadbeccf2e.mp4
             * live_type : 2
             */

            private String title;
            private String room_id;
            private String company_title;
            private String created;
            private String video_pic;
            private String user_pic;
            private String cid;
            private String video_count;
            private String cid_url;
            private String live_type;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRoom_id() {
                return room_id;
            }

            public void setRoom_id(String room_id) {
                this.room_id = room_id;
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

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getVideo_count() {
                return video_count;
            }

            public void setVideo_count(String video_count) {
                this.video_count = video_count;
            }

            public String getCid_url() {
                return cid_url;
            }

            public void setCid_url(String cid_url) {
                this.cid_url = cid_url;
            }

            public String getLive_type() {
                return live_type;
            }

            public void setLive_type(String live_type) {
                this.live_type = live_type;
            }
        }
    }
}
