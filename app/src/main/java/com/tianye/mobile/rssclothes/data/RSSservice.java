package com.tianye.mobile.rssclothes.data;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/4/13.
 */
public class RSSservice {
    public static List<RSSItem> getRessItems(InputStream is) {
        List<RSSItem> list = null;
        RSSItem item = null;

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "utf-8");
            int type = parser.getEventType();
            while (type != XmlPullParser.END_TAG) {
                switch (type) {
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<RSSItem>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("item".equals(parser.getName())) {
                            item = new RSSItem();
                        }
                        if (item != null) {
                            if ("title".equals(parser.getName())) {
                                item.setTitle(parser.nextText());
                            } else if ("link".equals(parser.getName())) {
                                item.setLink(parser.nextText());
                            } else if ("description".equals(parser.getName())) {
                                item.setDescription(parser.nextText());
                            } else if ("pubDate".equals(parser.getName())) {
                                item.setPubDate(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("item".equals(parser.getName())){
                            list.add(item);
                            item = null;
                        }
                        break;
                }
                type = parser.next();
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
