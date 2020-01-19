package com.ingl0ri0us.cryptoassetstracker.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ingl0ri0us.cryptoassetstracker.data.api.CoinMarketCapEndpoints;
import com.ingl0ri0us.cryptoassetstracker.utils.DeviceNetworkStatus;
import com.ingl0ri0us.cryptoassetstracker.utils.NetworkStatus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Named("baseUrl")
    @Provides
    public String baseUrl() {
        return "https://pro-api.coinmarketcap.com/";
    }

    @Singleton
    @Provides
    public CoinMarketCapEndpoints apiService(
            @Named("clientLogging")OkHttpClient okHttpClient,
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
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    @Named("clientLogging")
    @Singleton
    @Provides
    public OkHttpClient okHttpClientLogging(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public NetworkStatus networkStatus() {
        return new DeviceNetworkStatus();
    }

    @Singleton
    @Provides
    public Gson gson() {
        return new GsonBuilder().create();
    }

}
