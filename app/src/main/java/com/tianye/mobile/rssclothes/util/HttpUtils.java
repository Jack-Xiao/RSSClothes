package com.tianye.mobile.rssclothes.util;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lenovo on 2015/4/22.
 */
public class HttpUtils {

    private static HttpURLConnection conn = null;
    public static final String tag = "HttpUtils";
    private static final int TIMEOUT = 1000 * 10;

    public static InputStream getInputStream(String url) throws IOException {
        InputStream is = null;
        URL httpURL = null;
        httpURL = new URL(url);
        conn = (HttpURLConnection) httpURL.openConnection();
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(TIMEOUT);
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            is = conn.getInputStream();
        }
        return is;
    }
}
