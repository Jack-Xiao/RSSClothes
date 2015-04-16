package com.tianye.mobile.rssclothes.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by lenovo on 2015/4/8.
 */
public abstract class PagerFragment extends Fragment implements ViewPager.OnPageChangeListener{

    /*
        Get Provider of the currently selected fragment
        @return fragment provider
     */
    protected abstract FragmentProvider getProvider();

    protected Fragment getFragment(){
        FragmentProvider provider = getProvider();
        if(provider !=null){
            return provider.getSelected();
        }else
            return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = getFragment();
        if(fragment !=null)
            return fragment.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Fragment fragment = getFragment();
        if (fragment != null)
            fragment.onCreateOptionsMenu(menu, getActivity().getMenuInflater());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
