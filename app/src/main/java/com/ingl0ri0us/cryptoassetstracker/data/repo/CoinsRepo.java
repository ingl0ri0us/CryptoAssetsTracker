package com.ingl0ri0us.cryptoassetstracker.data.repo;

import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapApiKey;
import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapEndpoints;
import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CoinsRepo implements Repo {
    NetworkStatus networkStatus;
    CoinMarketCapEndpoints api;
    String apiKey = CoinMarketCapApiKey.COINMARKETCAP_API_KEY;

    public CoinsRepo(NetworkStatus networkStatus, CoinMarketCapEndpoints api) {
        this.networkStatus = networkStatus;
        this.api = api;
    }

    @Override
    public Single<List<ShortCoinInfo>> getCoinsList() {
        if (networkStatus.isOnline()) {
            return api.getIdMap(apiKey)
                    .flatMapObservable(coinIdMapResponse ->
                            Observable.fromArray(coinIdMapResponse.data))
                    .flatMap(data -> {
                        ShortCoinInfo coin = new ShortCoinInfo(data.getName(), data.getRank());
                        return Observable.just(coin);
                    })
                    .toSortedList()
                    .map(list -> {
                        // TODO: 2020-01-19 implement cache
                        Timber.d(list.toString() + " to cache");
                        return list;
                    })
                    .subscribeOn(Schedulers.computation());
        } else {
            Timber.d("cache not implemented yet");
            return null;
        }
    }
}
