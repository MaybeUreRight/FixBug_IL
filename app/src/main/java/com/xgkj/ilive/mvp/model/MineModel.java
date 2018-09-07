package com.xgkj.ilive.mvp.model;

import com.xgkj.ilive.mvp.contract.MineContract;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 10:52
 */

public class MineModel implements MineContract.Model {


    /**
     * APIDATA : {"code":200,"ret":{"pic":"http://192.168.0.127:9003/upload/55a7409439442c7d1045d88233dc5a7b.jpg","nickname":"伟大的兔子","sex":"1","industry":"1","accid":"E109705C9F35697CA41DCD1EF595982F","accid_token":"337aef03147349899c94460cf7ca8066"}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "MineModel{" +
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
         * ret : {"pic":"http://192.168.0.127:9003/upload/55a7409439442c7d1045d88233dc5a7b.jpg","nickname":"伟大的兔子","sex":"1","industry":"1","accid":"E109705C9F35697CA41DCD1EF595982F","accid_token":"337aef03147349899c94460cf7ca8066"}
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
            /**
             * pic : http://192.168.0.127:9003/upload/55a7409439442c7d1045d88233dc5a7b.jpg
             * nickname : 伟大的兔子
             * sex : 1
             * industry : 1
             * accid : E109705C9F35697CA41DCD1EF595982F
             * accid_token : 337aef03147349899c94460cf7ca8066
             */

            private String pic;
            private String nickname;
            private String sex;
            private String industry;
            private String accid;
            private String accid_token;
            private int bind_company;
            private String company_id;
            private String company_title;
            private String company_abbr;

            public int getBind_company() {
                return bind_company;
            }

            public void setBind_company(int bind_company) {
                this.bind_company = bind_company;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
            }

            public String getCompany_title() {
                return company_title;
            }

            public void setCompany_title(String company_title) {
                this.company_title = company_title;
            }

            public String getCompany_abbr() {
                return company_abbr;
            }

            public void setCompany_abbr(String company_abbr) {
                this.company_abbr = company_abbr;
            }

            @Override
            public String toString() {
                return "RetBean{" +
                        "pic='" + pic + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", sex='" + sex + '\'' +
                        ", industry='" + industry + '\'' +
                        ", accid='" + accid + '\'' +
                        ", accid_token='" + accid_token + '\'' +
                        ", bind_company=" + bind_company +
                        ", company_id='" + company_id + '\'' +
                        ", company_title='" + company_title + '\'' +
                        ", company_abbr='" + company_abbr + '\'' +
                        '}';
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getIndustry() {
                return industry;
            }

            public void setIndustry(String industry) {
                this.industry = industry;
            }

            public String getAccid() {
                return accid;
            }

            public void setAccid(String accid) {
                this.accid = accid;
            }

            public String getAccid_token() {
                return accid_token;
            }

            public void setAccid_token(String accid_token) {
                this.accid_token = accid_token;
            }
        }
    }
}
