package com.tianye.mobile.rssclothes.fragment;


import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tianye.mobile.rssclothes.data.RequestManager;
import com.tianye.mobile.rssclothes.util.ToastUtils;

/**
 * Created by lenovo on 2015/4/7.
 */
public class BaseFragment extends Fragment {

    public void onDestory(){
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    protected void executeRequest(Request request){
        RequestManager.addRequest(request,this);
    }
    protected Response.ErrorListener errorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showLong(volleyError.getMessage());
            }
        };
    }
}
