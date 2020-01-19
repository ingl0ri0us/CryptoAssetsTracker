package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ingl0ri0us.cryptoassetstracker.App;
import com.ingl0ri0us.cryptoassetstracker.R;
import com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview.AllCoinsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class AllCoinsFragment extends MvpAppCompatFragment implements AllCoinsView {
    public static AllCoinsFragment getInstance() {
        // TODO: 2020-01-19 add Bundle
        return new AllCoinsFragment();
    }

    @InjectPresenter
    AllCoinsPresenter presenter;

    @BindView(R.id.recyclerView_coinName_with_rank)
    RecyclerView recyclerView;

    @BindView(R.id.loading_layout)
    RelativeLayout loadingLayout;

    AllCoinsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_coins, container, false);
        ButterKnife.bind(this,view);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @ProvidePresenter
    public AllCoinsPresenter providePresenter() {
        AllCoinsPresenter presenter = new AllCoinsPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AllCoinsListAdapter(presenter.getAllCoinsList());
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
}