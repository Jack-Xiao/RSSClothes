package com.tianye.mobile.rssclothes.util;

import java.io.File;

/**
 * Created by lenovo on 2015/4/14.
 */
public class AppConfig {
    //SD卡目录
    public static final String APP_ROOT_DIR = FileUtils.getSDRootPath() + File.separator + "SimpleReader";
    public static final String APP_CACHE_DIR = APP_ROOT_DIR + File.separator + "cache";
    public static final String APP_SECTION_DIR = APP_CACHE_DIR + File.separator + "sections";
    public static final String APP_IMAGE_CACHE_DIR = APP_CACHE_DIR + File.separator + "images";
    public static final String APP_IMAGE_DIR = APP_ROOT_DIR + File.separator + "images";
    //文章过期配置
    public static final String PREF_DEPRECATED = "pref_deprecated";




    //有盟
    public static final String UM_BASE_KEY = "com.dreamteam.reader";
}
