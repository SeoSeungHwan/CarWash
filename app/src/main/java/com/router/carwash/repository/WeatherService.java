package com.router.carwash.repository;


import com.router.carwash.R;
import com.router.carwash.model.Item;
import com.router.carwash.model.Response;
import com.router.carwash.model.Weather;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface WeatherService {

    String BASE_URL ="http://apis.data.go.kr/1360000/MidFcstInfoService/";

    //api키 ignore하기!
    @GET("getMidTa?serviceKey=TPjqY3dBCSVQ6T0f%2BBo7WsczzD%2FAy7pmHDdcXDJwRpeE8P4LVp%2Bxq8g8IaQcOLYGSkMWPi4ofPfwEuctz4DRGA%3D%3D"+
            "&numOfRows=10"+
            "&pageNo=1"+
            "&dataType=JSON"+
            "&regId=11D20501")
    Call<Weather> getJson(@Query("tmFc") String tmFc);
}
