package com.xgkj.ilive.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xgkj.ilive.mvp.model.DBSerachModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：刘净辉
 * 日期: 2017/8/15 0015 15:37
 * 作用:
 */

public class DBManager {

    private volatile  static DBManager dbManager;
    private SQLiteDatabase db;
    private SQLiteDatabase database;

    /**
     * 通过单例模式创建DBManager对象
     * @return
     */
    public static DBManager getInstance(){
        if (dbManager == null){
            synchronized (DBManager.class){
                if (dbManager == null){
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }

    /**
     * 数据库管理类的初始化操作
     * @param context
     */
    public void init(Context context){
        if (dbManager != null) {
            SerachOpenHelper serachOpenHelper = new SerachOpenHelper(context);
            database = serachOpenHelper.getReadableDatabase();
        }
    }


    /**
     * 向数据库进行插入数据
     * @param data
     */
    public void insertData(String data){
        ContentValues values = new ContentValues();
        values.put("keyword",data);
        database.insert("serach_history",null,values);
    }

    /**
     * 查询数据的操作
     */
    public List<DBSerachModel> queryData(){
        List<DBSerachModel> list = new ArrayList<>();
        Cursor cursor = database.query("serach_history", null, null, null, null, null, null);
        if (cursor!=null && cursor.getCount()>0){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                int keyword = cursor.getColumnIndex("keyword");
                String columnName = cursor.getString(keyword);
                Log.e("columnName", "queryData: "+columnName );
                DBSerachModel dbSerachModel = new DBSerachModel();
                dbSerachModel.setData(columnName);
                list.add(0,dbSerachModel);
            }
        }
        return list;
    }

    /**
     * 清除数据库
     */
    public int deleteData(){
        int delete = database.delete("serach_history", null, null);
        return delete;
    }
}
