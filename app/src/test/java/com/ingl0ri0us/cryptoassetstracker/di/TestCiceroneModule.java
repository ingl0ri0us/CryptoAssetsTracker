package com.ingl0ri0us.cryptoassetstracker.di;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Router;

@Module
class TestCiceroneModule {
    @Provides
    Router getRouter() {
        return Mockito.mock(Cicerone.create().getRouter().getClass());
    }
}