package com.tianye.mobile.rssclothes.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/4/14.
 */
public class anim_CrossfadeFragment extends BaseFragment {
    @InjectView(R.id.loading_spinner)
    View mLoadingView;
    int mShortAnimationDuration;
    @InjectView(R.id.content)
    View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.anim_crossfade,null);
        ButterKnife.inject(this,contentView);
        mContentView.setVisibility(View.GONE);

        //获取并缓存系统默认的短时长
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        return contentView;
    }


    private void crossfade(){
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);
        //开始动画内容view 到100%的不透明度，然后清除所有设置在view上的动画监听器
        mContentView.animate()
                .alpha(1f)  //不透明
                .setDuration(mShortAnimationDuration)
                .setListener(null);
        /*
            加载View 开始动画逐渐变为0%的不透明度
            动画结束后，设置可见性为GONE(消失)作为一个优化步骤
            （它将不再参与布局的传递等过程）
         */
        mLoadingView.animate()
                .alpha(0f) //不透明
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //super.onAnimationEnd(animation);
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
    }
    private void ids(){

    }
    public class ZoomOutPagerTransformer implements ViewPager.PageTransformer{
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            if(position < -1){ //[-$, -1]
                //这一页已经是最左边的屏幕页
                page.setAlpha(0);
            }else if(position <= 1){ //[-1,1]
                // 修改默认的滑动过滤效果为缩放效果
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight *(1 - scaleFactor) /2;  //垂直
                float horzMargin = pageWidth * (1 - scaleFactor) / 2; //水平
                if(position <0){
                    page.setTranslationX(horzMargin - vertMargin / 2);
                }else{
                    page.setTranslationX(-horzMargin + vertMargin /2);
                }
                //开始根据缩放系统数进行变化( 在MIN_SCALE和 1 之间变化)
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                //根据大小（缩放系数） 变化化透明度实现淡化页面效果
                page.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) *(1 - MIN_ALPHA));
            }else{
                page.setAlpha(0);
            }
        }
    }

}