package com.ingl0ri0us.cryptoassetstracker.ui.fragments.watchlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class WatchlistViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<String> mText;

    public WatchlistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}