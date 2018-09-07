package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.BestNewContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/18 0018 13:45
 */

public class BestNewModel implements BestNewContract.Model {

    /**
     * APIDATA : {"code":200,"ret":{"list":[{"cid":"559","cid_url":"23cb231fb4914b7e9f24cff0d7e67fd2_1502348058073_1502348064130_20830585-00000.mp4","company_title":"海尔","created":"1502348009","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/23cb231fb4914b7e9f24cff0d7e67fd2","push_url":"rtmp://p8f1debd5.live.126.net/live/23cb231fb4914b7e9f24cff0d7e67fd2?wsSecret=e4be711785e1279cb87da292f4302270&wsTime=1502348041","status":"1","title":"葫芦我问一下","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/a4cb3cd26a56e966657fe3072ce91121.jpg"},{"cid":"558","cid_url":"","company_title":"海尔","created":"1502347901","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/3d1df0b325d54c0d9576e97a333a06a3","push_url":"rtmp://p8f1debd5.live.126.net/live/3d1df0b325d54c0d9576e97a333a06a3?wsSecret=cb89d9e8269b1b0b3be556099426ab36&wsTime=1502347934","status":"1","title":"去YY我想问一下","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/1d4dff135362f6f802af6a4ab71cb38f.jpg"},{"cid":"557","cid_url":"","company_title":"企业名称12","created":"1502275597","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/93cbb102501c48cd89d99e0fb242e204","push_url":"rtmp://p8f1debd5.live.126.net/live/93cbb102501c48cd89d99e0fb242e204?wsSecret=682e42e20c1acf95d568c4d059cf05c6&wsTime=1502275628","status":"1","title":"测试","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","username":"杨东","video_count":"0","video_pic":"http://static.devtool6.com/upload/682ca2909df882af738eedc59ffa9951.jpg"},{"cid":"556","cid_url":"474fff26fe1d46b4a031ba0a0e665aa0_1502183045495_1502183057751_20810984-00000.mp4","company_title":"海尔","created":"1502183012","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/474fff26fe1d46b4a031ba0a0e665aa0","push_url":"rtmp://p8f1debd5.live.126.net/live/474fff26fe1d46b4a031ba0a0e665aa0?wsSecret=5734634a2ac10a1f9355c83ceb19406c&wsTime=1502183042","status":"1","title":"回家了路线","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/e07fb560949ed5512b859e967e6c4f57.jpg"},{"cid":"555","cid_url":"ee625e89d49945ea8a7986155ccdf910_1502182478054_1502182484701_20810866-00000.mp4","company_title":"海尔","created":"1502182445","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/ee625e89d49945ea8a7986155ccdf910","push_url":"rtmp://p8f1debd5.live.126.net/live/ee625e89d49945ea8a7986155ccdf910?wsSecret=a9c8013b2fa72696171718d66f94df9a&wsTime=1502182474","status":"1","title":"入职","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/6637993eaeb9d2036c7d09c73a0f4b68.jpg"},{"cid":"554","cid_url":"47d721ef7baa4653b063d244be6ad6be_1502182313623_1502182323234_20810816-00000.mp4","company_title":"海尔","created":"1502182281","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/47d721ef7baa4653b063d244be6ad6be","push_url":"rtmp://p8f1debd5.live.126.net/live/47d721ef7baa4653b063d244be6ad6be?wsSecret=58a84a7301c778de07a1de21a95b78af&wsTime=1502182310","status":"1","title":"如图","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/5ff1ecf96144c87d266f33399684541f.jpg"},{"cid":"553","cid_url":"68753cbd833148f497ab4dbac1e456e8_1502182199782_1502182208027_20810774-00000.mp4","company_title":"海尔","created":"1502182167","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/68753cbd833148f497ab4dbac1e456e8","push_url":"rtmp://p8f1debd5.live.126.net/live/68753cbd833148f497ab4dbac1e456e8?wsSecret=2a90d091335f5a4cfb841cd16fe98c00&wsTime=1502182196","status":"1","title":"互相学习","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/e00d73eda46cd69799a8858587fae64a.jpg"},{"cid":"552","cid_url":"3fe045e2cbbd41e0adc6b777554f707c_1502179869833_1502179916196_20810191-00000.mp4","company_title":"海尔","created":"1502179834","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/3fe045e2cbbd41e0adc6b777554f707c","push_url":"rtmp://p8f1debd5.live.126.net/live/3fe045e2cbbd41e0adc6b777554f707c?wsSecret=6fafab0a8131e0e02d4574cdf2f024af&wsTime=1502179863","status":"1","title":"使用","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/d2b084362f486ac6d2c2fa89d89cf169.jpg"},{"cid":"551","cid_url":"2a0e7cdf6ac1443ea576b34c77fc2963_1502179398647_1502179653743_20810058-00000.mp4","company_title":"海尔","created":"1502179366","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/2a0e7cdf6ac1443ea576b34c77fc2963","push_url":"rtmp://p8f1debd5.live.126.net/live/2a0e7cdf6ac1443ea576b34c77fc2963?wsSecret=e2547f4695ea747678e8cebc288f3b86&wsTime=1502179395","status":"1","title":"监控","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/a78af8cad83dd947c402337075633a8b.jpg"},{"cid":"550","cid_url":"cb769550f1bd497c8aa5c664bdaf6ef4_1502179290737_1502179354059_20810029-00000.mp4","company_title":"海尔","created":"1502179258","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/cb769550f1bd497c8aa5c664bdaf6ef4","push_url":"rtmp://p8f1debd5.live.126.net/live/cb769550f1bd497c8aa5c664bdaf6ef4?wsSecret=ad03d3ce540cb5b820570987cc57e229&wsTime=1502179287","status":"1","title":"曲突徙薪","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/0003ee0fc9fdcf6a29f4de09b6055d48.jpg"}]}}
     */

