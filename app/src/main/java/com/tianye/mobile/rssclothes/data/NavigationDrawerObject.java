package com.tianye.mobile.rssclothes.data;

/**
 * Created by lenovo on 2015/4/7.
 */
public class NavigationDrawerObject {
    public static final int DRAWER_ITEM = 1;


    private String title;
    private int type;
    private String imageResource;

    public NavigationDrawerObject(String title,int type){
        this.title = title;
        this.type = type;
    }

//    public NavigationDrawerObject(String title,String imageResource,int type){
//        this.imageResource = imageResource;
//        this.type = type;
//        this.title = title;
//    }

    public NavigationDrawerObject(int type){
        this.type = type;
    }

    public NavigationDrawerObject(String title,String imageResource,int type){
        this.title = title;
        this.type = type;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}
