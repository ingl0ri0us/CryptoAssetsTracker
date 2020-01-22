package com.ingl0ri0us.cryptoassetstracker.ui.fragments.fullcoininfo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingl0ri0us.cryptoassetstracker.App;
import com.ingl0ri0us.cryptoassetstracker.R;
import com.ingl0ri0us.cryptoassetstracker.data.image.ImageLoader;
import com.ingl0ri0us.cryptoassetstracker.data.image.PicassoImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class FullCoinInfoFragment extends MvpAppCompatFragment implements FullCoinInfoView {

//    @Inject
//    ImageLoader<ImageView> imageLoader;

    ImageLoader<ImageView> imageLoader = new PicassoImageLoader();

    public static FullCoinInfoFragment newInstance(String coinId) {
        FullCoinInfoFragment fragment = new FullCoinInfoFragment();
        Bundle args = new Bundle();
        args.putString("coinId", coinId);
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    FullCoinInfoPresenter fullCoinInfoPresenter;

    @BindView(R.id.fullCoinInfo_coinName)
    TextView coinName;

    @BindView(R.id.fullCoinInfo_coinDescription)
    TextView coinDescription;

    @BindView(R.id.fullCoinInfo_urlAddress)
    TextView coinUrlAddress;

    @BindView(R.id.fullCoinInfo_coinThumbnail)
    ImageView coinThumbnail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_coin_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @ProvidePresenter
    public FullCoinInfoPresenter providePresenter() {
        FullCoinInfoPresenter fullCoinInfoPresenter = new FullCoinInfoPresenter
                (AndroidSchedulers.mainThread(), getArguments().getString("coinId"));
        App.getInstance().getAppComponent().inject(fullCoinInfoPresenter);
        return fullCoinInfoPresenter;
    }

    @Override
    public void init() {

    }

    @Override
    public void setCoinName(String coinName) {
        this.coinName.setText(coinName);
    }

    @Override
    public void setCoinUrl(String coinUrl) {
        this.coinUrlAddress.setText(coinUrl);
    }

    @Override
    public void setCoinDescription(String coinDescription) {
        this.coinDescription.setText(coinDescription);
    }

    @Override
    public void setCoinThumbnail(String thumbnailUrl) {
        imageLoader.loadInto(thumbnailUrl, coinThumbnail);
    }
}
