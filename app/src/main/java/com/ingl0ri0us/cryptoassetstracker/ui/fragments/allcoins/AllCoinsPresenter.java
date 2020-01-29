package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins;

import android.annotation.SuppressLint;

import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;
import com.ingl0ri0us.cryptoassetstracker.navigation.Screens;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.CoinsListToDisplay;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.DisplayableCoinsList;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.InitialCoinsList;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.CoinsList;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@InjectViewState
public class AllCoinsPresenter extends MvpPresenter<AllCoinsView> {

    private Scheduler mainThreadScheduler;
    private InitialCoinsList initialCoinsList;
    private CoinsListToDisplay coinsListToDisplay;

    @Inject
    Repo coinsRepo;

    @Inject
    Router router;

    @Inject
    NetworkStatus networkStatus;

    public AllCoinsPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        initialCoinsList = new InitialCoinsList();
        coinsListToDisplay = new CoinsListToDisplay();
    }

    CoinsList getInitialCoinsList() {
        return initialCoinsList;
    }

    DisplayableCoinsList getCoinListToDisplay() {
        return coinsListToDisplay;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();

        coinsListToDisplay.getClickedListItem().subscribe(listItem -> {
            if(networkStatus.isOnline()) {
                String coinId = Integer.toString(listItem.getCoinId());
                router.navigateTo(new Screens.FullCoinInfoScreen(coinId));
            } else {
                getViewState().showMessage("Device is currently offline, unable to show full coin info.");
            }
        });

        coinsListToDisplay.getFilteredList().subscribe(list -> {
            coinsListToDisplay.setList(list);
            getViewState().updateList();
        });
    }

    @SuppressLint("CheckResult")
    public void loadData() {
        getViewState().showLoading();
        coinsRepo.getSortedByRankCoinsList()
                .observeOn(mainThreadScheduler)
                .subscribe(list -> {
                    initialCoinsList.setList(list);
                    coinsListToDisplay.setList(list);
                    getViewState().updateList();
                    getViewState().hideLoading();
                }, Timber::e);
    }
}