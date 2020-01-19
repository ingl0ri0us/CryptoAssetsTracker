package com.ingl0ri0us.cryptoassetstracker.data.api;

import com.google.gson.annotations.SerializedName;

public class CoinIdMapResponse {
    @SerializedName("status")
    Status status;
    @SerializedName("data")
    public Data[] data;

    class Status {
        @SerializedName("timestamp")
        String timestamp;
        @SerializedName("error_code")
        int errorCode;
        @SerializedName("error_message")
        String errorMessage;
        @SerializedName("elapsed")
        int elapsed;
        @SerializedName("credit_count")
        int credit_count;
        @SerializedName("notice")
        String notice;
    }

    public class Data {
        @SerializedName("id")
        int id;
        @SerializedName("name")
        String name;
        @SerializedName("symbol")
        String symbol;
        @SerializedName("slug")
        String slug;
        @SerializedName("is_active")
        int isActive;
        @SerializedName("rank")
        int rank;
        @SerializedName("first_historical_data")
        String firstHistoricalData;
        @SerializedName("last_historical_data")
        String lastHistoricalData;
        @SerializedName("platform")
        Platform platform;

        public String getName() {
            return name;
        }

        public int getRank() {
            return rank;
        }
    }

    class Platform {
        @SerializedName("id")
        int id;
        @SerializedName("name")
        String name;
        @SerializedName("symbol")
        String symbol;
        @SerializedName("slug")
        String slug;
        @SerializedName("token_address")
        String token_address;
    }
}
