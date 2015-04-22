package com.tianye.mobile.rssclothes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2015/4/16.
 */
public class FeedDBManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "feed.db";

    public FeedDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateState(String tableName,int state, String url){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state",state);
        db.update(tableName,values,"url=?",new String[]{url});
        db.close();
    }
}
