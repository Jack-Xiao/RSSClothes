package com.tianye.mobile.rssclothes.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.service.RSSPullService;
import com.tianye.mobile.rssclothes.util.Constants;

/**
 * Created by lenovo on 2015/4/14.
 */
public class SuperAwesomeCardFragment extends BaseFragment{

    public static final String ARG_POSITION = "position";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);


        //过滤器的动作条件是自定义的BROADCAST_ACTION
        IntentFilter mStatusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        //为http协议添加一个数据过滤器
        mStatusIntentFilter.addDataScheme("http");


    }


    private int position;
    private static final int[] drawables = { R.drawable.f, R.drawable.s, R.drawable.t, R.drawable.fo,
            R.drawable.fi, R.drawable.fi, R.drawable.fi, R.drawable.fi };


    public static BaseFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);

        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);
        fl.setBackgroundResource(drawables[position]);
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
                .getDisplayMetrics());

        TextView v = new TextView(getActivity());
        params.setMargins(margin, margin, margin, margin);
        v.setLayoutParams(params);
        v.setLayoutParams(params);
        v.setGravity(Gravity.BOTTOM);
        v.setText("CARD " + (position + 1));

        fl.addView(v);

        return fl;
    }

    /**
     * 提供当前Fragment的主色调的Bitmap对象,供Palette解析颜色
     *
     * @return
     */
    public static int getBackgroundBitmapPosition(int selectViewPagerItem) {
        return drawables[selectViewPagerItem];
    }
}
