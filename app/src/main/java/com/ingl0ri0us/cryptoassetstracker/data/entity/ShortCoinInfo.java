package com.ingl0ri0us.cryptoassetstracker.data.entity;

public class ShortCoinInfo implements Comparable<ShortCoinInfo> {
    private String coinName;
    private int coinRank;

    public ShortCoinInfo(String coinName, int coinRank) {
        this.coinName = coinName;
        this.coinRank = coinRank;
    }

    @Override
    public int compareTo(ShortCoinInfo compareToThatCoin) {
        return this.coinRank - compareToThatCoin.getCoinRank();
    }

    public String getCoinName() {
        return coinName;
    }

    public int getCoinRank() {
        return coinRank;
    }
}
