package com.tianye.mobile.rssclothes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2015/4/16.
 */
public class DBManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "reader.db";
    public static final String FAVORITE_ITEM_TABLE_NAME="favorite_item";

    private static final String CREATE_SECTION_TABLE =
            "create table" + " " + DBConstant.SECTION_TABLE_NAME
                    + "(title text, url text, table_name text)";

    private static final String CREATE_FAVORITE_TABLE = "create table"
            + " " + FAVORITE_ITEM_TABLE_NAME
            + "(title text, pubdate text, item_detail text, "
            + "link text, first_img_url text, table_name text, table_url text)";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITE_TABLE);
        db.execSQL(CREATE_SECTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