    private APIDATABean APIDATA;


    public APIDATABean getAPIDATA() {
        return APIDATA;
    }

    @Override
    public String toString() {
        return "BestNewModel{" +
                "APIDATA=" + APIDATA +
                '}';
    }

    public void setAPIDATA(APIDATABean APIDATA) {
        this.APIDATA = APIDATA;
    }

    public static class APIDATABean {
        /**
         * code : 200
         * ret : {"list":[{"cid":"559","cid_url":"23cb231fb4914b7e9f24cff0d7e67fd2_1502348058073_1502348064130_20830585-00000.mp4","company_title":"海尔","created":"1502348009","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/23cb231fb4914b7e9f24cff0d7e67fd2","push_url":"rtmp://p8f1debd5.live.126.net/live/23cb231fb4914b7e9f24cff0d7e67fd2?wsSecret=e4be711785e1279cb87da292f4302270&wsTime=1502348041","status":"1","title":"葫芦我问一下","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/a4cb3cd26a56e966657fe3072ce91121.jpg"},{"cid":"558","cid_url":"","company_title":"海尔","created":"1502347901","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/3d1df0b325d54c0d9576e97a333a06a3","push_url":"rtmp://p8f1debd5.live.126.net/live/3d1df0b325d54c0d9576e97a333a06a3?wsSecret=cb89d9e8269b1b0b3be556099426ab36&wsTime=1502347934","status":"1","title":"去YY我想问一下","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/d773ece0f152c041c0a78329be08b699.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/1d4dff135362f6f802af6a4ab71cb38f.jpg"},{"cid":"557","cid_url":"","company_title":"企业名称12","created":"1502275597","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/93cbb102501c48cd89d99e0fb242e204","push_url":"rtmp://p8f1debd5.live.126.net/live/93cbb102501c48cd89d99e0fb242e204?wsSecret=682e42e20c1acf95d568c4d059cf05c6&wsTime=1502275628","status":"1","title":"测试","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","username":"杨东","video_count":"0","video_pic":"http://static.devtool6.com/upload/682ca2909df882af738eedc59ffa9951.jpg"},{"cid":"556","cid_url":"474fff26fe1d46b4a031ba0a0e665aa0_1502183045495_1502183057751_20810984-00000.mp4","company_title":"海尔","created":"1502183012","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/474fff26fe1d46b4a031ba0a0e665aa0","push_url":"rtmp://p8f1debd5.live.126.net/live/474fff26fe1d46b4a031ba0a0e665aa0?wsSecret=5734634a2ac10a1f9355c83ceb19406c&wsTime=1502183042","status":"1","title":"回家了路线","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/e07fb560949ed5512b859e967e6c4f57.jpg"},{"cid":"555","cid_url":"ee625e89d49945ea8a7986155ccdf910_1502182478054_1502182484701_20810866-00000.mp4","company_title":"海尔","created":"1502182445","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/ee625e89d49945ea8a7986155ccdf910","push_url":"rtmp://p8f1debd5.live.126.net/live/ee625e89d49945ea8a7986155ccdf910?wsSecret=a9c8013b2fa72696171718d66f94df9a&wsTime=1502182474","status":"1","title":"入职","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/6637993eaeb9d2036c7d09c73a0f4b68.jpg"},{"cid":"554","cid_url":"47d721ef7baa4653b063d244be6ad6be_1502182313623_1502182323234_20810816-00000.mp4","company_title":"海尔","created":"1502182281","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/47d721ef7baa4653b063d244be6ad6be","push_url":"rtmp://p8f1debd5.live.126.net/live/47d721ef7baa4653b063d244be6ad6be?wsSecret=58a84a7301c778de07a1de21a95b78af&wsTime=1502182310","status":"1","title":"如图","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/5ff1ecf96144c87d266f33399684541f.jpg"},{"cid":"553","cid_url":"68753cbd833148f497ab4dbac1e456e8_1502182199782_1502182208027_20810774-00000.mp4","company_title":"海尔","created":"1502182167","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/68753cbd833148f497ab4dbac1e456e8","push_url":"rtmp://p8f1debd5.live.126.net/live/68753cbd833148f497ab4dbac1e456e8?wsSecret=2a90d091335f5a4cfb841cd16fe98c00&wsTime=1502182196","status":"1","title":"互相学习","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/e00d73eda46cd69799a8858587fae64a.jpg"},{"cid":"552","cid_url":"3fe045e2cbbd41e0adc6b777554f707c_1502179869833_1502179916196_20810191-00000.mp4","company_title":"海尔","created":"1502179834","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/3fe045e2cbbd41e0adc6b777554f707c","push_url":"rtmp://p8f1debd5.live.126.net/live/3fe045e2cbbd41e0adc6b777554f707c?wsSecret=6fafab0a8131e0e02d4574cdf2f024af&wsTime=1502179863","status":"1","title":"使用","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/d2b084362f486ac6d2c2fa89d89cf169.jpg"},{"cid":"551","cid_url":"2a0e7cdf6ac1443ea576b34c77fc2963_1502179398647_1502179653743_20810058-00000.mp4","company_title":"海尔","created":"1502179366","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/2a0e7cdf6ac1443ea576b34c77fc2963","push_url":"rtmp://p8f1debd5.live.126.net/live/2a0e7cdf6ac1443ea576b34c77fc2963?wsSecret=e2547f4695ea747678e8cebc288f3b86&wsTime=1502179395","status":"1","title":"监控","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/a78af8cad83dd947c402337075633a8b.jpg"},{"cid":"550","cid_url":"cb769550f1bd497c8aa5c664bdaf6ef4_1502179290737_1502179354059_20810029-00000.mp4","company_title":"海尔","created":"1502179258","introduce":"","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/cb769550f1bd497c8aa5c664bdaf6ef4","push_url":"rtmp://p8f1debd5.live.126.net/live/cb769550f1bd497c8aa5c664bdaf6ef4?wsSecret=ad03d3ce540cb5b820570987cc57e229&wsTime=1502179287","status":"1","title":"曲突徙薪","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg","username":"小刘","video_count":"0","video_pic":"http://static.devtool6.com/upload/0003ee0fc9fdcf6a29f4de09b6055d48.jpg"}]}
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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * cid : 559
                 * cid_url : 23cb231fb4914b7e9f24cff0d7e67fd2_1502348058073_1502348064130_20830585-00000.mp4
                 * company_title : 海尔
                 * created : 1502348009
                 * introduce :
                 * live_type : 1
                 * pull_url : rtmp://v8f1debd5.live.126.net/live/23cb231fb4914b7e9f24cff0d7e67fd2
                 * push_url : rtmp://p8f1debd5.live.126.net/live/23cb231fb4914b7e9f24cff0d7e67fd2?wsSecret=e4be711785e1279cb87da292f4302270&wsTime=1502348041
                 * status : 1
                 * title : 葫芦我问一下
                 * type : 1
                 * type_value :
                 * user_pic : http://static.devtool6.com/upload/6245ecc933ea99eeed0bfb2434542740.jpg
                 * username : 小刘
                 * video_count : 0
                 * video_pic : http://static.devtool6.com/upload/a4cb3cd26a56e966657fe3072ce91121.jpg
                 */

                private String cid;
                private String cid_url;
                private String company_title;
                private String created;
                private String introduce;
                private String live_type;
                private String pull_url;
                private String push_url;
                private String status;
                private String title;
                private String type;
                private String type_value;
                private String user_pic;
                private String username;
                private String video_count;
                private String video_pic;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "cid='" + cid + '\'' +
                            ", cid_url='" + cid_url + '\'' +
                            ", company_title='" + company_title + '\'' +
                            ", created='" + created + '\'' +
                            ", introduce='" + introduce + '\'' +
                            ", live_type='" + live_type + '\'' +
                            ", pull_url='" + pull_url + '\'' +
                            ", push_url='" + push_url + '\'' +
                            ", status='" + status + '\'' +
                            ", title='" + title + '\'' +
                            ", type='" + type + '\'' +
                            ", type_value='" + type_value + '\'' +
                            ", user_pic='" + user_pic + '\'' +
                            ", username='" + username + '\'' +
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

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getLive_type() {
                    return live_type;
                }

                public void setLive_type(String live_type) {
                    this.live_type = live_type;
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
