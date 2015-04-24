package com.tianye.mobile.rssclothes.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;

import com.tianye.mobile.rssclothes.common.ItemListEntityParser;
import com.tianye.mobile.rssclothes.data.FeedCategoryDao;
import com.tianye.mobile.rssclothes.data.FeedDao;
import com.tianye.mobile.rssclothes.entity.Feed;
import com.tianye.mobile.rssclothes.entity.FeedCategory;
import com.tianye.mobile.rssclothes.entity.FeedItem;
import com.tianye.mobile.rssclothes.entity.ItemListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/4/23.
 */
public class RSSPullService extends IntentService {
    private int position;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RSSPullService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

//        Intent localIntent = new Intent(Constants.BROADCAST_ACTION)
//                                //把status放到Intent中
//                                .putExtra(Constants.EXTENDED_DATA_STATUS, status);
//        //广播Intent给应用中的接收器
//        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

        List<FeedCategory> list = GetCategoryList();
        FeedDao feedDao = new FeedDao(this);
        for(int i = 1; i <= list.size(); i++){
            ArrayList<Feed> feeds = feedDao.getListByCategoryId(i);
            for(int l= 0; l < feeds.size();l++){

                GetRSSInfo(feeds.get(i));
            }
        }
       // List<>
    }

    private void GetRSSInfo(Feed feed) {

        new AsyncTask<String,Void,ItemListEntity>(){

            @Override
            protected ItemListEntity doInBackground(String... params) {
                String url = params[0];
                ItemListEntityParser parser = new ItemListEntityParser();
                ItemListEntity entity= parser.parser(url);

                return entity;

            }

            @Override
            protected void onPostExecute(ItemListEntity entity) {
                //super.onPostExecute(s);
                for (int i =0;i<entity.getItemList().size();i++){
                    FeedItem item = entity.getItemList().get(i);

                }
            }
        }.execute(feed.getUrl());
    }

    private List<FeedCategory> GetCategoryList(){
        FeedCategoryDao dao  = new FeedCategoryDao(this);
        List<FeedCategory> list = dao.getList();
        return list;

    }
}
