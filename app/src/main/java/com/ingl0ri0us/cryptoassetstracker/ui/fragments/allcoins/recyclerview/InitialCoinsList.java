package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;

import java.util.ArrayList;
import java.util.List;


public class InitialCoinsList implements CoinsList {

    private List<ShortCoinInfo> initialCoinInfoList = new ArrayList<>();

    @Override
    public void setList(List<ShortCoinInfo> list) {
        initialCoinInfoList = list;
    }

    @Override
    public List<ShortCoinInfo> getList() {
        return initialCoinInfoList;
    }

}