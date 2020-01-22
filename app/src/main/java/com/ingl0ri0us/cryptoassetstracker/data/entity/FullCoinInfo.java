package com.ingl0ri0us.cryptoassetstracker.data.entity;

public class FullCoinInfo {

    private String coinName;
    private String coinUrlAddress;
    private String coinDescription;
    private String coinThumbnailUrl;

    public FullCoinInfo(String coinName, String coinUrlAddress, String coinDescription, String coinThumbnailUrl) {
        this.coinName = coinName;
        this.coinUrlAddress = coinUrlAddress;
        this.coinDescription = coinDescription;
        this.coinThumbnailUrl = coinThumbnailUrl;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinUrlAddress() {
        return coinUrlAddress;
    }

    public void setCoinUrlAddress(String coinUrlAddress) {
        this.coinUrlAddress = coinUrlAddress;
    }

    public String getCoinDescription() {
        return coinDescription;
    }

    public void setCoinDescription(String coinDescription) {
        this.coinDescription = coinDescription;
    }

    public String getCoinThumbnailUrl() {
        return coinThumbnailUrl;
    }

    public void setCoinThumbnailUrl(String coinThumbnailUrl) {
        this.coinThumbnailUrl = coinThumbnailUrl;
    }
}
