package com.ingl0ri0us.cryptoassetstracker.data.entity;

public class ShortCoinInfo implements Comparable<ShortCoinInfo> {
    private int coinId;
    private String coinName;
    private int coinRank;

    public ShortCoinInfo(int coinId, String coinName, int coinRank) {
        this.coinId = coinId;
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

    public int getCoinId() {
        return coinId;
    }
}