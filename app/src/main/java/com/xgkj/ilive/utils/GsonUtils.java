package com.xgkj.ilive.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 作者：刘净辉
 * 日期: 2017/7/15 0015 20:51
 * 封装解析json的工具类
 */

public class GsonUtils {


    /**
     * json数据转换成实体类
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static<T> T jsonToBean(String json,Class<T> cls){
        Gson  gson = new Gson();
        T t = gson.fromJson(json, cls);
        return t;
    }

    /**
     * 上传json格式的参数设置
     * @param map
     * @return
     */
    public static JSONObject upJson(Map<String,String> map){
        JSONObject jobj = new JSONObject();
        JSONObject jobj2 = new JSONObject();
        try {
            Set<String> strings = map.keySet();
            Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = map.get(key);
                jobj2.put(key,value);
            }
            jobj.put("APIDATA",jobj2);
            return jobj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传json格式的参数设置
     * @param map
     * @return
     */
    public static JSONObject upIntJson(Map<String,Integer> map){
        JSONObject jobj = new JSONObject();
        JSONObject jobj2 = new JSONObject();
        try {
            Set<String> strings = map.keySet();
            Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                int value = map.get(key);
                jobj2.put(key,value);
            }
            jobj.put("APIDATA",jobj2);
            return jobj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传json格式的参数设置
     * @param map
     * @return
     */
    public static JSONObject upObjextJson(Map<String,Integer> map){
        JSONObject jobj = new JSONObject();
        JSONObject jobj2 = new JSONObject();
        try {
            Set<String> strings = map.keySet();
            Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                int value = map.get(key);
                jobj2.put(key,value);
            }
            jobj.put("APIDATA",jobj2);
            return jobj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
