package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsFragment;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        RepoModule.class
})
public interface AppComponent {
    void inject(AllCoinsFragment allCoinsFragment);
    void inject(AllCoinsPresenter allCoinsPresenter);

}
