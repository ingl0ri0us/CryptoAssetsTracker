package com.ingl0ri0us.cryptoassetstracker;

import android.app.Application;

import com.ingl0ri0us.cryptoassetstracker.di.AppComponent;
import com.ingl0ri0us.cryptoassetstracker.di.AppModule;
import com.ingl0ri0us.cryptoassetstracker.di.DaggerAppComponent;

import io.paperdb.Paper;
import timber.log.Timber;

public class App extends Application {

    static private App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        Paper.init(this);

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
