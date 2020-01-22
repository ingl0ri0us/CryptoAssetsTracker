package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.ui.activity.MainActivity;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsFragment;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsPresenter;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.fullcoininfo.FullCoinInfoFragment;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.fullcoininfo.FullCoinInfoPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        RepoModule.class,
        CiceroneModule.class,
        ImageModule.class
})
public interface AppComponent {
    void inject(AllCoinsFragment allCoinsFragment);

    void inject(AllCoinsPresenter allCoinsPresenter);

    void inject(MainActivity mainActivity);

    void inject(FullCoinInfoFragment fullCoinInfoFragment);

    void inject(FullCoinInfoPresenter fullCoinInfoPresenter);
}
