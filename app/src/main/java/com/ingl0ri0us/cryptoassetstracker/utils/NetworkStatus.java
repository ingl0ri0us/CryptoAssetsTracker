package com.ingl0ri0us.cryptoassetstracker.utils;

public interface NetworkStatus {
    enum Status {
        WIFI,
        MOBILE,
        ETHERNET,
        OTHER,
        OFFLINE,
    }

    Status getStatus();

    boolean isOnline();

    boolean isWife();

    boolean isEthernet();

    boolean isMobile();

    boolean isOffline();
}
