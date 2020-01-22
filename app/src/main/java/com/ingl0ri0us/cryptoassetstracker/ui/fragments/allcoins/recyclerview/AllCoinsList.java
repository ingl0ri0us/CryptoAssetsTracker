package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class AllCoinsList implements CoinsList {
    PublishSubject<ListItem> clckSubject = PublishSubject.create();
    List<ShortCoinInfo> shortCoinInfoList = new ArrayList<>();

    @Override
    public void bind(ListItem item) {
        ShortCoinInfo coin = shortCoinInfoList.get(item.getItemPosition());
        item.setCoinName(coin.getCoinName());
        item.setCoinRank(Integer.toString(coin.getCoinRank()));
        item.setCoinId(coin.getCoinId());
    }

    @Override
    public int getCount() {
        return shortCoinInfoList.size();
    }

    @Override
    public PublishSubject<ListItem> getClickSubject() {
        return clckSubject;
    }

    @Override
    public void setList(List list) {
        shortCoinInfoList = list;
    }
}
