package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.utils.DeviceNetworkStatus;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class NetworkStatusModule {
    @Singleton
    @Provides
    NetworkStatus networkStatus() {
        return new DeviceNetworkStatus();
    }
}