package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.PublishVideoContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 16:06
 */

public class PublishVideoModel implements PublishVideoContract.Model {


    /**
     * APIDATA : {"code":200,"ret":{"list":[{"cid":"5334046","cid_url":"14189503-a3d7-4cda-aa5e-52cf3431ce2e.mp4","company_title":"I智播","created":"1500363446","live_type":"2","room_id":"9987958","title":"京东物流全套无人设备的运营体系体验","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"6","video_pic":"http://static.devtool6.com/upload/20170718/53301500363420.png"},{"cid":"5332576","cid_url":"62f39dba-7145-4150-a6c8-8f8439485ff2.mp4","company_title":"I智播","created":"1500363264","live_type":"2","room_id":"10001103","title":"乐高\u2014\u2014坦克爬坡测试","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"0","video_pic":"http://static.devtool6.com/upload/20170718/95861500363239.png"},{"cid":"5325852","cid_url":"288e760b-7a6c-4fcb-915a-a0ffbd09d92d.mp4","company_title":"I智播","created":"1500362852","live_type":"2","room_id":"9998260","title":"人与机器人之间的对话","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"0","video_pic":"http://static.devtool6.com/upload/20170718/26541500362838.png"},{"cid":"5333405","cid_url":"683ddace-557d-415a-aa8e-f54d971d23e3.mp4","company_title":"I智播","created":"1500362714","live_type":"2","room_id":"9984977","title":"超级机器人\u2014\u201420M长的臂展","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"0","video_pic":"http://static.devtool6.com/upload/20170718/13271500362700.png"},{"cid":"5192467","cid_url":"b8c6574a-db45-43bb-a723-5552d64bc39f.mp4","company_title":"I智播","created":"1500281229","live_type":"2","room_id":"9973709","title":"奔驰全自动化生产线","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"7","video_pic":"http://static.devtool6.com/upload/20170717/10541500281204.png"},{"cid":"5193255","cid_url":"728007aa-8618-4b7d-8442-575b2b18a89a.mp4","company_title":"I智播","created":"1500280998","live_type":"2","room_id":"9983224","title":"3D打印机打印狮子现场工作流程","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"19","video_pic":"http://static.devtool6.com/upload/20170717/76681500280953.png"},{"cid":"5191846","cid_url":"4dfe3373-00bb-49f9-b1c3-78357bb08b4e.mp4","company_title":"I智播","created":"1500280547","live_type":"2","room_id":"9974706","title":"远程遥控蛇形机器人","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"23","video_pic":"http://static.devtool6.com/upload/20170717/96271500280534.png"},{"cid":"5191801","cid_url":"e49fbc05-c66e-4bfe-a446-a858c7bc6ce3.mp4","company_title":"I智播","created":"1500280432","live_type":"2","room_id":"9983213","title":"德国宇航中心研发机器人","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"7","video_pic":"http://static.devtool6.com/upload/20170717/49361500280416.png"},{"cid":"5193035","cid_url":"95dd91be-78a1-49f3-8547-01a5572048e2.mp4","company_title":"I智播","created":"1500280310","live_type":"2","room_id":"9971697","title":"OC Robotics\u2014\u2014蛇形机器人","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"10","video_pic":"http://static.devtool6.com/upload/20170717/48511500280297.png"},{"cid":"4899077","cid_url":"3e324a6d-4c26-41fa-8bfd-abaadbeccf2e.mp4","company_title":"I智播","created":"1500021709","live_type":"2","room_id":"9923661","title":"新松研究院院长邹风山-服务机器人创新发展","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"67","video_pic":"http://static.devtool6.com/upload/20170714/61881500021330.png"}]}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "PublishVideoModel{" +
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
         * ret : {"list":[{"cid":"5334046","cid_url":"14189503-a3d7-4cda-aa5e-52cf3431ce2e.mp4","company_title":"I智播","created":"1500363446","live_type":"2","room_id":"9987958","title":"京东物流全套无人设备的运营体系体验","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"6","video_pic":"http://static.devtool6.com/upload/20170718/53301500363420.png"},{"cid":"5332576","cid_url":"62f39dba-7145-4150-a6c8-8f8439485ff2.mp4","company_title":"I智播","created":"1500363264","live_type":"2","room_id":"10001103","title":"乐高\u2014\u2014坦克爬坡测试","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"0","video_pic":"http://static.devtool6.com/upload/20170718/95861500363239.png"},{"cid":"5325852","cid_url":"288e760b-7a6c-4fcb-915a-a0ffbd09d92d.mp4","company_title":"I智播","created":"1500362852","live_type":"2","room_id":"9998260","title":"人与机器人之间的对话","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"0","video_pic":"http://static.devtool6.com/upload/20170718/26541500362838.png"},{"cid":"5333405","cid_url":"683ddace-557d-415a-aa8e-f54d971d23e3.mp4","company_title":"I智播","created":"1500362714","live_type":"2","room_id":"9984977","title":"超级机器人\u2014\u201420M长的臂展","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"0","video_pic":"http://static.devtool6.com/upload/20170718/13271500362700.png"},{"cid":"5192467","cid_url":"b8c6574a-db45-43bb-a723-5552d64bc39f.mp4","company_title":"I智播","created":"1500281229","live_type":"2","room_id":"9973709","title":"奔驰全自动化生产线","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"7","video_pic":"http://static.devtool6.com/upload/20170717/10541500281204.png"},{"cid":"5193255","cid_url":"728007aa-8618-4b7d-8442-575b2b18a89a.mp4","company_title":"I智播","created":"1500280998","live_type":"2","room_id":"9983224","title":"3D打印机打印狮子现场工作流程","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"19","video_pic":"http://static.devtool6.com/upload/20170717/76681500280953.png"},{"cid":"5191846","cid_url":"4dfe3373-00bb-49f9-b1c3-78357bb08b4e.mp4","company_title":"I智播","created":"1500280547","live_type":"2","room_id":"9974706","title":"远程遥控蛇形机器人","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"23","video_pic":"http://static.devtool6.com/upload/20170717/96271500280534.png"},{"cid":"5191801","cid_url":"e49fbc05-c66e-4bfe-a446-a858c7bc6ce3.mp4","company_title":"I智播","created":"1500280432","live_type":"2","room_id":"9983213","title":"德国宇航中心研发机器人","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"7","video_pic":"http://static.devtool6.com/upload/20170717/49361500280416.png"},{"cid":"5193035","cid_url":"95dd91be-78a1-49f3-8547-01a5572048e2.mp4","company_title":"I智播","created":"1500280310","live_type":"2","room_id":"9971697","title":"OC Robotics\u2014\u2014蛇形机器人","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"10","video_pic":"http://static.devtool6.com/upload/20170717/48511500280297.png"},{"cid":"4899077","cid_url":"3e324a6d-4c26-41fa-8bfd-abaadbeccf2e.mp4","company_title":"I智播","created":"1500021709","live_type":"2","room_id":"9923661","title":"新松研究院院长邹风山-服务机器人创新发展","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","video_count":"67","video_pic":"http://static.devtool6.com/upload/20170714/61881500021330.png"}]}
         */

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
                 * cid : 5334046
                 * cid_url : 14189503-a3d7-4cda-aa5e-52cf3431ce2e.mp4
                 * company_title : I智播
                 * created : 1500363446
                 * live_type : 2
                 * room_id : 9987958
                 * title : 京东物流全套无人设备的运营体系体验
                 * user_pic : http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg
                 * video_count : 6
                 * video_pic : http://static.devtool6.com/upload/20170718/53301500363420.png
                 */

                private String cid;
                private String cid_url;
                private String company_title;
                private String created;
                private String live_type;
                private String room_id;
                private String title;
                private String user_pic;
                private String video_count;
                private String video_pic;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "cid='" + cid + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", company_title='" + company_title + '\'' +
                            ", created='" + created + '\'' +
                            ", live_type='" + live_type + '\'' +
                            ", room_id='" + room_id + '\'' +
                            ", title='" + title + '\'' +
                            ", user_pic='" + user_pic + '\'' +
                            ", video_count='" + video_count + '\'' +
                            ", video_pic='" + video_pic + '\'' +
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

                public String getLive_type() {
                    return live_type;
                }

                public void setLive_type(String live_type) {
                    this.live_type = live_type;
                }

                public String getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(String room_id) {
                    this.room_id = room_id;
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

                public String getVideo_count() {
                    return video_count;
                }

                public void setVideo_count(String video_count) {
                    this.video_count = video_count;
                }

                public String getVideo_pic() {
                    return video_pic;
                }

                public void setVideo_pic(String video_pic) {
                    this.video_pic = video_pic;
                }
            }
        }
    }
}
