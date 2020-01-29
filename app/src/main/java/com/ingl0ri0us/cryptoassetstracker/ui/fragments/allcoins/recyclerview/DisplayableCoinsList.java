package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface DisplayableCoinsList extends CoinsList {
    void bind(ListItem item);
    int getCount();
    PublishSubject<ListItem> getClickedListItem();
    PublishSubject<List<ShortCoinInfo>> getFilteredList();
}