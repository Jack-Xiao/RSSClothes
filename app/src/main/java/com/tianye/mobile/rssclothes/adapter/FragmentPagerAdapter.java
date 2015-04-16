package com.tianye.mobile.rssclothes.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;

import com.tianye.mobile.rssclothes.fragment.FragmentProvider;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2015/4/8.
 */
public abstract class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter
        implements FragmentProvider {

    private final ActionBarActivity activity;
    private final FragmentManager fragmentManager;
    private Fragment selected;
    private final Set<String> tags = new HashSet<>();


    public FragmentPagerAdapter(ActionBarActivity activity) {
        super(activity.getSupportFragmentManager());
        fragmentManager = activity.getSupportFragmentManager();
        this.activity = activity;
    }

    public FragmentPagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
        fragmentManager = fragment.getChildFragmentManager();
        this.activity = (ActionBarActivity) fragment.getActivity();
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public FragmentPagerAdapter clearAdapter() {
        if (tags.isEmpty())
            return this;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (String tag : tags) {
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                transaction.remove(fragment);
            }
        }
        transaction.commit();
        tags.clear();
        return this;
    }

    public Fragment getSelected(){
        return selected;
    }

    public Object instantiateItem(ViewGroup container,int position){
        Object fragment = super.instantiateItem(container,position);
        if(fragment instanceof Fragment){
            tags.add(((Fragment) fragment).getTag());
        }
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        boolean changed = false;
        if(object instanceof Fragment){
            changed = object !=selected;
            selected = (Fragment) object;
        }else{
            changed = object !=null;
            selected = null;
        }
        if(changed)
            activity.invalidateOptionsMenu();
    }
}
