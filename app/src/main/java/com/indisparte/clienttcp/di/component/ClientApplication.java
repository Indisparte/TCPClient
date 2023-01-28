package com.indisparte.clienttcp.di.component;

import android.app.Application;
import android.content.Context;

import com.indisparte.clienttcp.data.network.Repository;

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
    public static Repository getMyRepository(){return Repository.getInstance();}

    public static Context getContext() {
        return sApplicationContext;
    }

}
