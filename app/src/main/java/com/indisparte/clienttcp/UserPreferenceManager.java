package com.indisparte.clienttcp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.indisparte.clienttcp.di.component.ClientApplication;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Used to load username from the preferences
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
public class UserPreferenceManager {
    public static final String USERNAME_PREF_KEY= "pref_username";
    private static UserPreferenceManager INSTANCE = null;
    private SharedPreferences mPreferenceManager;

    private UserPreferenceManager() {
        mPreferenceManager = PreferenceManager
                .getDefaultSharedPreferences(ClientApplication.getContext());
    }

    public static UserPreferenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserPreferenceManager();
        }
        return INSTANCE;
    }

    public String getUserName() {
        return mPreferenceManager.getString(USERNAME_PREF_KEY, null);
    }
}
