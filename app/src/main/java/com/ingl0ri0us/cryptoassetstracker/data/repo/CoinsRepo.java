package com.ingl0ri0us.cryptoassetstracker.data.repo;

import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapApiKey;
import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapEndpoints;
import com.ingl0ri0us.cryptoassetstracker.data.cache.Cache;
import com.ingl0ri0us.cryptoassetstracker.data.entity.FullCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CoinsRepo implements Repo {
    Cache cache;
    NetworkStatus networkStatus;
    CoinMarketCapEndpoints api;
    String apiKey = CoinMarketCapApiKey.COINMARKETCAP_API_KEY;

    public CoinsRepo(Cache cache, NetworkStatus networkStatus, CoinMarketCapEndpoints api) {
        this.cache = cache;
        this.networkStatus = networkStatus;
        this.api = api;
    }

    @Override
    public Single<List<ShortCoinInfo>> getSortedByRankCoinsList() {
        if (networkStatus.isOnline()) {
            return api.getIdMap(apiKey)
                    .flatMapObservable(coinIdMapResponse ->
                            Observable.fromArray(coinIdMapResponse.getData()))
                    .flatMap(data -> {
                        ShortCoinInfo coin = new ShortCoinInfo(data.getId(), data.getName(), data.getRank());
                        cache.putShortCoinInfo(coin).subscribe();
                        return Observable.just(coin);
                    })
                    .toSortedList()
                    .subscribeOn(Schedulers.computation());
        } else {
            return cache.getAllCoins().map(list -> {
                list.sort(ShortCoinInfo::compareTo);
                return list;
            }).subscribeOn(Schedulers.computation());
        }
    }

    @Override
    public Single<FullCoinInfo> getFullCoinInfoById(String coinId) {
        return api.getFullCoinInfo(apiKey, coinId)
                .map(fullCoinInfoResponse -> {
                    String name = fullCoinInfoResponse.getDataByCoinId(coinId).getName();
                    String url = fullCoinInfoResponse.getDataByCoinId(coinId).getUrls().getWebsite();
                    String description = fullCoinInfoResponse.getDataByCoinId(coinId).getDescription();
                    String thumbnail = fullCoinInfoResponse.getDataByCoinId(coinId).getLogo();

                    return new FullCoinInfo(name, url, description, thumbnail);
                }).subscribeOn(Schedulers.io());
    }
}