package com.tianye.mobile.rssclothes.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String Md5(String str) {
        if (str != null && !str.equals("")) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                char[] HEX =
                        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                                'c', 'd', 'e', 'f'};
                byte[] md5Byte = md5.digest(str.getBytes("UTF8"));
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < md5Byte.length; i++) {
                    sb.append(HEX[(int) (md5Byte[i] & 0xff) / 16]);
                    sb.append(HEX[(int) (md5Byte[i] & 0xff) % 16]);
                }
                str = sb.toString();
            } catch (NoSuchAlgorithmException e) {
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || networkInfo.isConnected() == false) {
            return false;
        }
        return true;
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static SharedPreferences getPrefrences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //清楚缓存
    public static void clearCache(Context context) {
        clearSdCache();
        clearWebViewCache(context);
    }

    public static void clearSdCache() {
        FileUtils.deleteDirectory(AppConfig.APP_SECTION_DIR);
        FileUtils.deleteDirectory(AppConfig.APP_IMAGE_CACHE_DIR);
    }

    //清除webview缓存
    public static void clearWebViewCache(Context context) {
//		File file = CacheManager.getCacheFileBaseDir();
//		if (file != null && file.exists() && file.isDirectory()) {
//		    for (File item : file.listFiles()) {
//		    	item.delete();
//		    }
//		    file.delete();
//		}
        context.deleteDatabase("webview.db");
        context.deleteDatabase("webview.db-shm");
        context.deleteDatabase("webview.db-wal");
        context.deleteDatabase("webviewCache.db");
        context.deleteDatabase("webviewCache.db-shm");
        context.deleteDatabase("webviewCache.db-wal");
    }

    public static File getSectionCache(String url)
    {
        return new File(AppConfig.APP_SECTION_DIR
                + File.separator + MD5.Md5(url));
    }
}
