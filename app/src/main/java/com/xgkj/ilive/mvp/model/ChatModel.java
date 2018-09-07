package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.ChatContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/27 0027 10:38
 */

public class ChatModel implements ChatContract.Model {


    /**
     * APIDATA : {"code":200,"roomid":"9758615","ret":{"list":[{"id":"11","formnick":"哈哈哈哈","msgtype":"TEXT","fromext":"b01e1f395cacde2e761d170b656917ce","fromAvator":"2583ba502f58bc0241a107baf51d406c.jpg","fromAccount":"b01e1f395cacde2e761d170b656917ce","fromclienttype":"IOS","eventtype":"4","roomid":"9758615","msgidclient":"e1c5ea2d-1f9b-439d-a727-249cb2a943cb","resendflag":"0","msgtimestamp":"1500888113929","roleinfotimetag":"1500888108816","attach":"太赞了"},{"id":"12","formnick":"昵称","msgtype":"TEXT","fromext":"b01e1f395cacde2e761d170b656917ce","fromAvator":"2583ba502f58bc0241a107baf51d406c.jpg","fromAccount":"b01e1f395cacde2e761d170b656917ce","fromclienttype":"IOS","eventtype":"4","roomid":"9758615","msgidclient":"5a8454eb-df94-445c-a175-9e1cb3c30b79","resendflag":"0","msgtimestamp":"1500888140888","roleinfotimetag":"1500888108816","attach":"聊天内容"}]}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "ChatModel{" +
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
         * roomid : 9758615
         * ret : {"list":[{"id":"11","formnick":"哈哈哈哈","msgtype":"TEXT","fromext":"b01e1f395cacde2e761d170b656917ce","fromAvator":"2583ba502f58bc0241a107baf51d406c.jpg","fromAccount":"b01e1f395cacde2e761d170b656917ce","fromclienttype":"IOS","eventtype":"4","roomid":"9758615","msgidclient":"e1c5ea2d-1f9b-439d-a727-249cb2a943cb","resendflag":"0","msgtimestamp":"1500888113929","roleinfotimetag":"1500888108816","attach":"太赞了"},{"id":"12","formnick":"昵称","msgtype":"TEXT","fromext":"b01e1f395cacde2e761d170b656917ce","fromAvator":"2583ba502f58bc0241a107baf51d406c.jpg","fromAccount":"b01e1f395cacde2e761d170b656917ce","fromclienttype":"IOS","eventtype":"4","roomid":"9758615","msgidclient":"5a8454eb-df94-445c-a175-9e1cb3c30b79","resendflag":"0","msgtimestamp":"1500888140888","roleinfotimetag":"1500888108816","attach":"聊天内容"}]}
         */

        private int code;
        private String roomid;
        private RetBean ret;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", roomid='" + roomid + '\'' +
                    ", ret=" + ret +
                    '}';
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
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
                 * id : 11
                 * formnick : 哈哈哈哈
                 * msgtype : TEXT
                 * fromext : b01e1f395cacde2e761d170b656917ce
                 * fromAvator : 2583ba502f58bc0241a107baf51d406c.jpg
                 * fromAccount : b01e1f395cacde2e761d170b656917ce
                 * fromclienttype : IOS
                 * eventtype : 4
                 * roomid : 9758615
                 * msgidclient : e1c5ea2d-1f9b-439d-a727-249cb2a943cb
                 * resendflag : 0
                 * msgtimestamp : 1500888113929
                 * roleinfotimetag : 1500888108816
                 * attach : 太赞了
                 */

                private String id;
                private String formnick;
                private String msgtype;
                private String fromext;
                private String fromAvator;
                private String fromAccount;
                private String fromclienttype;
                private String eventtype;
                private String roomid;
                private String msgidclient;
                private String resendflag;
                private String msgtimestamp;
                private String roleinfotimetag;
                private String attach;

                @Override
                public String toString() {
                    return "ListBean{" +
                            "id='" + id + '\'' +
                            ", formnick='" + formnick + '\'' +
                            ", msgtype='" + msgtype + '\'' +
                            ", fromext='" + fromext + '\'' +
                            ", fromAvator='" + fromAvator + '\'' +
                            ", fromAccount='" + fromAccount + '\'' +
                            ", fromclienttype='" + fromclienttype + '\'' +
                            ", eventtype='" + eventtype + '\'' +
                            ", roomid='" + roomid + '\'' +
                            ", msgidclient='" + msgidclient + '\'' +
                            ", resendflag='" + resendflag + '\'' +
                            ", msgtimestamp='" + msgtimestamp + '\'' +
                            ", roleinfotimetag='" + roleinfotimetag + '\'' +
                            ", attach='" + attach + '\'' +
                            '}';
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getFormnick() {
                    return formnick;
                }

                public void setFormnick(String formnick) {
                    this.formnick = formnick;
                }

                public String getMsgtype() {
                    return msgtype;
                }

                public void setMsgtype(String msgtype) {
                    this.msgtype = msgtype;
                }

                public String getFromext() {
                    return fromext;
                }

                public void setFromext(String fromext) {
                    this.fromext = fromext;
                }

                public String getFromAvator() {
                    return fromAvator;
                }

                public void setFromAvator(String fromAvator) {
                    this.fromAvator = fromAvator;
                }

                public String getFromAccount() {
                    return fromAccount;
                }

                public void setFromAccount(String fromAccount) {
                    this.fromAccount = fromAccount;
                }

                public String getFromclienttype() {
                    return fromclienttype;
                }

                public void setFromclienttype(String fromclienttype) {
                    this.fromclienttype = fromclienttype;
                }

                public String getEventtype() {
                    return eventtype;
                }

                public void setEventtype(String eventtype) {
                    this.eventtype = eventtype;
                }

                public String getRoomid() {
                    return roomid;
                }

                public void setRoomid(String roomid) {
                    this.roomid = roomid;
                }

                public String getMsgidclient() {
                    return msgidclient;
                }

                public void setMsgidclient(String msgidclient) {
                    this.msgidclient = msgidclient;
                }

                public String getResendflag() {
                    return resendflag;
                }

                public void setResendflag(String resendflag) {
                    this.resendflag = resendflag;
                }

                public String getMsgtimestamp() {
                    return msgtimestamp;
                }

                public void setMsgtimestamp(String msgtimestamp) {
                    this.msgtimestamp = msgtimestamp;
                }

                public String getRoleinfotimetag() {
                    return roleinfotimetag;
                }

                public void setRoleinfotimetag(String roleinfotimetag) {
                    this.roleinfotimetag = roleinfotimetag;
                }

                public String getAttach() {
                    return attach;
                }

                public void setAttach(String attach) {
                    this.attach = attach;
                }
            }
        }
    }
}
