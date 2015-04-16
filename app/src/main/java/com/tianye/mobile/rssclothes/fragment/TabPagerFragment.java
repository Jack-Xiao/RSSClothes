package com.tianye.mobile.rssclothes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.view.SlidingTabLayout;

/**
 * Created by lenovo on 2015/4/8.
 */
public abstract class TabPagerFragment<V extends PagerAdapter & FragmentProvider>
                extends PagerFragment implements TabHost.OnTabChangeListener,TabHost.TabContentFactory{

    /**
     * View pager
     */
    protected ViewPager pager;

    protected SlidingTabLayout slidingTabsLayout;

    protected V adapter;

    protected abstract V createAdapter();

    protected String getTitle(final int position) {
        return adapter.getPageTitle(position).toString();
    }
    @Override
    public View createTabContent(String tag) {
        //return ViewUtils.setGone(new View(getActivity().getApplication()), true);
        return null;
    }

    protected String getIcon(final int position) {
        return null;
    }

    protected TabPagerFragment<V> setGone(boolean gone) {
//        ViewUtils.setGone(slidingTabsLayout, gone);
//        ViewUtils.setGone(pager, gone);
        return this;
    }
    protected void setCurrentItem(final int position){

    }
    protected int getContentView(){
        return R.layout.pager_with_tabs;
    }

    private void createPager(){
        adapter = createAdapter();
        getActivity().supportInvalidateOptionsMenu();
        pager.setAdapter(adapter);
        slidingTabsLayout.setViewPager(pager);
    }
    protected void configureTabPager(){
        createPager();
    }

    @Override
    public void onTabChanged(String tabId) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentView(),null);
    }

    //indicator 指示器
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.toolbar).setVisibility(View.GONE);

        //On Lollipop, the action bar shadow is provided by default, so have to remove it explicitly
        ((ActionBarActivity)getActivity()).getSupportActionBar().setElevation(0);
        pager = (ViewPager)view.findViewById(R.id.vp_pages);
        pager.setOnPageChangeListener(this);
        slidingTabsLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs_layout);
        slidingTabsLayout.setCustomTabView(R.layout.tab, R.id.tv_tab);
        slidingTabsLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.white));
        slidingTabsLayout.setDividerColors(0);
    }
    @Override
    protected FragmentProvider getProvider() {
        return adapter;
    }
}
