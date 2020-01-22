package com.ingl0ri0us.cryptoassetstracker.navigation;

import androidx.fragment.app.Fragment;

import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsFragment;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.fullcoininfo.FullCoinInfoFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class AllCoinsScreen extends SupportAppScreen {

        @Override
        public Fragment getFragment() {
            return AllCoinsFragment.newInstance();
        }
    }

    public static class FullCoinInfoScreen extends SupportAppScreen {

        String coinId;

        public FullCoinInfoScreen(String coinId) {
            this.coinId = coinId;
        }

        @Override
        public Fragment getFragment() {
            return FullCoinInfoFragment.newInstance(coinId);
        }
    }
 }
