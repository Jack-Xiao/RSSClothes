package com.tianye.mobile.rssclothes.ui;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import com.tianye.mobile.rssclothes.R;

/**
 * Created by lenovo on 2015/4/15.
 */
public class TestActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout1);
        View decorView = getWindow().getDecorView();
        //inside your activity (if you did not enable transitions in your theme)
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //set an exit transition
        getWindow().setExitTransition(new Explode());
        //Window.setAllowEnterTransitionOverlap().

        Intent intent = new Intent();
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());


        // Calling setSystemUiVisibility() with a value of 0 clears
        // all flags.
        decorView.setSystemUiVisibility(0);
    }
    void test1(){
//        final View imgContainerView = findViewById(R.id.img_container);
//        final View androidRobotView = findViewById(R.id.image_small);
        //define a click listener
        final View imgContainerView=null;
        imgContainerView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this,Activity2.class);
                //create the transition animation - the images in the layouts
                //of both activities are defined with android:transitionName="robot"
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
//                        this,androidRobotView,"robot");
                //start the new activity
                //startActivity(intent,options.toBundle());
               // finishAfterTransition();
            }
        });
        //animator (动画器)
        /*
            ObjectAnimator mAnimator;
            mAnimator = ObjectAnimator.onFloat(view,View.X,View.Y,path);
            ...
            mAnimator.start();
         */
        //PathInterpolator p = new PathInterpolator();

    }
    void test(){
        ObjectAnimator obj = new ObjectAnimator();
        View decorView = getWindow().getDecorView();
    // Calling setSystemUiVisibility() with a value of 0 clears
    // all flags.
        decorView.setSystemUiVisibility(0);
    }
    //cursor buffers   --- content provider --- client
    // 虚拟内存范围    allocations与dellocations 大块的数据可以使得物理内存能够被正常的回收.  heap
    /*
        切换应用  cached.
     */
    //使用 IntentService

    /**
     *
     */
    //TestActivity()

    //接收到用户离开你的UI时的通知
    //所有UI组件被隐藏的时候接收到该回调方法
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    void test2(){


    }

    /**
     * 优化
     *
     *   Enums的内存消耗通常是static constants的2倍。你应该尽量避免在Android上使用enums。
         在Java中的每一个类(包括匿名内部类)都会使用大概500 bytes。
         每一个类的实例花销是12-16 bytes。
         往HashMap添加一个entry需要额一个额外占用的32 bytes的entry对象。
     */
}
