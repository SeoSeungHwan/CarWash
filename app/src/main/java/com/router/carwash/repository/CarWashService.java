package com.router.carwash.repository;

import com.router.carwash.model.apiInfo.CarWashInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CarWashService {
    String BASE_URL ="http://dapi.kakao.com/";

    @Headers("Authorization: KakaoAK " +"76ecf187f47db84d6fe88344a32b9d99" )
    @GET("v2/local/search/keyword.json")
    Call<CarWashInfo> getSearchKeyword(
            @Query("query") String query,
            @Query("x") Double x,
            @Query("y") Double y,
            @Query("radius") int size
    );
}
