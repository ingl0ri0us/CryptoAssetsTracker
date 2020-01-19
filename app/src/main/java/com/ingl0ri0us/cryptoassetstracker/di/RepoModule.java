package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapEndpoints;
import com.ingl0ri0us.cryptoassetstracker.data.repo.CoinsRepo;
import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        ApiModule.class
})
public class RepoModule {
    @Provides
    public Repo getCoinsRepo(NetworkStatus networkStatus, CoinMarketCapEndpoints api) {
        return new CoinsRepo(networkStatus, api);
    }
}
