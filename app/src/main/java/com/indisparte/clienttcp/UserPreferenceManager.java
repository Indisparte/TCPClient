package com.indisparte.clienttcp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Used to load username from the preferences
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class UserPreferenceManager {
    private static UserPreferenceManager INSTANCE = null;
    private SharedPreferences mPreferenceManager;

    private UserPreferenceManager() {
        mPreferenceManager = PreferenceManager
                .getDefaultSharedPreferences(ClientApplication.getsApplicationContext());
    }

    public static UserPreferenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserPreferenceManager();
        }
        return INSTANCE;
    }

    public String getUserName() {
        return mPreferenceManager.getString("pref_username", null);
    }
}
