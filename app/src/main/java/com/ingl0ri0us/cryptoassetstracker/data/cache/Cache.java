package com.ingl0ri0us.cryptoassetstracker.data.cache;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface Cache {
    Single<ShortCoinInfo> getShortCoinInfoById(int coinId);
    Single<ShortCoinInfo> getShortCoinInfoByName(String coinName);
    Single<List<ShortCoinInfo>> getAllCoins();

    Completable putShortCoinInfo(ShortCoinInfo coin);
}
