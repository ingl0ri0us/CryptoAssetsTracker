package com.ingl0ri0us.cryptoassetstracker.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ingl0ri0us.cryptoassetstracker.App;

import java.util.Objects;

public class DeviceNetworkStatus implements NetworkStatus {
    @Override
    public Status getStatus() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) App
                        .getInstance()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        if (activeNetwork != null) {
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return Status.WIFI;

                case ConnectivityManager.TYPE_ETHERNET:
                    return Status.ETHERNET;

                case ConnectivityManager.TYPE_MOBILE:
                    return Status.MOBILE;
            }
            return Status.OTHER;
        }
        return Status.OFFLINE;
    }

    @Override
    public boolean isOnline() {
        return !getStatus().equals(Status.OFFLINE);
    }

    @Override
    public boolean isWife() {
        return getStatus().equals(Status.WIFI);
    }

    @Override
    public boolean isEthernet() {
        return getStatus().equals(Status.ETHERNET);
    }

    @Override
    public boolean isMobile() {
        return getStatus().equals(Status.MOBILE);
    }

    @Override
    public boolean isOffline() {
        return getStatus().equals(Status.OFFLINE);
    }
}
