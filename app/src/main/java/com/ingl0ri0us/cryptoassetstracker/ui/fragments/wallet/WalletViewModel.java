package com.ingl0ri0us.cryptoassetstracker.ui.fragments.wallet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WalletViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}