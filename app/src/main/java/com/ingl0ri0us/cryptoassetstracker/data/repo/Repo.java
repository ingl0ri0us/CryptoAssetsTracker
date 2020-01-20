package com.ingl0ri0us.cryptoassetstracker.data.repo;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.List;

import io.reactivex.Single;

public interface Repo {
    Single<List<ShortCoinInfo>> getSortedByRankCoinsList();
}
