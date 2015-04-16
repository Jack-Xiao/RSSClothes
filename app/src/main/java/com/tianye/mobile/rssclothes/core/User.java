package com.tianye.mobile.rssclothes.core;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/4/8.
 */
public class User implements Serializable {

    public int id;
    public int followers;
    public int following;
    public String email;
    public String name;
    public String company;

}
