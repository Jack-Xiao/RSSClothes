package com.tianye.mobile.rssclothes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tianye.mobile.rssclothes.data.RequestManager;
import com.tianye.mobile.rssclothes.util.ToastUtils;

import static com.tianye.mobile.rssclothes.fragment.PagerSlidingTabStripFragment.SELECTION_POSITION;

/**
 * Created by lenovo on 2015/4/7.
 */
public class BaseFragment extends Fragment {

    protected int position;
    public void onDestory(){
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position =(int) getArguments().getInt(SELECTION_POSITION);
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
