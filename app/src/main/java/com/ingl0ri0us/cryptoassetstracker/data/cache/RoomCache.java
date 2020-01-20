package com.ingl0ri0us.cryptoassetstracker.data.cache;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.data.entity.room.RoomShortCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.data.entity.room.db.Database;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RoomCache implements Cache {
    Database database;

    public RoomCache(Database database) {
        this.database = database;
    }

    @Override
    public Single<ShortCoinInfo> getShortCoinInfoById(int coinId) {
        return Single.create(emitter -> {
            RoomShortCoinInfo roomCoin = database
                    .getDaoShortCoinInfo()
                    .findByCoinId(coinId);

            if (roomCoin == null) {
                emitter.onError(new RuntimeException("No such coin in cache"));
            } else {
                emitter.onSuccess(new ShortCoinInfo(roomCoin.getCoinId(), roomCoin.getCoinName(), roomCoin.getCoinRank()));
            }
        }).subscribeOn(Schedulers.io()).cast(ShortCoinInfo.class);
    }

    @Override
    public Single<ShortCoinInfo> getShortCoinInfoByName(String coinName) {
        return Single.create(emitter -> {
            RoomShortCoinInfo roomCoin = database
                    .getDaoShortCoinInfo()
                    .findByCoinName(coinName);

            if (roomCoin == null) {
                emitter.onError(new RuntimeException("No such coin in cache"));
            } else {
                emitter.onSuccess(new ShortCoinInfo(roomCoin.getCoinId(), roomCoin.getCoinName(), roomCoin.getCoinRank()));
            }
        }).subscribeOn(Schedulers.io()).cast(ShortCoinInfo.class);
    }

    @Override
    public Single<List<ShortCoinInfo>> getAllCoins() {
        return Single.create(emitter -> {
            List<RoomShortCoinInfo> allRoomCoins = database.getDaoShortCoinInfo().getAll();

            if (allRoomCoins == null) {
                emitter.onError(new RuntimeException("No coins in cache"));
            } else {
                List<ShortCoinInfo> allCoins = new ArrayList<>();
                for (RoomShortCoinInfo roomCoin : allRoomCoins) {
                    allCoins.add(new ShortCoinInfo(roomCoin.getCoinId(), roomCoin.getCoinName(), roomCoin.getCoinRank()));
                }
                emitter.onSuccess(allCoins);
            }
        }).subscribeOn(Schedulers.io()).cast((Class<List<ShortCoinInfo>>) (Class) List.class);
    }

    @Override
    public Completable putShortCoinInfo(ShortCoinInfo coin) {
        return Completable.fromAction(() ->{
            RoomShortCoinInfo roomCoin = new RoomShortCoinInfo();
            roomCoin.setCoinId(coin.getCoinId());
            roomCoin.setCoinName(coin.getCoinName());
            roomCoin.setCoinRank(coin.getCoinRank());

            database.getDaoShortCoinInfo().insert(roomCoin);
        });
    }
}