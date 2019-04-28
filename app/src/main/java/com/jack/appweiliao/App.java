package com.jack.appweiliao;

import android.app.Application;

public class App extends Application {
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}