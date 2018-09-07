package com.xgkj.ilive.mvp.model;

/**
 * 作者：刘净辉
 * 日期: 2017/8/4 0004 15:50
 */

public class LookStatusModel {

    private boolean isChecked;
    private String look_status;
    private String look_description;

    public LookStatusModel(boolean isChecked, String look_status, String look_description) {
        this.isChecked = isChecked;
        this.look_status = look_status;
        this.look_description = look_description;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getLook_status() {
        return look_status;
    }

    public void setLook_status(String look_status) {
        this.look_status = look_status;
    }

    public String getLook_description() {
        return look_description;
    }

    public void setLook_description(String look_description) {
        this.look_description = look_description;
    }

    @Override
    public String toString() {
        return "LookStatusModel{" +
                "isChecked=" + isChecked +
                ", look_status='" + look_status + '\'' +
                ", look_description='" + look_description + '\'' +
                '}';
    }
}
