package com.manriqueweb.mwepgmedia;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.manriqueweb.mwepgmedia.utils.Constants;

public class MwEpgApp extends Application {
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat(MwEpgApp.class.getSimpleName());

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public synchronized final boolean isDebbugMode() {
        return isDebbug();
    }

    private boolean isDebbug() {
        Log.d(LOG_TAG, String.valueOf(ApplicationInfo.FLAG_DEBUGGABLE));
        return 0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE);
    }

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }
}
