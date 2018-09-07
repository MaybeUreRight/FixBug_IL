package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.IndustryContract;

import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/10 0010 10:53
 */

public class IndustryModel implements IndustryContract.Model {

    /**
     * APIDATA : {"list":[{"id":"1","title":"五金"},{"id":"2","title":"电器"},{"id":"3","title":"金融"}],"code":200}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "IndustryModel{" +
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
         * list : [{"id":"1","title":"五金"},{"id":"2","title":"电器"},{"id":"3","title":"金融"}]
         * code : 200
         */

        private int code;
        private List<ListBean> list;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code=" + code +
                    ", list=" + list +
                    '}';
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * title : 五金
             */

            private String id;
            private String title;

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
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
        }
    }
}
