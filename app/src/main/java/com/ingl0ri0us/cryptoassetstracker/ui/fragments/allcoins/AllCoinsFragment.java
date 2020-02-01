package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ingl0ri0us.cryptoassetstracker.App;
import com.ingl0ri0us.cryptoassetstracker.R;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.AllCoinsListAdapter;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class AllCoinsFragment extends MvpAppCompatFragment implements AllCoinsView {
    //git test branch

    public static AllCoinsFragment newInstance() {
        return new AllCoinsFragment();
    }

    @InjectPresenter
    AllCoinsPresenter presenter;

    @BindView(R.id.recyclerView_coinName_with_rank)
    RecyclerView recyclerView;

    @BindView(R.id.loading_layout)
    RelativeLayout loadingLayout;

    @BindView(R.id.coin_search)
    EditText coinSearchEditText;

    private CompositeDisposable disposable = new CompositeDisposable();

    private AllCoinsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_coins, container, false);
        ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        disposable
                .add(RxTextView
                        .textChangeEvents(coinSearchEditText)
                        .skipInitialValue()
                        .debounce(700, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(searchCoin()));
    }

    @ProvidePresenter
    AllCoinsPresenter providePresenter() {
        AllCoinsPresenter presenter = new AllCoinsPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AllCoinsListAdapter(presenter.getInitialCoinsList(), presenter.getCoinListToDisplay());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    private DisposableObserver<TextViewTextChangeEvent> searchCoin() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                adapter.getFilter().filter(textViewTextChangeEvent.getText());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}