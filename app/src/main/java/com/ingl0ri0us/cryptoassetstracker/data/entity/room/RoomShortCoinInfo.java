package com.ingl0ri0us.cryptoassetstracker.data.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomShortCoinInfo {
    @NonNull
    @PrimaryKey
    private int coinId;
    private String coinName;
    private int coinRank;

    public RoomShortCoinInfo() {
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public int getCoinRank() {
        return coinRank;
    }

    public void setCoinRank(int coinRank) {
        this.coinRank = coinRank;
    }
}
