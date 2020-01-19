package com.ingl0ri0us.cryptoassetstracker.data.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CoinMarketCapEndpoints {
    @GET("/v1/cryptocurrency/map")
    Single<CoinIdMapResponse> getIdMap(@Header("X-CMC_PRO_API_KEY") String ApiKey);
}
