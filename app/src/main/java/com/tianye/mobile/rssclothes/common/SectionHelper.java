package com.tianye.mobile.rssclothes.common;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.tianye.mobile.rssclothes.db.DBConstant;
import com.tianye.mobile.rssclothes.db.FavoItemDBHelper;
import com.tianye.mobile.rssclothes.util.AppConfig;
import com.tianye.mobile.rssclothes.util.FileUtils;
import com.tianye.mobile.rssclothes.util.MD5;

import java.io.File;

/**
 * Created by lenovo on 2015/4/22.
 */
public class SectionHelper {

    public static void removeRecord(SQLiteDatabase db,String url){
        db.delete(DBConstant.SECTION_TABLE_NAME , "url = ?",new String[]{url});
        db.close();
    }

    public static void insert(SQLiteDatabase db,String tableName,String title,String url){
        ContentValues values =new ContentValues();
        values.put(FavoItemDBHelper.SECTION_TITLE,tableName);
        values.put(FavoItemDBHelper.TITLE,title);
        values.put("url", url);
        db.insert(DBConstant.SECTION_TABLE_NAME, null, values);
    }
    public static File newSdCache(String url){
        String name= AppConfig.APP_ROOT_DIR + File.separator + MD5.Md5(url);
        return FileUtils.newAbsoluteFile(name);
    }

    public static File getSdCache(String url){
        return new File(AppConfig.APP_SECTION_DIR + File.separator + MD5.Md5(url));
    }
}
