package com.tianye.mobile.rssclothes.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.adapter.NavigationDrawerAdapter;
import com.tianye.mobile.rssclothes.fragment.AG9Fragment;
import com.tianye.mobile.rssclothes.fragment.HomePagerFragment;
import com.tianye.mobile.rssclothes.fragment.PagerSlidingTabStripFragment;
import com.tianye.mobile.rssclothes.fragment.SettingFragment;
import com.tianye.mobile.rssclothes.util.ToastUtils;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private NavigationDrawerAdapter navigationDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //mTitle = getTitle();
        navigationDrawerAdapter = new NavigationDrawerAdapter(MainActivity.this);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),navigationDrawerAdapter);
        //selecteFragment(0);
        onNavigationDrawerItemSelected(0);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = selecteFragment(position);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private Fragment selecteFragment(int position) {
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        switch(position){
            case 0:
                fragment =new PagerSlidingTabStripFragment();
                break;
            case 1:
                //selecteFragment(position);
                fragment = new SettingFragment();
                break;
            case 90:
                fragment = new AG9Fragment();
            default:
                fragment = new HomePagerFragment();
                break;
        }
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

//
//    public void onSectionAttached(int number) {
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_section3);
//                break;
//        }
//    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();

            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_share){
            ToastUtils.showLong("action  share");
        }
        return super.onOptionsItemSelected(item);
    }

    void setNavigationBackground(){
        MainActivity.this.getLayoutInflater().setFactory(new android.view.LayoutInflater.Factory() {
            /**
             * name - Tag name to be inflated.<br/>
             * context - The context the view is being created in.<br/>
             * attrs - Inflation attributes as specified in XML file.<br/>
             */
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                // 指定自定义inflate的对象
                if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")) {
                    try {
                        LayoutInflater f = getLayoutInflater();
                        final View view = f.createView(name, null, attrs);
                        new Handler().post(new Runnable() {
                            public void run() {
                                // 设置背景图片
                                view.setBackgroundResource(R.color.section_start);
                            }
                        });
                        return view;
                    } catch (InflateException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }

}
