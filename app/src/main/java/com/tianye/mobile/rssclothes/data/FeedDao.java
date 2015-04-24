package com.tianye.mobile.rssclothes.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tianye.mobile.rssclothes.db.FeedDBManager;
import com.tianye.mobile.rssclothes.entity.Feed;

import java.util.ArrayList;


/**
 * @description
 * @author zcloud
 * @date 2014年10月1日
 */
public class FeedDao
{
	public static final String TABLE_NAME = "feed";
	private Context context;
	
	public FeedDao(Context context)
	{
		this.context = context;
	}
	
	public ArrayList<Feed> getListByCategoryId(int id)
	{
		ArrayList<Feed> list = new ArrayList<Feed>();
		FeedDBManager helper = new FeedDBManager(context, FeedDBManager.DB_NAME, null, 1);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME, null, "cid=?", new String[]{String.valueOf(id)}, null, null, null);
		if (cursor.moveToFirst())
		{
			for (int i = 0, n = cursor.getCount(); i < n; i++)
			{
				Feed f = new Feed();
				String title = cursor.getString(cursor.getColumnIndex("fname"));
				String url = cursor.getString(cursor.getColumnIndex("url"));
				int selectStatus = cursor.getInt(cursor
						.getColumnIndex("state"));
				f.setTitle(title);
				f.setUrl(url);
				f.setSelectStatus(selectStatus);
				list.add(f);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		return list;
	}


}
