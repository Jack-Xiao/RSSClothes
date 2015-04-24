package com.tianye.mobile.rssclothes.common;

import com.tianye.mobile.rssclothes.entity.FeedItem;
import com.tianye.mobile.rssclothes.entity.ItemListEntity;
import com.tianye.mobile.rssclothes.util.DateUtils;
import com.tianye.mobile.rssclothes.util.HtmlFilter;
import com.tianye.mobile.rssclothes.util.HttpUtils;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by lenovo on 2015/4/22.
 */
public class ItemListEntityParser extends DefaultHandler {
    private static final String TAG = "ItemListEntityParser";

    private StringBuilder sb = new StringBuilder();

    private boolean isTitle = true;
    private boolean isDesc = true;
    private boolean isLink = true;

    private ItemListEntity itemListEntity;
    private FeedItem feedItem;
    private ArrayList<FeedItem> items = new ArrayList<FeedItem>();
    private String feedTitle;


    @Override
    public void startDocument() throws SAXException
    {
        itemListEntity = new ItemListEntity();
    }

    @Override
    public void endDocument() throws SAXException
    {
        itemListEntity.setItemList(items);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException
    {
        sb.setLength(0);
        if(qName.equalsIgnoreCase("item"))
        {
            feedItem = new FeedItem();
            items.add(feedItem);
            isTitle = false;
            isDesc = false;
            isLink = false;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {
        String content = sb.toString();
        if(!isLink && qName.equalsIgnoreCase("link"))
        {
            feedItem.setLink(content);
        }
        if(qName.equalsIgnoreCase("title"))
        {
            if(isTitle)
            {
                feedTitle = content;
            }
            else
            {
                feedItem.setTitle(content);
            }
            return;
        }
        if(!isDesc && (qName.equalsIgnoreCase("description") || qName.equalsIgnoreCase("content:encoded")))
        {
            feedItem.setContent(content);
            ArrayList<String> srcs = HtmlFilter.getImageSrcs(content);
            if(!srcs.isEmpty())
                feedItem.setFirstImageUrl(srcs.get(0));
            feedItem.setImageUrls(srcs);
            isDesc = false;
            return;
        }
        if(qName.equalsIgnoreCase("pubDate"))
        {
            content = DateUtils.rfcNormalDate(content);
            if(feedItem != null)
                feedItem.setPubdate(content);
        }
    }

    public ItemListEntity parser(String url){
        SAXParserFactory saxpf = SAXParserFactory.newInstance();
        SAXParser saxp = null;
        InputStream inputStream = null;
        InputSource inputSource = null;
        try{
            inputStream = HttpUtils.getInputStream(url);
            inputSource = new InputSource(inputStream);
            saxp = saxpf.newSAXParser();
            XMLReader xmlr = saxp.getXMLReader();
            xmlr.setContentHandler(this);
            xmlr.parse(inputSource);
            return itemListEntity;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        }finally
        {
            if(inputStream != null)
            {
                try
                {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }

    }
}
