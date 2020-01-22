package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingl0ri0us.cryptoassetstracker.R;
import com.jakewharton.rxbinding3.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCoinsListAdapter extends RecyclerView.Adapter<AllCoinsListAdapter.ViewHolder> {

    private CoinsList coinsList;

    public AllCoinsListAdapter(CoinsList coinsList) {
        this.coinsList = coinsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_coin_with_rank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemPosition = position;
        coinsList.bind(holder);

        //click on item
        RxView
                .clicks(holder.itemView)
                .map(o -> holder)
                .subscribe(coinsList.getClickSubject());
    }

    @Override
    public int getItemCount() {
        return coinsList.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ListItem {
        int itemPosition = 0;
        int coinId = 0;

        @BindView(R.id.coin_name)
        TextView coinNameField;

        @BindView(R.id.coin_rank)
        TextView coinRankField;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getItemPosition() {
            return itemPosition;
        }

        @Override
        public void setCoinName(String coinName) {
            coinNameField.setText(coinName);
        }

        @Override
        public void setCoinRank(String coinRank) {
            coinRankField.setText(coinRank);
        }

        @Override
        public void setCoinId(int id) {
            coinId = id;
        }

        @Override
        public int getCoinId() {
            return coinId;
        }
    }
}