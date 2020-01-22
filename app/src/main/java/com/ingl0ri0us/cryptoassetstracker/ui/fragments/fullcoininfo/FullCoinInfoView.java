package com.ingl0ri0us.cryptoassetstracker.ui.fragments.fullcoininfo;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface FullCoinInfoView extends MvpView {

    void init();

    void setCoinName(String coinName);

    void setCoinUrl(String coinUrl);

    void setCoinDescription(String coinDescription);

    void setCoinThumbnail(String thumbnailUrl);
}
