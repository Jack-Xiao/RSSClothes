package com.tianye.mobile.rssclothes.entity;

import android.media.Image;

import java.util.HashMap;

/**
 * Created by lenovo on 2015/4/16.
 */
public class RSSFeed{
    private static final HashMap<String,RSSFeed> CACHE = new HashMap<String,RSSFeed>();

    public String id;
    public String title;
    public Image image;
    public String link;
    public Vote votes;

    private class Vote{
        public int count;
    }
    private static void addToCache(RSSFeed feed){
        CACHE.put(feed.id,feed);
    }
    private static RSSFeed getFromCache(String id){
        return CACHE.get(id);
    }

//    public static RSSFeed fromCursor(Cursor cursor){
//        //String id = cursor.getString(cursor.getColumnIndex())
//
//    }
}
