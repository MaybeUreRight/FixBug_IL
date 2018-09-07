package com.xgkj.ilive.mvp.contract;

import android.content.Intent;
import android.graphics.Bitmap;

import com.xgkj.ilive.mvp.model.MineModel;
import com.xgkj.ilive.view.CircleImageView;

/**
 * 作者：刘净辉
 * 日期: 2017/7/14 0014 10:52
 */

public interface MineContract {
    interface Model {
    }

    interface View {
        //获取用户信息完成
        void getUserInfoFinished( MineModel.APIDATABean.RetBean ret);
        //获取circleimageview控件
        CircleImageView getCicleImageViewWidget();

    }

    interface Presenter {
        //开始跳转的方法
        <T> void startActivity(Class<T> cls);
        //获取用户信息
        void getUserInfo();
        //上传用户头像
        void updateUserIcon(Intent data);
        //上传本地图片
        void updateLocalPicture(String photo);
        //获取选择本地后压缩成功后的图片
        Bitmap getBitmap(String filePath, int destWidth, int destHeight);
        //获取公司信息
        void getCompanyInfo(  String company_id);
    }
}
