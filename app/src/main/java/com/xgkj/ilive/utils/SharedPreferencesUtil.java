package com.xgkj.ilive.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 作者：刘净辉
 * 日期：2017/7/12 11:14
 */

public class SharedPreferencesUtil {

    /**
     * 保存pager状态的数据
     * @param context
     * @param key
     * @param value
     */
    public static void savePagerStatus(Context context,String key,Boolean value){
        SharedPreferences sp = context.getSharedPreferences("is_first", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key,value).commit();
    }

    public static Boolean getPagerStatus(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("is_first", Context.MODE_PRIVATE);
        return sp.getBoolean(key,true);
    }

    /**
     * 保存accesstoken
     * @param context
     */
    public static void saveAccessToken(Context context,String key,String value){
        SharedPreferences access_token = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = access_token.edit();
        edit.putString(key,value);
        edit.commit();
    }

    /**
     * 获取保存用户的信息
     * @param context
     * @param key
     * @return
     */
    public static String getAccessToken(Context context,String key){
        SharedPreferences access_token = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);
        String access_tokenString = access_token.getString(key, "");
        return access_tokenString;
    }

    /**
     * 保护用户昵称
     * @param context
     * @param key
     * @param value
     */
    public static void saveNickName(Context context,String key,String value){
        SharedPreferences register_yunxinid = context.getSharedPreferences("register_yunxinid", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = register_yunxinid.edit();
        edit.putString(key,value).commit();
    }

    /**
     * 获取用户昵称
     * @param context
     * @param key
     * @return
     */
    public static String getNickName(Context context,String key){
        SharedPreferences register_yunxinid = context.getSharedPreferences("register_yunxinid", Context.MODE_PRIVATE);
        return register_yunxinid.getString(key,"");
    }


    public static void savePhotoData(Context context,String key,String value){
        SharedPreferences register_yunxinid = context.getSharedPreferences("register_yunxinid", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = register_yunxinid.edit();
        edit.putString(key,value).commit();
    }

    public static String  getPhotoData(Context context,String key){
        SharedPreferences register_yunxinid = context.getSharedPreferences("register_yunxinid", Context.MODE_PRIVATE);
        return register_yunxinid.getString(key,"");
    }
    /**
     * 保存登录的accid和accid_token
     * @param context
     * @param loginstatusMap
     */
    public static void saveLoginStatus(Context context,Map<String,String> loginstatusMap){
        SharedPreferences sp = context.getSharedPreferences("ilive_login", Context.MODE_PRIVATE);
        Set<String> keySet = loginstatusMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        SharedPreferences.Editor edit = sp.edit();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = loginstatusMap.get(key);
            edit.putString(key,value);
        }
        edit.commit();
    }

    public static  String getLoginStatus(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("ilive_login", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void clearAccessToke(Context context){
        SharedPreferences access_token = context.getSharedPreferences("access_token", Context.MODE_PRIVATE);
        access_token.edit().clear().commit();
    }
}
