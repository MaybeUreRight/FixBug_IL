package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/7/23 0023 14:09
 */

public class UpLoadIconModel {


    /**
     * APIDATA : {"code":200,"ret":{"filename":"/upload/08dc7c4e34a49b5075639ef32dbf878e.jpg","url":"http://192.168.0.127:9003/upload/08dc7c4e34a49b5075639ef32dbf878e.jpg"}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "UpLoadIconModel{" +
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
         * ret : {"filename":"/upload/08dc7c4e34a49b5075639ef32dbf878e.jpg","url":"http://192.168.0.127:9003/upload/08dc7c4e34a49b5075639ef32dbf878e.jpg"}
         */

        private int code;
        private RetBean ret;

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
            /**
             * filename : /upload/08dc7c4e34a49b5075639ef32dbf878e.jpg
             * url : http://192.168.0.127:9003/upload/08dc7c4e34a49b5075639ef32dbf878e.jpg
             */

            private String filename;
            private String url;

            @Override
            public String toString() {
                return "RetBean{" +
                        "filename='" + filename + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
