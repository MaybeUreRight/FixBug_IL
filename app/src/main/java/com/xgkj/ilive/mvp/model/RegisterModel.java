package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.RegisterContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 15:06
 */

public class RegisterModel implements RegisterContract.Model {

    /**
     * APIDATA : {"ret":{"code":404,"msg":"手机号的值\"12236252365\"已经被占用了。"}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "RegisterModel{" +
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
         * ret : {"code":404,"msg":"手机号的值\"12236252365\"已经被占用了。"}
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
             * msg : 手机号的值"12236252365"已经被占用了。
             */

            private int code;
            private String msg;

            @Override
            public String toString() {
                return "RetBean{" +
                        "code=" + code +
                        ", msg='" + msg + '\'' +
                        '}';
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
