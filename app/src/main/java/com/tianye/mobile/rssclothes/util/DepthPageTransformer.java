package com.tianye.mobile.rssclothes.util;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by lenovo on 2015/4/14.
 */
public class DepthPageTransformer implements  ViewPager.PageTransformer{
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        if(position < -1){
            page.setAlpha(0);
        }else if(position <= 0){
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        }else if(position <=1){ //(0,1]
            //淡出页面
            page.setAlpha(1 - position);
            //抵消默认的整页过滤
            page.setTranslationX(pageWidth *-position);
            //根据缩放系数变化(在 MIN_SCALE和 1 之间变化)
            float scaleFactor = MIN_SCALE + (1-MIN_SCALE)*(1-Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }else{ // (1,]
            //这一页已经是最右边的屏幕页
            page.setAlpha(0);
        }
    }
}
