package com.tianye.mobile.rssclothes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianye.mobile.rssclothes.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/4/13.
 */
public class AG9Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.test_fragment,null);
        ButterKnife.inject(this,contentView);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright);

//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeLayout.setRefreshing(true);//动画
//                (new Handler()).postDelayed(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                        double f = Math.random();
//
//                        // adapter.notifyDataSetChanged();
//                    }
//                },3000);
//            }
//        });

        return contentView;
    }


    @Override
    public void onRefresh() {

    }
}
