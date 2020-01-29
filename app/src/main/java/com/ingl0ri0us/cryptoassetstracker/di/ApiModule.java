package com.ingl0ri0us.cryptoassetstracker.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapEndpoints;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkStatusModule.class)
class ApiModule {
    @Named("baseUrl")
    @Provides
    String baseUrl() {
        return "https://pro-api.coinmarketcap.com/";
    }

    @Singleton
    @Provides
    CoinMarketCapEndpoints apiService(
            @Named("clientLogging") OkHttpClient okHttpClient,
            Gson gson,
            @Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(CoinMarketCapEndpoints.class);
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    @Named("clientLogging")
    @Singleton
    @Provides
    OkHttpClient okHttpClientLogging(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Gson gson() {
        return new GsonBuilder().create();
    }
}