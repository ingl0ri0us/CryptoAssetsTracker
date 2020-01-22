package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

public interface ListItem {
    int getItemPosition();
    void setCoinName(String coinName);
    void setCoinRank(String coinRank);
    void setCoinId(int id);

    int getCoinId();
}
