package com.tianye.mobile.rssclothes.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.tianye.mobile.rssclothes.entity.FeedItem;

/**
 * Created by lenovo on 2015/4/16.
 */
public class FavoItemDBHelper {
    public static final String TITLE = "title";
    public static final String PUBDATE = "pubdate";
    public static final String DETAIL = "item_detail";
    public static final String LINK = "link";
    public static final String FIRST_IMG_URL = "first_img_url";
    public static final String SECTION_TITLE = "table_name";
    public static final String SECTION_URL = "table_url";

    static final Object DBLock = new Object();



    public static void insert(SQLiteDatabase db, String title,
                              String pubdate, String itemDetail, String link,
                              String firstImgUrl, String sectionTitle, String sectionUrl)
    {
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(PUBDATE, pubdate);
        values.put(DETAIL, itemDetail);
        values.put(LINK, link);
        values.put(FIRST_IMG_URL, firstImgUrl);
        values.put(SECTION_TITLE, sectionTitle);
        values.put(SECTION_URL, sectionUrl);

        db.insert(DBConstant.FAVO_TABLE_NAME, null, values);
        db.close();
    }

    public void insert(FeedItem item){
        ContentValues values = new ContentValues();
        values.put(TITLE, item.getTitle());
        values.put(PUBDATE,item.getPubdate());
        values.put(DETAIL,item.getContent());
        values.put(LINK,item.getLink());
        values.put(FIRST_IMG_URL,item.getFirstImageUrl());
        //values.put(SECTION_TITLE,item.);


    }



    public static void removeRecord(SQLiteDatabase db, String link) {
        db.delete(RSSDBManager.FAVORITE_ITEM_TABLE_NAME, "link=?", new String[]{link});
        db.close();
    }
}
