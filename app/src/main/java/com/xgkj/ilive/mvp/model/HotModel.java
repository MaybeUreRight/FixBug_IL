package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.HotContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/17 0017 19:09
 */

public class HotModel implements HotContract.Model {


    /**
     * APIDATA : {"code":200,"ret":{"list":[{"cid":"472","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","company_title":"I智播","created":"1499331474","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","status":"2","title":"钱江机器人现场工作展示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"287","video_pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg"},{"cid":"465","cid_url":"f972e54f248d4e0b8b425155334ed76b_1499238092820_1499238822870_15967742-00000.mp4","company_title":"I智播","created":"1499238074","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b","push_url":"rtmp://p8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b?wsSecret=70bed4f64b52f66e3b9095a6daa0cffc&wsTime=1499238074","status":"2","title":"Hiwin智能设备现场讲解直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"76","video_pic":"http://static.devtool6.com/upload/2974ff6d6630e9f07299243663947b3e.jpg"},{"cid":"469","cid_url":"566229cb6062499bb32c760e62618708_1499319480416_1499324000223_16003556-00000.mp4","company_title":"I智播","created":"1499319467","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708","push_url":"rtmp://p8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708?wsSecret=4e310262a189dfcff41657ccb763bc11&wsTime=1499319467","status":"2","title":"2017中国国际服务机器人发展论坛","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"75","video_pic":"http://static.devtool6.com/upload/85f845f5be287f4c0c265cc949ac026e.jpg"},{"cid":"468","cid_url":"25a54dd26e554dfeb619a1a5d70f921d_1499309454179_1499313012432_15998332-00000.mp4","company_title":"I智播","created":"1499309439","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d","push_url":"rtmp://p8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d?wsSecret=7fc7e279cc389cecd017e0cf8d996cc8&wsTime=1499309440","status":"2","title":"首届中国智能制造产业园区Top50高峰论坛","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"62","video_pic":"http://static.devtool6.com/upload/2ed9ed296221855d464267403802e660.jpg"},{"cid":"432","cid_url":"170de7aaa5c345cfaa0651c05f2222d7_1497411494383_1497412854790_12672128-00000.mp4","company_title":"企业名称12","created":"1497411485","introduce":"","live_online_count":"10","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/170de7aaa5c345cfaa0651c05f2222d7","push_url":"rtmp://p8f1debd5.live.126.net/live/170de7aaa5c345cfaa0651c05f2222d7?wsSecret=cf7cdd069f8017f62a4a90f8e98a3afa&wsTime=1497411485","status":"2","title":"惠普3d打印","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","username":"杨东","video_count":"243","video_pic":"http://static.devtool6.com/upload/3e7ab4a4039d09c4a0cf2d47d0a1224b.jpg"},{"cid":"431","cid_url":"003e74450e2f485a8bd339213df99201_1497409276391_1497409589915_12662459-00000.mp4","company_title":"I智播","created":"1497409252","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/003e74450e2f485a8bd339213df99201","push_url":"rtmp://p8f1debd5.live.126.net/live/003e74450e2f485a8bd339213df99201?wsSecret=d64422a45748ab78e42c5f2d9a83d5a2&wsTime=1497409252","status":"2","title":"顺兴开浩合模机现场演示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"124","video_pic":"http://static.devtool6.com/upload/9cce8b9884c794f63cf36e858fa883fe.jpg"},{"cid":"425","cid_url":"8e4961616c01478da8cf83834394b718_1497332512495_1497333070090_12340596-00000.mp4","company_title":"I智播","created":"1497332409","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/8e4961616c01478da8cf83834394b718","push_url":"rtmp://p8f1debd5.live.126.net/live/8e4961616c01478da8cf83834394b718?wsSecret=9f6602c1cb367a3114ff06d519698231&wsTime=1497332409","status":"2","title":"北京精雕～一个鸡蛋的艺术之旅","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"62","video_pic":"http://static.devtool6.com/upload/0f1bb313bd0b95cb4efef451b8477d03.jpg"},{"cid":"426","cid_url":"b384c52d25ce45dc8e07d1b8ca32d0c6_1497333225014_1497333581482_12343606-00000.mp4","company_title":"I智播","created":"1497333213","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/b384c52d25ce45dc8e07d1b8ca32d0c6","push_url":"rtmp://p8f1debd5.live.126.net/live/b384c52d25ce45dc8e07d1b8ca32d0c6?wsSecret=aa1577035d719996b878f557ef757c14&wsTime=1497333213","status":"2","title":"gf5轴联动全新加工模式","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"33","video_pic":"http://static.devtool6.com/upload/d1dba1bdabb64f6eedbed988e269202b.jpg"},{"cid":"308","cid_url":"18574f027ef54045a2cf177d9387c2f0_1496373422403_1496375098266_8654119-00000.mp4","company_title":"i智播","created":"1496373415","introduce":"","live_online_count":"0","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/18574f027ef54045a2cf177d9387c2f0","push_url":"rtmp://p8f1debd5.live.126.net/live/18574f027ef54045a2cf177d9387c2f0?wsSecret=2ba92d008c66a5265d2b38f4b96d93ad&wsTime=1496373415","status":"2","title":"GF加工方案中国市场部及销售支持经理孟轩先生","type":"1","type_value":"(null)","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"184","video_pic":"http://static.devtool6.com/upload/ba647bb48277adaade3f4a95fad1d196.jpg"}],"video_list":[{"cid":"917984","cid_url":"5de9a719-baa0-4ea8-9bb9-d73adad7a95c.mp4","company_title":"","created":"1491910295","live_type":"2","room_id":"9129169","title":"【专访】秦川机床工具集团董事长龙兴元花","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","video_count":"2","video_pic":"http://static.devtool6.com/upload/20170411/36681491910257.png"},{"cid":"920065","cid_url":"d67af29c-32f0-4325-b4f8-eec88b40f59b.mp4","company_title":"","created":"1491910097","live_type":"2","room_id":"9123655","title":"【专访】汽车工业协会原副秘书长荣惠康","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","video_count":"2","video_pic":"http://static.devtool6.com/upload/20170411/56701491910067.png"},{"cid":"919604","cid_url":"1a9abb42-36c7-47f1-a88e-33f65ec7d468.mp4","company_title":"","created":"1491909888","live_type":"2","room_id":"9118902","title":"【专访】中国工程院院士卢秉恒教授","user_pic":"","video_count":"7","video_pic":"http://static.devtool6.com/upload/20170411/50391491909924.png"}]}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "HotModel{" +
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
         * ret : {"list":[{"cid":"472","cid_url":"6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4","company_title":"I智播","created":"1499331474","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070","push_url":"rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474","status":"2","title":"钱江机器人现场工作展示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"287","video_pic":"http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg"},{"cid":"465","cid_url":"f972e54f248d4e0b8b425155334ed76b_1499238092820_1499238822870_15967742-00000.mp4","company_title":"I智播","created":"1499238074","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b","push_url":"rtmp://p8f1debd5.live.126.net/live/f972e54f248d4e0b8b425155334ed76b?wsSecret=70bed4f64b52f66e3b9095a6daa0cffc&wsTime=1499238074","status":"2","title":"Hiwin智能设备现场讲解直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"76","video_pic":"http://static.devtool6.com/upload/2974ff6d6630e9f07299243663947b3e.jpg"},{"cid":"469","cid_url":"566229cb6062499bb32c760e62618708_1499319480416_1499324000223_16003556-00000.mp4","company_title":"I智播","created":"1499319467","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708","push_url":"rtmp://p8f1debd5.live.126.net/live/566229cb6062499bb32c760e62618708?wsSecret=4e310262a189dfcff41657ccb763bc11&wsTime=1499319467","status":"2","title":"2017中国国际服务机器人发展论坛","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"75","video_pic":"http://static.devtool6.com/upload/85f845f5be287f4c0c265cc949ac026e.jpg"},{"cid":"468","cid_url":"25a54dd26e554dfeb619a1a5d70f921d_1499309454179_1499313012432_15998332-00000.mp4","company_title":"I智播","created":"1499309439","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d","push_url":"rtmp://p8f1debd5.live.126.net/live/25a54dd26e554dfeb619a1a5d70f921d?wsSecret=7fc7e279cc389cecd017e0cf8d996cc8&wsTime=1499309440","status":"2","title":"首届中国智能制造产业园区Top50高峰论坛","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"62","video_pic":"http://static.devtool6.com/upload/2ed9ed296221855d464267403802e660.jpg"},{"cid":"432","cid_url":"170de7aaa5c345cfaa0651c05f2222d7_1497411494383_1497412854790_12672128-00000.mp4","company_title":"企业名称12","created":"1497411485","introduce":"","live_online_count":"10","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/170de7aaa5c345cfaa0651c05f2222d7","push_url":"rtmp://p8f1debd5.live.126.net/live/170de7aaa5c345cfaa0651c05f2222d7?wsSecret=cf7cdd069f8017f62a4a90f8e98a3afa&wsTime=1497411485","status":"2","title":"惠普3d打印","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","username":"杨东","video_count":"243","video_pic":"http://static.devtool6.com/upload/3e7ab4a4039d09c4a0cf2d47d0a1224b.jpg"},{"cid":"431","cid_url":"003e74450e2f485a8bd339213df99201_1497409276391_1497409589915_12662459-00000.mp4","company_title":"I智播","created":"1497409252","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/003e74450e2f485a8bd339213df99201","push_url":"rtmp://p8f1debd5.live.126.net/live/003e74450e2f485a8bd339213df99201?wsSecret=d64422a45748ab78e42c5f2d9a83d5a2&wsTime=1497409252","status":"2","title":"顺兴开浩合模机现场演示直播","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"124","video_pic":"http://static.devtool6.com/upload/9cce8b9884c794f63cf36e858fa883fe.jpg"},{"cid":"425","cid_url":"8e4961616c01478da8cf83834394b718_1497332512495_1497333070090_12340596-00000.mp4","company_title":"I智播","created":"1497332409","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/8e4961616c01478da8cf83834394b718","push_url":"rtmp://p8f1debd5.live.126.net/live/8e4961616c01478da8cf83834394b718?wsSecret=9f6602c1cb367a3114ff06d519698231&wsTime=1497332409","status":"2","title":"北京精雕～一个鸡蛋的艺术之旅","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"62","video_pic":"http://static.devtool6.com/upload/0f1bb313bd0b95cb4efef451b8477d03.jpg"},{"cid":"426","cid_url":"b384c52d25ce45dc8e07d1b8ca32d0c6_1497333225014_1497333581482_12343606-00000.mp4","company_title":"I智播","created":"1497333213","introduce":"","live_online_count":"1","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/b384c52d25ce45dc8e07d1b8ca32d0c6","push_url":"rtmp://p8f1debd5.live.126.net/live/b384c52d25ce45dc8e07d1b8ca32d0c6?wsSecret=aa1577035d719996b878f557ef757c14&wsTime=1497333213","status":"2","title":"gf5轴联动全新加工模式","type":"1","type_value":"","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"33","video_pic":"http://static.devtool6.com/upload/d1dba1bdabb64f6eedbed988e269202b.jpg"},{"cid":"308","cid_url":"18574f027ef54045a2cf177d9387c2f0_1496373422403_1496375098266_8654119-00000.mp4","company_title":"i智播","created":"1496373415","introduce":"","live_online_count":"0","live_type":"1","pull_url":"rtmp://v8f1debd5.live.126.net/live/18574f027ef54045a2cf177d9387c2f0","push_url":"rtmp://p8f1debd5.live.126.net/live/18574f027ef54045a2cf177d9387c2f0?wsSecret=2ba92d008c66a5265d2b38f4b96d93ad&wsTime=1496373415","status":"2","title":"GF加工方案中国市场部及销售支持经理孟轩先生","type":"1","type_value":"(null)","user_pic":"http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg","username":"落寞的小丑","video_count":"184","video_pic":"http://static.devtool6.com/upload/ba647bb48277adaade3f4a95fad1d196.jpg"}],"video_list":[{"cid":"917984","cid_url":"5de9a719-baa0-4ea8-9bb9-d73adad7a95c.mp4","company_title":"","created":"1491910295","live_type":"2","room_id":"9129169","title":"【专访】秦川机床工具集团董事长龙兴元花","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","video_count":"2","video_pic":"http://static.devtool6.com/upload/20170411/36681491910257.png"},{"cid":"920065","cid_url":"d67af29c-32f0-4325-b4f8-eec88b40f59b.mp4","company_title":"","created":"1491910097","live_type":"2","room_id":"9123655","title":"【专访】汽车工业协会原副秘书长荣惠康","user_pic":"http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg","video_count":"2","video_pic":"http://static.devtool6.com/upload/20170411/56701491910067.png"},{"cid":"919604","cid_url":"1a9abb42-36c7-47f1-a88e-33f65ec7d468.mp4","company_title":"","created":"1491909888","live_type":"2","room_id":"9118902","title":"【专访】中国工程院院士卢秉恒教授","user_pic":"","video_count":"7","video_pic":"http://static.devtool6.com/upload/20170411/50391491909924.png"}]}
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
            private List<VideoListBean> video_list;

            @Override
            public String toString() {
                return "RetBean{" +
                        "list=" + list +
                        ", video_list=" + video_list +
                        '}';
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<VideoListBean> getVideo_list() {
                return video_list;
            }

            public void setVideo_list(List<VideoListBean> video_list) {
                this.video_list = video_list;
            }

            public static class ListBean {
                /**
                 * cid : 472
                 * cid_url : 6a1cc7af64c04c50a52d1f3954956070_1499331486537_1499331778326_16011992-00000.mp4
                 * company_title : I智播
                 * created : 1499331474
                 * introduce :
                 * live_online_count : 1
                 * live_type : 1
                 * pull_url : rtmp://v8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070
                 * push_url : rtmp://p8f1debd5.live.126.net/live/6a1cc7af64c04c50a52d1f3954956070?wsSecret=0a5d2896a40453479702ce9681f553ab&wsTime=1499331474
                 * status : 2
                 * title : 钱江机器人现场工作展示直播
                 * type : 1
                 * type_value :
                 * user_pic : http://static.devtool6.com/upload/6d782e084e024722c25481f2b180975d.jpg
                 * username : 落寞的小丑
                 * video_count : 287
                 * video_pic : http://static.devtool6.com/upload/947f5b35f470b09000e64a11214db946.jpg
                 */

                private String cid;
                private String cid_url;
                private String company_title;
                private String created;
                private String introduce;
                private String live_online_count;
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
                            ", live_online_count='" + live_online_count + '\'' +
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

                public String getLive_online_count() {
                    return live_online_count;
                }

                public void setLive_online_count(String live_online_count) {
                    this.live_online_count = live_online_count;
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

            public static class VideoListBean {
                /**
                 * cid : 917984
                 * cid_url : 5de9a719-baa0-4ea8-9bb9-d73adad7a95c.mp4
                 * company_title :
                 * created : 1491910295
                 * live_type : 2
                 * room_id : 9129169
                 * title : 【专访】秦川机床工具集团董事长龙兴元花
                 * user_pic : http://static.devtool6.com/upload/008907539f7313b18dca88a5e6781c16.jpg
                 * video_count : 2
                 * video_pic : http://static.devtool6.com/upload/20170411/36681491910257.png
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
                    return "VideoListBean{" +
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
