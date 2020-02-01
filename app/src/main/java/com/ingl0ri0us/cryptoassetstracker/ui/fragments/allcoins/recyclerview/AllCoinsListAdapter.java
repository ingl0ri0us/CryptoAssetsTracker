package com.ingl0ri0us.cryptoassetstracker.ui.fragments.allcoins.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingl0ri0us.cryptoassetstracker.R;
import com.ingl0ri0us.cryptoassetstracker.data.entity.ShortCoinInfo;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCoinsListAdapter extends RecyclerView.Adapter<AllCoinsListAdapter.ViewHolder> implements Filterable {

    private DisplayableCoinsList coinsListToDisplay;
    private CoinsList initialCoinsList;

    public AllCoinsListAdapter(CoinsList initialCoinsList, DisplayableCoinsList coinsListToDisplay) {
        this.initialCoinsList = initialCoinsList;
        this.coinsListToDisplay = coinsListToDisplay;
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
        coinsListToDisplay.bind(holder);

        RxView
                .clicks(holder.itemView)
                .map(o -> holder)
                .subscribe(coinsListToDisplay.getClickedListItem());
    }

    @Override
    public int getItemCount() {
        return coinsListToDisplay.getCount();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<ShortCoinInfo> filteredTempList = new ArrayList<>();
                FilterResults filterResults = new FilterResults();
                if (charString.isEmpty()) {
                    filterResults.values = initialCoinsList.getList();
                } else {
                    for (ShortCoinInfo item : initialCoinsList.getList()) {
                        if (item.getCoinName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredTempList.add(item);
                        }
                    }
                    filterResults.values = filteredTempList;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                coinsListToDisplay.getFilteredList().onNext((ArrayList<ShortCoinInfo>) results.values);
            }
        };
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