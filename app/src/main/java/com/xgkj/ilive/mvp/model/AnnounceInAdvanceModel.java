package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.AnnounceInAdvanceContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/3 0003 13:52
 */

public class AnnounceInAdvanceModel implements AnnounceInAdvanceContract.Model {

    /**
     * APIDATA : {"code":200,"ret":[]}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "AnnounceInAdvanceModel{" +
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
         * ret : []
         */

        private int code;
        private List<?> ret;

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

        public List<?> getRet() {
            return ret;
        }

        public void setRet(List<?> ret) {
            this.ret = ret;
        }
    }
}
