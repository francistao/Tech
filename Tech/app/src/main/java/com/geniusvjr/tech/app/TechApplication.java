package com.geniusvjr.tech.app;

import android.app.Application;

import com.geniusvjr.tech.db.DatabaseHelper;

/**
 * Created by dream on 16/4/10.
 */
public class TechApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.init(this);
    }
}
