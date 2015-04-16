package com.tianye.mobile.rssclothes.ui;

import android.content.Intent;
import android.os.Bundle;

import com.tianye.mobile.rssclothes.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2015/4/7.
 */
public class LoginActivity extends BaseActivity {
//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.login);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.login_wb)
    void OnClickWB(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    @OnClick(R.id.login_wx)
        void OnClickWX(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
}
