package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins;

import android.annotation.SuppressLint;

import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.AllCoinsList;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.CoinsList;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import timber.log.Timber;

@InjectViewState
public class AllCoinsPresenter extends MvpPresenter<AllCoinsView> {

    private Scheduler mainThreadScheduler;
    private AllCoinsList allCoinsList;

    @Inject
    Repo coinsRepo;

    public AllCoinsPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        allCoinsList = new AllCoinsList();
    }

    public CoinsList getAllCoinsList() {
        return allCoinsList;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }

    //possible place of error, wrong scheduler
    @SuppressLint("CheckResult")
    private void loadData() {
        getViewState().showLoading();
        coinsRepo.getCoinsList()
                .observeOn(mainThreadScheduler)
                .subscribe(list -> {
                    allCoinsList.setList(list);
                    getViewState().updateList();
                    getViewState().hideLoading();
                }, Timber::e);
    }
}
