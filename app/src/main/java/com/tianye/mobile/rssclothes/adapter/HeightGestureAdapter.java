package com.tianye.mobile.rssclothes.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tianye.mobile.rssclothes.R;

/**
 * Created by lenovo on 2015/4/16.
 */
public class HeightGestureAdapter extends CursorAdapter {
    private static final int[] COLORS = {R.color.holo_blue_light, R.color.holo_green_light, R.color.holo_orange_light, R.color.holo_purple_light, R.color.holo_red_light};

    private static final int IMAGE_MAXHEIGHT = 240;
    private LayoutInflater mLayoutInflater;
    private ListView mListView;
    private Drawable mImageDrawable;
    private Resources mResource;

    public HeightGestureAdapter(Context context, ListView listView){
        super(context,null,false);
        mResource = context.getResources();
        mLayoutInflater = LayoutInflater.from(context);
        mListView = listView;
    }

    @Override
    public Object getItem(int position) {
        mCursor.moveToPosition(position);
        //return super.getItem(position);
        return null;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
