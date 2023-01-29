package com.indisparte.clienttcp.di.module;

import com.indisparte.clienttcp.data.network.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Singleton
    @Provides
    public Repository provideRepository() {
        return Repository.getInstance();
    }
}
