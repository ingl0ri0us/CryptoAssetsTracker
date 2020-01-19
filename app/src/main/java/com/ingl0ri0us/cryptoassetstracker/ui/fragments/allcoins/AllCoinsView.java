package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface AllCoinsView extends MvpView {
    void init();
    void updateList();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String text);

    void showLoading();
    void hideLoading();
}
