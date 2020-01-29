package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        TestRepoModule.class,
        TestCiceroneModule.class,
        TestNetworkStatusModule.class})
public interface TestComponent {
    void inject(AllCoinsPresenter presenter);
}