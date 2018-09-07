package com.xgkj.ilive.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：刘净辉
 * 日期: 2017/8/25 0025 16:04
 * 作用: 网络连接状态判断
 */

public class NetWorkManager {

    /**
     * 没有网络连接
     */
    private static final int NETWORK_NONE = -1;

    /**
     * 移动数据网络
     */
    private static final int NETWORK_MOBILE = 0;

    /**
     * wifi数据
     */
    private static final int NETWORK_WIFI = 1;

    /**
     * 获取网络工作的状态
     * @param context
     * @return
     */
    public static int getNetWorkStatus(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo!=null && networkInfo.isConnected()){
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                return NETWORK_WIFI;
            }else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                return NETWORK_MOBILE;
            }
        }else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }



}
