package com.indisparte.clienttcp.di.component;

import android.app.Application;
import android.content.Context;

import dagger.hilt.android.HiltAndroidApp;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@HiltAndroidApp
public class ClientApplication extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplicationContext = getApplicationContext();

    }

    public static Context getContext() {
        return sApplicationContext;
    }

}
