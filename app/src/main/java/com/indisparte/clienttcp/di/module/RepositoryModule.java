package com.indisparte.clienttcp.di.module;

import com.indisparte.clienttcp.data.network.PotholeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Singleton
    @Provides
    public PotholeRepository provideRepository() {
        return PotholeRepository.getInstance();
    }
}
