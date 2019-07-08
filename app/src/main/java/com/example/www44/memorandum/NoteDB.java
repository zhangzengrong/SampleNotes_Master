package com.example.www44.memorandum;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
便签数据库
 数据库的类
 */

public class NoteDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String ARTICLE = "article";
    public static final String ID = "_id";
    public static final String TIME = "time";
    public static final String PATH = "path";
    public static final String VIDEO = "video";
    public static final String mDbName =  SDPath.DB_DIR + File.separator + "notes.db";
    /*
     *  context 上下文
     *  notes 数据库文件名
     *  null 为游标，null表示使用默认创建游标得方式
     *  1 表示数据库版本号（只可以提高不允许降低）
     *
     */
    public NoteDB(Context context) {
        super(context, mDbName, null, 1);
    }

    /*@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT
        +" TEXT NOT NULL," + PATH + " TEXT NOT NULL," + VIDEO
        + " TEXT NOT NULL," + TIME + " TEXT NOT NULL)");
    }*/

    //第一次进入程序先创建数据库和数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CONTENT +" TEXT NOT NULL,"
                + ARTICLE +" TEXT NOT NULL,"
                +TIME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
