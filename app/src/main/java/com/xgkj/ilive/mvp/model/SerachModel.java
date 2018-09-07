package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.SerachContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 12:10
 */

public class SerachModel implements SerachContract.Model {


    /**
     * APIDATA : {"code":200,"hot_search":["能力风暴机器人","钛金属的锻造","GF加工方案"]}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "SerachModel{" +
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
         * hot_search : ["能力风暴机器人","钛金属的锻造","GF加工方案"]
         */

        private int code;
        private List<String> hot_search;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", hot_search=" + hot_search +
                    '}';
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<String> getHot_search() {
            return hot_search;
        }

        public void setHot_search(List<String> hot_search) {
            this.hot_search = hot_search;
        }
    }
}
