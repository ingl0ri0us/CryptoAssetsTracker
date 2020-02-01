package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.List;

public interface CoinsList {
    void setList(List<ShortCoinInfo> list);
    List<ShortCoinInfo> getList();

}