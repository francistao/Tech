package com.geniusvjr.tech.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geniusvjr.tech.R;

/**
 * Created by dream on 16/4/9.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity{

    protected Toolbar mToolbar;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        setupToolbar();
        initWidgets();
        afterOnCreate();
    }


    /**
     * 获取Activity的布局id
     */
    protected abstract int getContentViewResId();

    /**
     * 初始化toolbar
     */
    protected void setupToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void initWidgets()
    {

    }

    protected void afterOnCreate()
    {

    }
}
