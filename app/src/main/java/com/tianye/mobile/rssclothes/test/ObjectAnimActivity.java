package com.tianye.mobile.rssclothes.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lenovo on 2015/4/15.
 */
public class ObjectAnimActivity extends Activity {
    private ObjectAnimator mBlueBall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    public void rotateyAnimRun(final View view){
//        ObjectAnimator
//                .ofFloat(view,"rotationX",0.0F,360F)
//                .setDuration(500)
//                .start();
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view,"zhy",1.0F,0.0F)
                .setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float)animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }
    public void propertyValuesHolder(View view){
        PropertyValuesHolder pvhx = PropertyValuesHolder.ofFloat("alpha",1f,0f,1f);
        PropertyValuesHolder pvhy = PropertyValuesHolder.ofFloat("scaleX",1f,0,1f);
        PropertyValuesHolder pvhz = PropertyValuesHolder.ofFloat("scaleY",1f,0,1f);
        ObjectAnimator.ofPropertyValuesHolder(view,pvhx,pvhy,pvhz).setDuration(1000).start();
    }
    public void verticalRun(View view){
//        ValueAnimator animator = ValueAnimator.ofFloat(0,mScreenHeight - mBlueBall.getHeight());
//        animator.setTarget(mBlueBall);
//        animator.setDuration(1000).start();
    }
    /**
     * 自由落体
     */
    public void FreeDown(View view){
       // ValueAnimator animator = ValueAnimator.ofFloat(0,mScreenHeight - mBlueBall.getHeight());
        //animator.setTarget(mBlueBall);
//        animator.setDuration(1000).start();
//        //animator.setInterpolator(value);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mBlueBall.setTranslationY((Float)animation.getAnimatedValue());
//            }
//        });
    }
    /**
     * 抛物线
     */
    public void paowuxian(View view){
        ValueAnimator anim = new ValueAnimator();
        anim.setDuration(3000);
        anim.setObjectValues(new PointF(0,0));
        anim.setInterpolator((new LinearInterpolator()));
        anim.setEvaluator(new TypeEvaluator<PointF>(){
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                point.x = 200 * fraction *3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point =(PointF) animation.getAnimatedValue();
//                mBlueBall.setX(point.x);
//                mBlueBall.setY(point.y);
            }
        });
    }
    public void fadeOut(View view){
        ObjectAnimator anim = ObjectAnimator.ofFloat(mBlueBall,"alpha",0.5f);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //start
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //end
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //cancel
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //repeat
//                ViewGroup parent = (ViewGroup) mBlueBall.getParent();
//                if(parent !=null){
//                   // parent.removeView(mBlueBall);
//                }
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                ViewGroup parent = (ViewGroup)mBlueBall.getParent();
//                if(parent !=null){
//                    parent.removeView(mBlueBall);
//                }
            }
        });
    }
    public void togetherRun(View view){
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall,"scaleX",1.0f,2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall,"scaleY",1.0f,2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        // 两个动画同时执行
        animSet.playTogether(anim1,anim2);
        animSet.start();
    }
    public void playWithAfter(View view){
        //float cx = mBlueBall.getX();
        float cx =1;// = mBlueBall.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",
                1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall,
                "x",  cx ,  0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall,
                "x", cx);

        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);

        animSet.play(anim4).after(anim3);
        //animSet.playTogether();
        animSet.setDuration(1000);
        animSet.start();
    }

    public void scaleX(View view){
//        Animator anim = AnimatorInflater.loadAnimator(this, R.anim.scalex);
//        anim.setTarget(mMv);
//        anim.start();

        Animator anim = null; //= AnimatorInflater.loadAnimator(this, R.anim.scalexy);
//        mMv.setPivotX(0);
//        mMv.setPivotY(0);
//        //显示的调用invalidate
//        mMv.invalidate();
//        anim.setTarget(mMv);
        anim.start();
        LayoutTransition transition = new LayoutTransition();
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING,
                transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING,null);
        transition.setAnimator(LayoutTransition.DISAPPEARING,null);
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,null);

        //LayoutTransition.APPEARING  当一个View在ViewGroup中出现时，对此View设置的动画
        //LayoutTransition.CHANGE_APPEARING 当一个View 在ViewGroup中出现时，对此View其他View位置造成影响
        //对其他View 设置的动画
        //LayoutTransition.DISAPPEARING 当一个View在ViewGroup中消失时，对此View设置的动画
        //LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响
        //对其他View设置的动画
        //LayoutTransition.CHANGE不是由于View出现或消失造成对其他View位置造成影响，然后对其他View设置的动画
        //注意动画到底设置在谁身上，此View 还是其他View.


    }
}
