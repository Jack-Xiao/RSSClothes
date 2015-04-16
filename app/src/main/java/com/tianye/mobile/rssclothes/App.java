package com.tianye.mobile.rssclothes;

import android.app.Application;
import android.content.Context;

/**
 * Created by lenovo on 2015/4/7.
 */
public class App extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        //initImageLoader(getApplicationContext());
    }

    public static Context getContext() {
        return sContext;
    }

}
