package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestRepoModule {
    @Provides
    public Repo getCoinsRepo() {
        return Mockito.mock(Repo.class);
    }
}
