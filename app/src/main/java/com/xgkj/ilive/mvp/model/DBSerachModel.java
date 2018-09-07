package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/15 0015 15:47
 * 作用:数据库管理的实体类
 */

public class DBSerachModel {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DBSerachModel{" +
                "data='" + data + '\'' +
                '}';
    }
}
