package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/16 0016 17:22
 */

public class MineCompanyInfoModel {

    /**
     * APIDATA : {"code":"200","ret":{"id":9,"title":"海尔","logo":"http://static.devtool6.com/150184289259844dcc1b15c9.03684053.png","type":"4","contacts":"刘图图","mobile":"18001307178","remarks":"","abbreviation":"","created":"1501842892","username":"18001307178","password":"e10adc3949ba59abbe56e057f20f883e","real_password":"123456","status":1,"auth_key":"","background":"http://static.devtool6.com/"}}
     */

    private APIDATABean APIDATA;

    @Override
    public String toString() {
        return "MineCompanyInfoModel{" +
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
         * ret : {"id":9,"title":"海尔","logo":"http://static.devtool6.com/150184289259844dcc1b15c9.03684053.png","type":"4","contacts":"刘图图","mobile":"18001307178","remarks":"","abbreviation":"","created":"1501842892","username":"18001307178","password":"e10adc3949ba59abbe56e057f20f883e","real_password":"123456","status":1,"auth_key":"","background":"http://static.devtool6.com/"}
         */

        private String code;
        private RetBean ret;

        @Override
        public String toString() {
            return "APIDATABean{" +
                    "code='" + code + '\'' +
                    ", ret=" + ret +
                    '}';
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
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
             * id : 9
             * title : 海尔
             * logo : http://static.devtool6.com/150184289259844dcc1b15c9.03684053.png
             * type : 4
             * contacts : 刘图图
             * mobile : 18001307178
             * remarks :
             * abbreviation :
             * created : 1501842892
             * username : 18001307178
             * password : e10adc3949ba59abbe56e057f20f883e
             * real_password : 123456
             * status : 1
             * auth_key :
             * background : http://static.devtool6.com/
             */

            private int id;
            private String title;
            private String logo;
            private String type;
            private String contacts;
            private String mobile;
            private String remarks;
            private String abbreviation;
            private String created;
            private String username;
            private String password;
            private String real_password;
            private int status;
            private String auth_key;
            private String background;

            @Override
            public String toString() {
                return "RetBean{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", logo='" + logo + '\'' +
                        ", type='" + type + '\'' +
                        ", contacts='" + contacts + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", remarks='" + remarks + '\'' +
                        ", abbreviation='" + abbreviation + '\'' +
                        ", created='" + created + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", real_password='" + real_password + '\'' +
                        ", status=" + status +
                        ", auth_key='" + auth_key + '\'' +
                        ", background='" + background + '\'' +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getAbbreviation() {
                return abbreviation;
            }

            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getReal_password() {
                return real_password;
            }

            public void setReal_password(String real_password) {
                this.real_password = real_password;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAuth_key() {
                return auth_key;
            }

            public void setAuth_key(String auth_key) {
                this.auth_key = auth_key;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }
        }
    }
}
