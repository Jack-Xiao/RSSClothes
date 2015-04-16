package com.tianye.mobile.rssclothes.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tianye.mobile.rssclothes.adapter.HomePagerAdapter;
import com.tianye.mobile.rssclothes.core.User;
import com.tianye.mobile.rssclothes.util.PreferenceUtils;

/**
 * Created by lenovo on 2015/4/7.
 */
public class HomePagerFragment extends TabPagerFragment<HomePagerAdapter> {

    private static final String TAG="HomePagerFragment";
    private static final String PREF_ORG_ID="orgId";

    private SharedPreferences sharedPreferences;
    private boolean isDefaultUser;
    private User org;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        //(User)getArguments().getSerializable("org")
        setOrg();
    }

    private void setOrg() {
        PreferenceUtils.save(sharedPreferences.edit().putInt(PREF_ORG_ID , 1));
        //this.org=org;
        this.isDefaultUser = true;
        configureTabPager();
    }

    @Override
    protected HomePagerAdapter createAdapter() {
        return new HomePagerAdapter(this,isDefaultUser,org);
    }
}
