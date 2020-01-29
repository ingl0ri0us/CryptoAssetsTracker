package com.ingl0ri0us.cryptoassetstracker.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import retrofit2.http.Url;

public class FullCoinInfoResponse {

    @SerializedName("status")
    Status status;

    @SerializedName("data")
    Map<String, FullCoinInfoRestModel> data;

    public FullCoinInfoRestModel getDataByCoinId(String coinId) {
        return data.get(coinId);
    }

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
    }

    public class FullCoinInfoRestModel {
        @SerializedName("urls")
        Urls urls;
        @SerializedName("platform")
        Platform platform;
        @SerializedName("id")
        int id;
        @SerializedName("name")
        String name;
        @SerializedName("symbol")
        String symbol;
        @SerializedName("category")
        String category;
        @SerializedName("slug")
        String slug;
        @SerializedName("logo")
        String logo;
        @SerializedName("description")
        String description;
        @SerializedName("date_added")
        String dateAdded;
        @SerializedName("notice")
        String notice;
        @SerializedName("tags")
        String[] tags;

        public String getName() {
            return name;
        }

        public String getLogo() {
            return logo;
        }

        public String getDescription() {
            return description;
        }

        public Urls getUrls() {
            return urls;
        }


        public class Urls {
            @SerializedName("website")
            String[] website;
            @SerializedName("technical_doc")
            String[] technical_doc;
            @SerializedName("explorer")
            String[] explorer;
            @SerializedName("source_code")
            String[] source_code;
            @SerializedName("message_board")
            String[] message_board;
            @SerializedName("chat")
            String[] chat;
            @SerializedName("announcement")
            String[] announcement;
            @SerializedName("reddit")
            String[] reddit;
            @SerializedName("twitter")
            String[] twitter;

            public String getWebsite() {
                return website[0];
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
}
