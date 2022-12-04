package com.indisparte.clienttcp;

import android.app.Application;
import android.content.Context;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class ClientApplication extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
    }

    public static Context getsApplicationContext() {
        return sApplicationContext;
    }
}
