package com.tianye.mobile.rssclothes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lenovo on 2015/4/10.
 */
public class NewFeedFragment3 extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv= new TextView(getActivity());
        tv.setText("111111111111");
        return tv;
    }
}
