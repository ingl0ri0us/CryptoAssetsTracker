package com.ingl0ri0us.cryptoassetstracker.ui.fragments.fullcoininfo;

import android.annotation.SuppressLint;

import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class FullCoinInfoPresenter extends MvpPresenter<FullCoinInfoView> {

    private Scheduler mainThreadScheduler;
    private String coinId;

    @Inject
    Repo repo;

    FullCoinInfoPresenter(Scheduler mainThreadScheduler, String coinId) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.coinId = coinId;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        repo.getFullCoinInfoById(coinId)
                .observeOn(mainThreadScheduler)
                .subscribe(fullCoinInfo -> {
                    getViewState().setCoinName(fullCoinInfo.getCoinName());
                    getViewState().setCoinDescription(fullCoinInfo.getCoinDescription());
                    getViewState().setCoinUrl(fullCoinInfo.getCoinUrlAddress());
                    getViewState().setCoinThumbnail(fullCoinInfo.getCoinThumbnailUrl());
                });
    }
}