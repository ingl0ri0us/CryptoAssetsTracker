package com.ingl0ri0us.cryptoassetstracker.data.image;

public interface ImageLoader<T> {
    void loadInto(String url, T container);
}