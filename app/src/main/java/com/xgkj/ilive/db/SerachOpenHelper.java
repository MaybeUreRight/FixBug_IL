package com.xgkj.ilive.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：刘净辉
 * 日期: 2017/8/15 0015 15:33
 */

public class SerachOpenHelper extends SQLiteOpenHelper{

    /**
     * 数据库名字
     */
    public static final String DATABSE_NAME = "serach_history.db";
    /**
     * 版本号
     */
    private static final int VERSION = 1;

    public SerachOpenHelper(Context context) {
        super(context, DATABSE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table serach_history(serachid integer primary key autoincrement,keyword text)");
        sqLiteDatabase.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
