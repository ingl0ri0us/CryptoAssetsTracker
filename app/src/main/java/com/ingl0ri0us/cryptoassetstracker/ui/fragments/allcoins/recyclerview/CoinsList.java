package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface CoinsList {
    void bind(ListItem item);
    int getCount();
    PublishSubject<ListItem> getClickSubject();
    void setList(List list);
}