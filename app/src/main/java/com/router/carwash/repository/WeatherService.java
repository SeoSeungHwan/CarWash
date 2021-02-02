package com.router.carwash.repository;


import com.router.carwash.model.apiInfo.WeatherInfo;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface WeatherService {

    String BASE_URL ="http://api.openweathermap.org/data/2.5/";

   //api키 ignore하기!
    @GET("forecast?appid=e0bd2e6604e6512dff6235b57ef889fc&lang=kr")
    Call<WeatherInfo> getJson(@Query("lat") String tmFc,
                              @Query("lon") String lon);

    /*@GET("forecast?lat=36.769670&lon=126.931656&appid=e0bd2e6604e6512dff6235b57ef889fc&lang=kr")
    Call<WeatherInfo> getJson();
    */
}
