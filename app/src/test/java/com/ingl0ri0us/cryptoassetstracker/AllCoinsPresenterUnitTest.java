package com.ingl0ri0us.cryptoassetstracker;


import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.data.repo.Repo;
import com.ingl0ri0us.cryptoassetstracker.di.DaggerTestComponent;
import com.ingl0ri0us.cryptoassetstracker.di.TestComponent;
import com.ingl0ri0us.cryptoassetstracker.di.TestRepoModule;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsPresenter;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.AllCoinsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

public class AllCoinsPresenterUnitTest {
    private AllCoinsPresenter presenter;
    private TestScheduler testScheduler;

    @Mock
    AllCoinsView allCoinsView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        presenter = Mockito.spy(new AllCoinsPresenter(testScheduler));
    }

    @Test
    public void loadInfoSuccess() {
        List<ShortCoinInfo> testList = new ArrayList<>();
        testList.add(new ShortCoinInfo(10, "coin1", 10));
        testList.add(new ShortCoinInfo(9, "coin9", 9));
        testList.add(new ShortCoinInfo(8, "coin8", 8));
        testList.add(new ShortCoinInfo(7, "coin7", 7));
        testList.add(new ShortCoinInfo(6, "coin6", 6));
        testList.add(new ShortCoinInfo(5, "coin5", 5));
        testList.add(new ShortCoinInfo(4, "coin4", 4));

        TestComponent testComponent = DaggerTestComponent
                .builder()
                .testRepoModule(new TestRepoModule() {
                    @Override
                    public Repo getCoinsRepo() {
                        Repo repo = super.getCoinsRepo();
                        Mockito.when(repo.getSortedByRankCoinsList()).thenReturn(Single.just(testList));
                        return repo;
                    }
                }).build();
        testComponent.inject(presenter);
        presenter.attachView(allCoinsView);
        Mockito.verify(presenter).loadData();
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        Mockito.verify(allCoinsView, Mockito.times(1)).showLoading();
        Mockito.verify(allCoinsView).updateList();
    }
}