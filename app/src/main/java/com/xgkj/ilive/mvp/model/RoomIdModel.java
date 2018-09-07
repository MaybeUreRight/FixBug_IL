package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/6 0006 15:13
 */

public class RoomIdModel {

    /**
     * APIDATA : {"ret":{"code":404,"msg":"参数不正确"}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "RoomIdModel{" +
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
         * ret : {"code":404,"msg":"参数不正确"}
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
             * code : 404
             * msg : 参数不正确
             */

            private int code;
            private String msg;
            private String roomid;

            @Override
            public String toString() {
                return "RetBean{" +
                        "code=" + code +
                        ", msg='" + msg + '\'' +
                        ", roomid='" + roomid + '\'' +
                        '}';
            }

            public String getRoomid() {
                return roomid;
            }

            public void setRoomid(String roomid) {
                this.roomid = roomid;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }
    }
}
