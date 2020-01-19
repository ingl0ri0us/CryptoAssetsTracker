package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App app() {
        return app;
    }
}
