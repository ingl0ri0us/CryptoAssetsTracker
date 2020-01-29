package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
class TestNetworkStatusModule {
    @Provides
    NetworkStatus networkStatus() {
        return Mockito.mock(NetworkStatus.class);
    }
}