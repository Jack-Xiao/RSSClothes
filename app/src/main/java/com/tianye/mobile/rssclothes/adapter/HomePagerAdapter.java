package com.tianye.mobile.rssclothes.adapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.core.User;
import com.tianye.mobile.rssclothes.fragment.NeedFragment2;
import com.tianye.mobile.rssclothes.fragment.NewFeedFragment;
import com.tianye.mobile.rssclothes.fragment.NewFeedFragment3;

import java.util.HashSet;
import java.util.Set;

/**
 * Pager adapter for a user's different views
 *
 * Created by lenovo on 2015/4/8.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private final User org;
    private boolean defaultUser;
    private final FragmentManager fragmentManager;
    private final Resources resources;
    private final Set<String> tags = new HashSet<>();

    public HomePagerAdapter(final Fragment fragment,final boolean defaultUser,final User org){
        super(fragment);

        this.org = org;
        fragmentManager = fragment.getChildFragmentManager();
        resources = fragment.getResources();
        this.defaultUser = defaultUser;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                //fragment = defaultUser ? new
                //fragment = defaultUser ? new UserReceivedNew
                fragment = new NewFeedFragment();
                break;
            case 1:
                fragment = new NeedFragment2();
                break;
            case 2:
                fragment = new NewFeedFragment3();
                break;
            case 3:
                break;
        }
        if(fragment !=null){
            Bundle args = new Bundle();
            //args.putSerializable("org",org);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return defaultUser ? 4 : 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return resources.getString(R.string.tab_news);
            case 1:
                return resources.getString(R.string.tab_repositories);
            case 2:
                return resources.getString(defaultUser ? R.string.tab_followers_self
                                : R.string.tab_members);
            case 3:
                return resources.getString(R.string.tab_following_self);
            default:
                return null;
        }
    }
}