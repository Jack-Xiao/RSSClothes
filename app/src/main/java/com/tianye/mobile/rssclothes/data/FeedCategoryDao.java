package com.tianye.mobile.rssclothes.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tianye.mobile.rssclothes.db.FeedDBManager;
import com.tianye.mobile.rssclothes.db.RSSDBManager;
import com.tianye.mobile.rssclothes.entity.FeedCategory;

import java.util.ArrayList;


public class FeedCategoryDao
{
	public static final String TABLE_NAME = "feed_category";
	private Context context;
	
	public FeedCategoryDao(Context context)
	{
		this.context = context;
	}
	
	public ArrayList<FeedCategory> getList()
	{
		ArrayList<FeedCategory> fcList = new ArrayList<FeedCategory>();
		
		RSSDBManager  helper = new RSSDBManager(context, FeedDBManager.DB_NAME, null, 1);
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		if (cursor.moveToFirst())
		{
			for (int i = 0, n = cursor.getCount(); i < n; i++)
			{
				FeedCategory fc = new FeedCategory();
				fc.setId(cursor.getInt(cursor.getColumnIndex("cid")));
				fc.setName(cursor.getString(cursor.getColumnIndex("cname")));
				fcList.add(fc);
				
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		
		return fcList;
	}
	
	public ArrayList<String> getNameList()
	{
		ArrayList<String> fcList = new ArrayList<String>();

		RSSDBManager helper = new RSSDBManager(context, FeedDBManager.DB_NAME, null, 1);
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query("category", null, null, null, null, null, null);
		if (cursor.moveToFirst())
		{
			for (int i = 0, n = cursor.getCount(); i < n; i++)
			{
				fcList.add(cursor.getString(cursor.getColumnIndex("fname")));

				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		
		return fcList;
	}
}
