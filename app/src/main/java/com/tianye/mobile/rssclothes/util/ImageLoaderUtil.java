package com.tianye.mobile.rssclothes.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2015/4/14.
 */
public class ImageLoaderUtil {
    public static final String tag = "ImageLoader";
    private static HashMap<String,SoftReference<Bitmap>> cache;
    private static Map<ImageView,String> imageViews;
    private static ExecutorService pool;
    private Bitmap defBitmap;

    static {
        cache = new HashMap<String,SoftReference<Bitmap>>();
        pool = Executors.newFixedThreadPool(5);
        imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView,String>());
    }

    public Bitmap getCacheImage(String url){
        Bitmap bmp = null;
        if(cache.containsKey(url)){
            bmp = cache.get(url).get();
        }
        return bmp;
    }
    public void loadImage(String url,ImageView imageView,int width,int height){
        imageViews.put(imageView,url);
        Bitmap bmp = getCacheImage(url);
        if(bmp !=null){
            imageView.setImageBitmap(bmp);
        }else{
            File file = AppContextUtil.getSdImgCache(url);
            if(file.exists()){
                try{
                    bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageView.setImageBitmap(bmp);
                }catch(OutOfMemoryError e){
                    e.printStackTrace();
                }
            }else{
                imageView.setImageBitmap(defBitmap);
                loadNetImage(url,imageView,width,height);
            }
        }
    }
    private void loadNetImage(final String url,final ImageView imageView,final int width,final int height){
        final Handler handler = new Handler(){
//            String tag = imageViews.get(imageViews);
//            if(tag !=null && tag.equals(url)){
//                if(msg.obj !=null){
//                    imageView.setImageBitmap((Bitmap)msg.obj);
//                }
//            }
        };
    }
}
