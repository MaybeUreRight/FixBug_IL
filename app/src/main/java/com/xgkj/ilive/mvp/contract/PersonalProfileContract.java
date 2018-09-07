package com.xgkj.ilive.mvp.contract;

import android.content.Intent;
import android.graphics.Bitmap;

import com.xgkj.ilive.mvp.model.MineModel;

/**
 * 作者：刘净辉
 * 日期: 2017/7/23 0023 16:21
 */

public interface PersonalProfileContract {
    interface Model {
    }

    interface View {
        //获取用户信息完成
        void getUserInfoFinished( MineModel.APIDATABean.RetBean ret);

        void getUserData(String photoData);
    }

    interface Presenter {
        //获取用户信息
        void getUserInfo();
        //进行设置用户头像的方法
        void updateUserIcon(Intent data);
        //进行选择本地图片进行更改头像
        void updateLocalPicture(String photo);
        //返回处理后的图片
        Bitmap getBitmap(String filePath, int destWidth, int destHeight);
    }
}
