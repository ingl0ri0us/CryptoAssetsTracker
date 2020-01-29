package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class CoinsListToDisplay implements DisplayableCoinsList {

    private PublishSubject<ListItem> clickSubject = PublishSubject.create();
    private List<ShortCoinInfo> coinsListToDisplay = new ArrayList<>();
    private PublishSubject<List<ShortCoinInfo>> listToPublish = PublishSubject.create();

    @Override
    public void bind(ListItem item) {
        ShortCoinInfo coin = coinsListToDisplay.get(item.getItemPosition());
        item.setCoinName(coin.getCoinName());
        item.setCoinRank(Integer.toString(coin.getCoinRank()));
        item.setCoinId(coin.getCoinId());
    }

    @Override
    public int getCount() {
        return coinsListToDisplay.size();
    }

    @Override
    public PublishSubject<ListItem> getClickedListItem() {
        return clickSubject;
    }

    @Override
    public void setList(List<ShortCoinInfo> list) {
        coinsListToDisplay = list;
    }

    @Override
    public List<ShortCoinInfo> getList() {
        return coinsListToDisplay;
    }

    @Override
    public PublishSubject<List<ShortCoinInfo>> getFilteredList() {
        return listToPublish;
    }
}