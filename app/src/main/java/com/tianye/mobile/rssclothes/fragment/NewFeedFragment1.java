package com.tianye.mobile.rssclothes.fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.common.ItemListEntityParser;
import com.tianye.mobile.rssclothes.common.SectionHelper;
import com.tianye.mobile.rssclothes.common.SeriaHelper;
import com.tianye.mobile.rssclothes.entity.ItemListEntity;
import com.tianye.mobile.rssclothes.util.AppConfig;
import com.tianye.mobile.rssclothes.util.AppContextUtil;
import com.tianye.mobile.rssclothes.util.ToastUtils;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/4/8.
 */
public class NewFeedFragment1 extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, SwipeRefreshLayout.OnRefreshListener{

    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeLayout;

    @InjectView(R.id.grid_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_feed1,null);

        ButterKnife.inject(this, rootView);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeColors(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        getLoaderManager().initLoader(0, null, this);

        loadFirst();
        return rootView;
    }

    private void loadFirst() {
        loadData();
    }

    private void loadData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mSwipeLayout.setRefreshing(false);
            }
        }, 5000);
    }

    class LoadDataTask extends AsyncTask<String,Integer,ItemListEntity>{

        @Override
        protected ItemListEntity doInBackground(String... params) {
            ItemListEntityParser parser = new ItemListEntityParser();
            ItemListEntity entity = parser.parser(params[0]);
            if(entity != null){
                SeriaHelper helper = SeriaHelper.newInstance();
                File cache = SectionHelper.newSdCache(params[0]);
                helper.saveObject(entity,cache);
            }
            return entity;
        }

        @Override
        protected void onPostExecute(ItemListEntity itemListEntity) {
            //super.onPostExecute(itemListEntity);
            if(itemListEntity !=null && !itemListEntity.getItemList().isEmpty()){
                ToastUtils.showLong("ÍøÂçÒì³£");
            }
        }
    }

    private void checkDeprecated() {
        String fileName = getActivity().getFilesDir().getAbsolutePath() + File.separator
                + AppConfig.PREF_DEPRECATED;
        File file = new File(fileName);
        int day = (int) (System.currentTimeMillis() - file.lastModified())/(24*60*60*1000);
        if(day >= 7)
        {
            AppContextUtil.clearCache(getActivity());
            file.setLastModified(System.currentTimeMillis());
        }
    }

}