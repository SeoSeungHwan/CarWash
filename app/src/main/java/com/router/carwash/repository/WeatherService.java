package com.router.carwash.repository;


import com.router.carwash.BuildConfig;
import com.router.carwash.model.apiInfo.WeatherInfo;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface WeatherService {

    String BASE_URL ="http://api.openweathermap.org/data/2.5/";


   //api키 ignore하기!
    @GET("forecast?appid="+BuildConfig.OpenWeather_Key+"&lang=kr")
    Call<WeatherInfo> getJson(@Query("lat") String tmFc,
                              @Query("lon") String lon);

}
