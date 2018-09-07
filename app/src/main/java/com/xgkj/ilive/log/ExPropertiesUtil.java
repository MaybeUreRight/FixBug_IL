package com.xgkj.ilive.log;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 作者: 刘净辉
 * 日期: 2017/3/30 11:49.
 *
 */

public class ExPropertiesUtil {

    //获取输出log的时获取标识名称
    private static final String TAG = ExPropertiesUtil.class.getSimpleName();
    private Context mContext;
    //路径
    private String mPath;
    //文件
    private String mFile;
    //属性
    private Properties mProps;

    private static ExPropertiesUtil exPropertiesUtil = null;

    public static ExPropertiesUtil getExPropertiesUtil(Context context){

        if (exPropertiesUtil == null){
            exPropertiesUtil = new ExPropertiesUtil();
            exPropertiesUtil.mPath = Environment.getExternalStorageDirectory()+"/LogDemo_log";
            exPropertiesUtil.mFile="properties.ini";
            exPropertiesUtil.mContext=context;
        }

        return exPropertiesUtil;
    }

    public ExPropertiesUtil setPath(String path){
        mPath = path;
        return this;
    }

    public ExPropertiesUtil setFile(String file){
        mFile= file;
        return this;
    }

    public ExPropertiesUtil init(){
        Log.d(TAG, "init: path="+mPath + "/" +mFile);
        FileInputStream is = null;
        try{
            File dir = new File(mPath);
            if (!dir.exists()){
                dir.mkdirs();
            }

            File file = new File(dir, mFile);
            if (!file.exists()){
                file.createNewFile();
            }
            is = new FileInputStream(file);
            mProps = new Properties();
            mProps.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭流
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  this;
    }



    public void commit(){
        File file = new File(mPath + "/" + mFile);
        try {
            OutputStream os = new FileOutputStream(file);
            mProps.store(os,"");
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mProps.clear();
    }

    public void clear(){
        mProps.clear();
    }

    public void open(){
        mProps.clear();
        File file = new File(mPath + "/" + mFile);
        try {
            InputStream is = new FileInputStream(file);
            mProps.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeString(String key,String value){
        mProps.setProperty(key,value);
    }

    public void readString(String key , String defaultValue){
        mProps.getProperty(key,defaultValue);
    }

    public void writeInt(String key ,int value){
        mProps.setProperty(key,value +"");
    }

    public void readInt(String key , int defaultValue){
        mProps.getProperty(key,defaultValue+"");
    }

    public void writeBoolean(String key,boolean value){
        mProps.setProperty(key,""+value);
    }

    public void readBoolean(String key,boolean defaultValue){
        mProps.getProperty(key,defaultValue+"");
    }

    public void writeDouble(String key,double value){
        mProps.setProperty(key,""+value);
    }

    public void readDouble(String key,double defaultValue){
        mProps.getProperty(key,defaultValue+"");
    }
}
