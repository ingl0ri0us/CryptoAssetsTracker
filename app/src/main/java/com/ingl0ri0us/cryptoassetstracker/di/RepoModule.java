package com.ingl0ri0us.cryptoassetstracker.di;

import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapEndpoints;
import com.ingl0ri0us.cryptoassetstracker.data.cache.Cache;
import com.ingl0ri0us.cryptoassetstracker.data.repo.CoinsRepo;
import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        ApiModule.class,
        CacheModule.class
})
public class RepoModule {
    @Provides
    public Repo getCoinsRepo(Cache cache, NetworkStatus networkStatus, CoinMarketCapEndpoints api) {
        return new CoinsRepo(cache, networkStatus, api);
    }
}
